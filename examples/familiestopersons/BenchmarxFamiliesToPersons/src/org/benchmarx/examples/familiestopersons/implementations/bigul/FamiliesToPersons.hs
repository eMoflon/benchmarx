{-# LANGUAGE TemplateHaskell, TypeFamilies, TupleSections #-}

import Control.Arrow
import Control.Applicative
import Data.Function
import Data.List
import GHC.Generics
import System.Exit

import Generics.BiGUL
import Generics.BiGUL.TH
import Generics.BiGUL.Lib
import Generics.BiGUL.Lib.List
import Generics.BiGUL.Interpreter


--------
-- Families

newtype FamilyRegister = FamilyRegister { families :: [Family] }
  deriving (Show, Read, Eq)

data Family = Family { familyName :: String
                     , father     :: Maybe FamilyMember
                     , mother     :: Maybe FamilyMember
                     , sons       :: [FamilyMember]
                     , daughters  :: [FamilyMember] }
  deriving (Show, Read, Eq)

newtype FamilyMember = FamilyMember { firstName :: String }
  deriving (Show, Read, Eq)

deriveBiGULGeneric ''FamilyRegister
deriveBiGULGeneric ''Family
deriveBiGULGeneric ''FamilyMember

sampleFamilyRegister :: FamilyRegister
sampleFamilyRegister = FamilyRegister
  { families =
      [ Family { familyName = "Simpson"
               , father     = Just (FamilyMember { firstName = "Homer" })
               , mother     = Nothing
               , sons       = []
               , daughters  = [ FamilyMember { firstName = "Lisa" }
                              , FamilyMember { firstName = "Maggie" } ] } ] }


--------
-- Persons

newtype PersonRegister = PersonRegister { persons :: [Person] }
  deriving (Show, Read, Eq)

data Person = Male   { fullName :: String
                     , birthday :: String }
            | Female { fullName :: String
                     , birthday :: String }
  deriving (Show, Read, Eq)

deriveBiGULGeneric ''PersonRegister
deriveBiGULGeneric ''Person

samplePersonRegister :: PersonRegister
samplePersonRegister = PersonRegister
  { persons = [ Male   { fullName = "Simpson, Homer"
                       , birthday = "9 Jan 2013" }
              , Female { fullName = "Simpson, Lisa"
                       , birthday = "Thu Jan 01 00:00:00 CET 1" }
              , Female { fullName = "Simpson, Maggie"
                       , birthday = "Thu Jan 01 00:00:00 CET 1" } ] }


--------
-- synchronisation with persons

type MediumR = [((String, String), Bool)]  -- family name, first name, is male

fuseName :: BiGUL String (String, String)
fuseName = Case
  [ $(normalSV [p| ',':' ':_ |] [p| ([], _) |] [p| ',':_ |])
    ==> $(update [p| ',':' ':firstName |] [p| ([], firstName) |] [d| firstName = Replace |])
  , $(adaptiveSV [p| _ |] [p| ([], _) |])
    ==> \ss _ -> dropWhile (/= ',') ss
  , $(normalSV [| \(s:ss) -> s /= ',' |] [p| (_:_, _) |] [| \(s:ss) -> s /= ','|])
    ==> $(rearrS [| \(s:ss) -> (s, ss) |])$
          $(rearrV [| \(v:vs, vs') -> (v, (vs, vs')) |])$
            Replace `Prod` fuseName
  , $(adaptiveSV [p| ',':_ |] [p| (_:_, _) |])
    ==> \ss (vs, _) -> vs ++ ss
  ]

getFullName :: Person -> String
getFullName (Male   fullName _) = fullName
getFullName (Female fullName _) = fullName

splitFullName :: String -> (String, String)
splitFullName = (id *** (tail . tail)) . span (/= ',')

getFamilyName :: Person -> String
getFamilyName = fst . splitFullName . getFullName

getFirstName :: Person -> String
getFirstName = snd . splitFullName . getFullName

isMale :: Person -> Bool
isMale (Male   _ _) = True
isMale (Female _ _) = False

syncR :: BiGUL PersonRegister MediumR
syncR =
  $(rearrS [| \(PersonRegister ps) -> ps |])$
    align (const True)
          (\p ((familyName, firstName), gender) ->
            getFamilyName p == familyName && getFirstName p == firstName && isMale p == gender)
          (Case
             [ $(normalSV [p| Male _ _ |] [p| _ |] [p| Male _ _ |])
               ==> $(update [p| Male name _ |] [p| (name, True) |] [d| name = fuseName |])
             , $(normalSV [p| Female _ _ |] [p| _ |] [p| Female _ _ |])
               ==> $(update [p| Female name _ |] [p| (name, False) |] [d| name = fuseName |])
             ])
          (\((familyName, firstName), gender) ->
             (if gender then Male else Female) (familyName ++ ", " ++ firstName) "Thu Jan 01 00:00:00 CET 1")
          (const Nothing)


--------
-- intermediate grouping

type MediumL = [(String, [(String, Bool)])]  -- family name, first name, is male

