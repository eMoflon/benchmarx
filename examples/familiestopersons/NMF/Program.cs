using NMF.Synchronizations;
using NMF.Transformations;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TTC2017.FamiliesToPersons.NMF.Families;
using TTC2017.FamiliesToPersons.NMF.Persons;

namespace TTC2017.FamiliesToPersons.NMF
{
    class Program
    {
        static void Main(string[] args)
        {
            var familyRegister = new FamilyRegister();
            var personsRegister = new PersonRegister();

            var familesToPersons = new FamiliesToPersonsSynchronization();
            FamiliesToPersonsSynchronization.PreferCreatingParentToChild = false;

            familesToPersons.Synchronize(familesToPersons.SynchronizationRule<FamiliesToPersonsSynchronization.FamilyRegisterToPersonRegister>(),
                ref familyRegister, ref personsRegister,
                SynchronizationDirection.LeftWins, ChangePropagationMode.TwoWay);

            //AddFamily(familyRegister);
            AddPersons(personsRegister);
        }

        private static void AddFamily(FamilyRegister family)
        {
            var homer = new FamilyMember { Name = "Homer" };
            var marge = new FamilyMember { Name = "Marge" };
            var bart = new FamilyMember { Name = "Bart" };
            var lisa = new FamilyMember { Name = "Lisa" };
            var maggie = new FamilyMember { Name = "Maggie" };
            var simpsons = new Family
            {
                Name = "Simpson",
                Father = homer,
                Mother = marge
            };
            simpsons.Sons.Add(bart);
            simpsons.Daughters.Add(lisa);
            simpsons.Daughters.Add(maggie);
            family.Families.Add(simpsons);
        }

        private static void AddPersons(PersonRegister persons)
        {
            var homer = new Male { Name = "Simpson, Homer" };
            var marge = new Female { Name = "Simpson, Marge" };
            var bart = new Male { Name = "Simpson, Bart" };
            var lisa = new Female { Name = "Simpson, Lisa" };
            var maggie = new Female { Name = "Simpson, Maggie" };

            persons.Persons.Add(homer);
            persons.Persons.Add(marge);
            persons.Persons.Add(bart);
            persons.Persons.Add(lisa);
            persons.Persons.Add(maggie);
        }
    }
}
