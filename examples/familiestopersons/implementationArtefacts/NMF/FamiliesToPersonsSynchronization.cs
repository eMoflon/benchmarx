using NMF.Collections.ObjectModel;
using NMF.Expressions;
using NMF.Expressions.Linq;
using NMF.Models;
using NMF.Models.Meta;
using NMF.Synchronizations;
using System.Collections.Generic;
using System.Linq;
using TTC2017.FamiliesToPersons.NMF.Families;
using TTC2017.FamiliesToPersons.NMF.Persons;

namespace TTC2017.FamiliesToPersons.NMF
{
    public class FamiliesToPersonsSynchronization : ReflectiveSynchronization
    {
        public class FamilyRegisterToPersonRegister : SynchronizationRule<FamilyRegister, PersonRegister>
        {
            public override void DeclareSynchronization()
            {
                SynchronizeMany(SyncRule<MemberToMember>(),
                    fam => new FamilyMemberCollection(fam),
                    persons => persons.Persons);
            }
        }

        public class MemberToMember : SynchronizationRule<IFamilyMember, IPerson>
        {
            public override void DeclareSynchronization()
            {
                Synchronize(m => m.GetFullName(), p => p.Name);
            }
        }

        public class MemberToMale : SynchronizationRule<IFamilyMember, IMale>
        {
            public override void DeclareSynchronization()
            {
                MarkInstantiatingFor(SyncRule<MemberToMember>(), leftPredicate: m => m.FatherInverse != null || m.SonsInverse != null);
            }

            protected override IFamilyMember CreateLeftOutput(IMale input, IEnumerable<IFamilyMember> candidates, ISynchronizationContext context, out bool existing)
            {
                var member = base.CreateLeftOutput(input, candidates, context, out existing);
                member.Extensions.Add(new TemporaryStereotype(member)
                {
                    IsMale = true,
                    LastName = input.Name.Substring(0, input.Name.IndexOf(','))
                });
                return member;
            }
        }

        public class MemberToFemale : SynchronizationRule<IFamilyMember, IFemale>
        {
            public override void DeclareSynchronization()
            {
                MarkInstantiatingFor(SyncRule<MemberToMember>(), leftPredicate: m => m.MotherInverse != null || m.DaughtersInverse != null);
            }

            protected override IFamilyMember CreateLeftOutput(IFemale input, IEnumerable<IFamilyMember> candidates, ISynchronizationContext context, out bool existing)
            {
                var member = base.CreateLeftOutput(input, candidates, context, out existing);
                member.Extensions.Add(new TemporaryStereotype(member)
                {
                    IsMale = false,
                    LastName = input.Name.Substring(0, input.Name.IndexOf(','))
                });
                return member;
            }
        }

        private class FamilyMemberCollection : CustomCollection<IFamilyMember>
        {
            public FamilyRegister Register { get; private set; }

            public FamilyMemberCollection(FamilyRegister register)
                : base(register.Families.SelectMany(fam => fam.Children.OfType<IFamilyMember>()))
            {
                Register = register;
            }

            public override void Add(IFamilyMember item)
            {
                var temp = item.GetExtension<TemporaryStereotype>();
                item.AddToFamily(Register, temp.IsMale, temp.LastName);
                item.Extensions.Remove(temp);
            }

            public override void Clear()
            {
                Register.Families.Clear();
            }

            public override bool Remove(IFamilyMember item)
            {
                item.Delete();
                return true;
            }
        }
    }

    public class TemporaryStereotype : ModelElementExtension<IFamilyMember>
    {
        public bool IsMale { get; set; }

        public string LastName { get; set; }

        public TemporaryStereotype(IFamilyMember parent) : base(parent) { }

        public override IExtension GetExtension() { return null; }
    }

    public static class Helpers
    {
        public static bool PreferCreatingParentToChild = true;
        public static bool PreferExistingFamilyToNew = true;

        private static ObservingFunc<IFamilyMember, string> fullName = new ObservingFunc<IFamilyMember, string>(
            m => m.Name == null ? null : ((IFamily)m.Parent).Name + ", " + m.Name);
        
        [LensPut(typeof(Helpers), "SetFullName")]
        [ObservableProxy(typeof(Helpers), "GetFullNameInc")]
        public static string GetFullName(this IFamilyMember member)
        {
            return fullName.Evaluate(member);
        }

        public static INotifyValue<string> GetFullNameInc(this IFamilyMember member)
        {
            return fullName.Observe(member);
        }

        public static void AddToFamily(this IFamilyMember item, IFamilyRegister register, bool isMale, string name)
        {
            IFamily family = null;
            if (PreferExistingFamilyToNew)
            {
                IEnumerable<IFamily> candidateFamilies = register.Families.AsEnumerable().Where(fam => fam.Name == name);
                if (PreferCreatingParentToChild)
                {
                    if (isMale)
                    {
                        family = candidateFamilies.Where(fam => fam.Father == null).FirstOrDefault();
                    }
                    else
                    {
                        family = candidateFamilies.Where(fam => fam.Mother == null).FirstOrDefault();
                    }
                }
                family = family ?? candidateFamilies.FirstOrDefault();
            }
            if (family == null)
            {
                family = new Family { Name = name };
                register.Families.Add(family);
            }
            if (isMale)
            {
                if (family.Father == null && PreferCreatingParentToChild)
                {
                    family.Father = item;
                }
                else
                {
                    family.Sons.Add(item);
                }
            }
            else
            {
                if (family.Mother == null && PreferCreatingParentToChild)
                {
                    family.Mother = item;
                }
                else
                {
                    family.Daughters.Add(item);
                }
            }
        }

        public static void SetFullName(this IFamilyMember member, string newName)
        {
            var family = member.Parent as IFamily;
            var separator = newName.IndexOf(", ");
            var lastName = newName.Substring(0, separator);
            var firstName = newName.Substring(separator + 2);
            member.Name = firstName;
            if (family != null && family.Name != lastName)
            {
                var isMale = member.FatherInverse != null || member.SonsInverse != null;
                member.AddToFamily(family.FamiliesInverse, isMale, lastName);
            }
        }
    }
}