extract :: (Ord a, Show a) => BiGUL (a, [a]) [a]
extract = Case
  [ $(normal [| \(s, _) (v:_) -> v == s |] [| \(s, ss) -> null ss || head ss >= s |])
    ==> $(update [p| (x, xs) |] [p| x:xs |] [d| x = Replace; xs = Replace |])
  , $(normal [| \(s, _:_) (v:_) -> v < s |] [| \(s, s':_) -> s' < s |])
    ==> $(rearrS [| \(s, s':ss) -> (s', (s, ss)) |])$
          $(rearrV [| \(v:vs) -> (v, vs) |])$
            Replace `Prod` extract
  , $(adaptive [| \(s, []) (v:_) -> v < s |])
    ==> \(s, _) _ -> (s, [undefined])
  ]

syncM :: BiGUL MediumL MediumR
syncM = Case
  [ $(normalSV [p| [] |] [p| [] |] [p| [] |])
    ==> $(update [p| _ |] [p| [] |] [d| |])
  , $(normalSV [p| ((familyName, []):_) |] [p| _ |] [p| (_, []):_ |])
    ==> $(update [p| _:rest |] [p| rest |] [d| rest = syncM |])
  , $(normal [| \((familyName, (firstName, gender):_):_) vs -> ((familyName, firstName), gender) `elem` vs |]
             [p| (_, _:_):_ |])
    ==> $(rearrS [| \((familyName, (firstName, gender):ns):ss) ->
                      (((familyName, firstName), gender), (familyName, ns):ss) |])$
          (Replace `Prod` syncM) `Compose` extract
  , $(adaptive [| \_ _ -> otherwise |])
    ==> adapt
  ]
  where
    adapt :: MediumL -> MediumR -> MediumL
    adapt [] vs = map ((fst . fst . head) &&& map (snd *** id)) (groupBy ((==) `on` (fst . fst)) vs)
    adapt ((familyName, ns):ss) vs =
      let ns' :: [(String, Bool)]
          ns' = concat (map snd (filter ((== familyName) . fst) ss))
          vs' :: [(String, Bool)]
          vs' = map (snd *** id) (filter ((== familyName) . fst . fst) vs) \\ (ns' \\ ns)
      in  (familyName, vs') : adapt ss (vs \\ (map ((familyName,) *** id) vs'))


----------
---- synchronisation with families

promoteParent :: BiGUL (Maybe FamilyMember, [String]) [String]
promoteParent = Case
  [ $(normal [| \(Nothing, ns) vs -> null (vs \\ ns) |] [p| (Nothing, _) |])
    ==> $(update [p| (_, ns) |] [p| ns |] [d| ns = Replace |])
  , $(normal [| \(Just (FamilyMember parentName), _) vs -> parentName `elem` vs |] [p| (Just _, _) |])
    ==> $(rearrS [| \(Just (FamilyMember n), ns) -> (n, ns) |])$
          extract
  , $(adaptive [| \_ _ -> otherwise |])
    ==> \(_, ns) vs -> case vs \\ ns of
                         []  -> (Nothing, vs)
                         n:_ -> (Just (FamilyMember n), delete n vs)
  ]

classifyByGender :: BiGUL ([String],[String]) [(String, Bool)]
classifyByGender = Case
  [ $(normal [| \(ms, fs) vs -> sort (map (flip (,) True) ms ++ map (flip (,) False) fs) == vs |] [p| _ |])
    ==> Skip (\(ms, fs) -> sort (map (flip (,) True) ms ++ map (flip (,) False) fs))
  , $(adaptive [| \_ _ -> otherwise |])
    ==> \_ vs -> (map fst (filter snd vs), map fst (filter (not . snd) vs))
  ]

syncL :: BiGUL FamilyRegister MediumL
syncL = $(rearrS [| \(FamilyRegister fs) -> fs |])$
          align isNonEmptyFamily
                (\(Family { familyName = x }) (y, _) -> x == y)
                ($(rearrS [| \(Family familyName father mother sons daughters) ->
                               (familyName, (father, sons), (mother, daughters)) |])$
                   Replace `Prod`
                   ((((Replace `Prod` familyMemberWrapper) `Compose` promoteParent) `Prod`
                     ((Replace `Prod` familyMemberWrapper) `Compose` promoteParent)) `Compose` classifyByGender))
                (\(familyName, ((firstName, gender):_)) ->
                   let (father, mother) = (if gender then id else flip) (,) (Just (FamilyMember firstName)) Nothing
                   in  Family familyName father mother [] [])
                (const Nothing)
  where
    isNonEmptyFamily :: Family -> Bool
    isNonEmptyFamily (Family _ Nothing Nothing [] []) = False
    isNonEmptyFamily _                                = True
    familyMemberWrapper :: BiGUL [FamilyMember] [String]
    familyMemberWrapper =
      align (const True) (\_ _ -> True) ($(rearrS [| \(FamilyMember n) -> n |]) Replace) FamilyMember (const Nothing)


----------
---- main program

main :: IO ()
main = do
  (dir, familyRegister, personRegister) <- read <$> getContents
  case dir of
    "fwd" -> maybe exitFailure print (get (syncL `Compose` syncM) familyRegister >>= put syncR personRegister)
    "bwd" -> maybe exitFailure print (get syncR personRegister >>= put (syncL `Compose` syncM) familyRegister)
    _     -> exitFailure
