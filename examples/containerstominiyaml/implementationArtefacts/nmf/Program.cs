using GeneratedCode.Miniyaml;
using Hsrm.TTC23.Containers;
using nmf;
using NMF.Models;
using NMF.Models.Changes;
using NMF.Models.Repository;
using NMF.Synchronizations;
using NMF.Transformations;
using System.Diagnostics;

//Debugger.Launch();

ModelRepository repository = new ModelRepository();
var synchronization = new ContainersToMiniYaml();

var composition = new Composition();
var map = new Map();

synchronization.Synchronize(synchronization.SynchronizationRule<ContainersToMiniYaml.MainMap>(),
    ref composition, ref map,
    SynchronizationDirection.LeftWins, ChangePropagationMode.TwoWay);

var compositionRootModel = new Model { RootElements = { composition } };
var miniYamlModel = new Model { RootElements = { map } };

repository.Models.Add(new Uri("ttc:source"), compositionRootModel);
repository.Models.Add(new Uri("ttc:target"), miniYamlModel);

TimeSpan Propagate(string argument)
{
    var changes = (ModelChangeSet)repository.Resolve(argument).RootElements[0]; ;
    var stopwatch = new Stopwatch();
    stopwatch.Start();
    changes.Apply();
    stopwatch.Stop();
    return stopwatch.Elapsed;
}

bool AcceptCommand(string? command)
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
        case "Propagate":
            var time = Propagate(argument);
            Console.Out.WriteLine(time.Ticks * 100);
            break;
        case "SaveContainer":
            repository.Serializer.Serialize(compositionRootModel, argument);
            Console.Out.WriteLine("ok");
            break;
        case "SaveYaml":
            repository.Serializer.Serialize(miniYamlModel, argument);
            Console.Out.WriteLine("ok");
            break;
        default:
            Console.Error.WriteLine($"The command {command} is not known. Exiting synchronization.");
            return false;
    }
    return true;
}

var command = Console.In.ReadLine();
while (AcceptCommand(command))
{
    command = Console.In.ReadLine();
}
Console.WriteLine("Bye");