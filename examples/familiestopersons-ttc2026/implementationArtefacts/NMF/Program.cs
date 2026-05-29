using NMF.Expressions;
using NMF.Models;
using NMF.Models.Changes;
using NMF.Models.Repository;
using NMF.Synchronizations;
using NMF.Transformations;
using System.Diagnostics;
using TTC2026.FamiliesToPersons.NMF.Families;
using TTC2026.FamiliesToPersons.NMF.Persons;

namespace TTC2026.FamiliesToPersons.NMF
{
    class Program
    {
        private FamilyRegister familyRegister;
        private PersonRegister personRegister;

        private ISynchronizationContext synchronizationContext;

        private ModelRepository repository = new ModelRepository();

        private FamiliesToPersonsSynchronization synchronization = new FamiliesToPersonsSynchronization();

        public Program()
        {
            //Debugger.Launch();
            var familyRootModel = new Model();
            var personRootModel = new Model();

            familyRegister = new FamilyRegister();
            personRegister = new PersonRegister();

            synchronizationContext = synchronization.Synchronize(synchronization.SynchronizationRule<FamiliesToPersonsSynchronization.FamilyRegisterToPersonRegister>(),
                ref familyRegister, ref personRegister,
                SynchronizationDirection.LeftWins, ChangePropagationMode.TwoWay);

            familyRootModel.RootElements.Add(familyRegister);
            personRootModel.RootElements.Add(personRegister);

            repository.Models.Add(new Uri("ttc:source"), familyRootModel);
            repository.Models.Add(new Uri("ttc:target"), personRootModel);
        }

        private bool AcceptCommand(string command)
        {
            // this case happens when the junit test is cancelled abnormally
            if (command == null) return false;

            var breakIndex = command.IndexOf(' ');
            if (breakIndex < 0)
            {
                if (command != "exit")
                {
                    Console.Error.WriteLine($"The command {command} is not known. Exiting synchronization.");
                }
                return false;
            }
            var verb = command.Substring(0, breakIndex);
            var argument = command.Substring(breakIndex + 1);
            switch (verb)
            {
                case "SetPreferExistingFamilies":
                    Helpers.PreferExistingFamilyToNew = bool.Parse(argument);
                    break;
                case "SetPreferCreateParents":
                    Helpers.PreferCreatingParentToChild = bool.Parse(argument);
                    break;
                case "PropagateConflicted":
                    PropagateConflict(argument);
                    break;
                case "Propagate":
                    Propagate(argument);
                    break;
                case "SaveFamilies":
                    repository.Serializer.Serialize(familyRegister, argument);
                    break;
                case "SavePersons":
                    repository.Serializer.Serialize(personRegister, argument);
                    break;
                default:
                    Console.Error.WriteLine($"The command {command} is not known. Exiting synchronization.");
                    return false;
            }
            return true;
        }

        private TimeSpan? timeOfLastPropagate;

        private void Propagate(string argument)
        {
            var changes = repository.Resolve(argument).RootElements[0] as ModelChangeSet;
            var stopwatch = new Stopwatch();
            stopwatch.Start();
            changes.Apply();
            stopwatch.Stop();
            timeOfLastPropagate = stopwatch.Elapsed;
        }

        private void PropagateConflict(string argument)
        {
            var changes = argument.Split(' ');
            var changes1 = repository.Resolve(changes[0]).RootElements[0] as ModelChangeSet;
            var changes2 = repository.Resolve(changes[1]).RootElements[0] as ModelChangeSet;
            var stopwatch = new Stopwatch();
            stopwatch.Start();
            ExecutionEngine.Current.BeginTransaction();
            synchronizationContext.ForceAdditions = false;
            changes1.Apply();
            changes2.Apply();
            ExecutionEngine.Current.CommitTransaction();
            synchronizationContext.ForceAdditions = true;
            stopwatch.Stop();
            timeOfLastPropagate = stopwatch.Elapsed;
        }

        static void Main(string[] args)
        {
            var program = new Program();
            var command = Console.In.ReadLine();
            while (program.AcceptCommand(command))
            {
                if (program.timeOfLastPropagate.HasValue)
                {
                    Console.Out.WriteLine(program.timeOfLastPropagate.Value.Ticks * 100);
                    program.timeOfLastPropagate = null;
                }
                else
                {
                    Console.Out.WriteLine("OK");
                }
                command = Console.In.ReadLine();
            }
            Console.WriteLine("Bye");
        }
    }
}
