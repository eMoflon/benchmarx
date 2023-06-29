using GeneratedCode.Miniyaml;
using Hsrm.TTC23.Containers;
using NMF.Collections.ObjectModel;
using NMF.Expressions;
using NMF.Expressions.Linq;
using NMF.Synchronizations;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace nmf
{
    internal class ContainersToMiniYaml : ReflectiveSynchronization
    {
        public class MainMap : SynchronizationRule<Composition, Map>
        {
            public override void DeclareSynchronization()
            {
                SynchronizeLeftToRightOnly(_ => "2.4", m => m.Scalar<string>("version"));

                SynchronizeMany(SyncRule<Container2MapEntry>(),
                    c => c.Nodes.OfType<INode, IContainer>(),
                    m => m.ForceEntries("services"));
                SynchronizeMany(SyncRule<Volume2MapEntry>(),
                    c => c.Nodes.OfType<INode, IVolume>(),
                    m => m.ForceEntries("volumes"));
            }
        }

        public class Container2MapEntry : SynchronizationRule<IContainer, IMapEntry>
        {
            public override void DeclareSynchronization()
            {
                Synchronize(c => c.Name, me => me.Key);

                Synchronize(c => c.Image.Image_, me => me.Scalar<string>("image"));
                Synchronize(c => c.Replicas.WithDefault(1), me => me.Scalar<int?>("replicas"));

                SynchronizeMany(
                    c => new VolumeMountCollection(c),
                    me => new ScalarCollection(me, "volumes"));
                SynchronizeMany(c => new DependsOnNameCollection(c),
                    me => new ScalarCollection(me, "depends_on"));
            }

            protected override IContainer CreateLeftOutput(IMapEntry input, IEnumerable<IContainer> candidates, ISynchronizationContext context, out bool existing)
            {
                existing = false;
                return new Container { Image = new Image() };
            }

            [LensPut(typeof(Container2MapEntry), nameof(ParseInteger))]
            public static string? AsString(int? value) => value?.ToString();

            public static int? ParseInteger(int? before, string? value)
            {
                if (value == null) return null;
                if (int.TryParse(value, out var integer)) return integer;
                return before;
            }

            private class VolumeMountCollection : CustomCollection<string>
            {
                private readonly IContainer _container;

                public VolumeMountCollection(IContainer container)
                    : base(container.VolumeMounts.Select(vm => $"{vm.Volume.Name}:{vm.Path}"))
                {
                    _container = container;
                }

                public override void Add(string item)
                {
                    var composition = (IComposition)_container.Parent;
                    var sep = item.IndexOf(':');
                    var volume = composition.Nodes.OfType<IVolume>()
                                                  .SingleOrDefault(v => v.Name == item.Substring(0, sep));
                    _container.VolumeMounts.Add(new VolumeMount
                    {
                        Volume = volume,
                        Path = item.Substring(sep + 1)
                    });
                }

                public override void Clear()
                {
                    _container.VolumeMounts.Clear();
                }

                public override bool Remove(string item)
                {
                    var mount = _container.VolumeMounts.AsEnumerable().FirstOrDefault(vm => item.StartsWith(vm.Volume.Name) && item.EndsWith(vm.Path));
                    return _container.VolumeMounts.Remove(mount);
                }
            }

            private class DependsOnNameCollection : CustomCollection<string>
            {
                private readonly IContainer _container;

                public DependsOnNameCollection(IContainer container) :
                    base(container.DependsOn.Select(c => c.Name))
                {
                    _container = container;
                }

                public override void Add(string item)
                {
                    var composition = (IComposition)_container.Parent;
                    var container = composition.Nodes.OfType<IContainer>().First(c => c.Name == item);
                    _container.DependsOn.Add(container);
                }

                public override void Clear()
                {
                    _container.DependsOn.Clear();
                }

                public override bool Remove(string item)
                {
                    var dependentContainer = _container.DependsOn.AsEnumerable().FirstOrDefault(c => c.Name == item);
                    if (dependentContainer != null)
                    {
                        return _container.DependsOn.Remove(dependentContainer);
                    }
                    return false;
                }
            }

            private class ScalarCollection : CustomCollection<string>
            {
                private readonly IMap _map;
                private readonly string _key;
                private readonly IList _list;

                public ScalarCollection(IMapEntry entry, string key)
                    : this(entry.ForceMap(), key) { }

                public ScalarCollection(IMap map, string key)
                    : this(map.Entries.AsEnumerable().FirstOrDefault(e => e.Key == key)?.Value as IList ?? new List(), map, key)
                {
                }

                private ScalarCollection(IList list, IMap map, string key)
                    : base(list.Values.OfType<IValue, IScalar>()
                               .Select(s => s.Value))
                {
                    _list = list;
                    _map = map;
                    _key = key;
                }

                public override void Add(string item)
                {
                    if (_list.Values.Count == 0)
                    {
                        _map.Entries.Add(new MapEntry
                        {
                            Key = _key,
                            Value = _list
                        });
                    }
                    _list.Values.Add(new Scalar { Value = item });
                }

                public override void Clear()
                {
                    _list.Values.Clear();
                    RemoveEntry();
                }

                private void RemoveEntry()
                {
                    var entry = _map.Entries.AsEnumerable().FirstOrDefault(e => e.Key == _key);
                    if (entry != null)
                    {
                        _map.Entries.Remove(entry);
                    }
                }

                public override bool Remove(string item)
                {
                    var scalar = _list.Values.AsEnumerable().FirstOrDefault(v => v is IScalar s && s.Value == item);
                    if (scalar != null)
                    {
                        var ret = _list.Values.Remove(scalar);
                        if (_list.Values.Count == 0)
                        {
                            RemoveEntry();
                        }
                        return ret;
                    }
                    return false;
                }
            }
        }

        public class Volume2MapEntry : SynchronizationRule<IVolume, IMapEntry>
        {
            public override void DeclareSynchronization()
            {
                Synchronize(v => v.Name, m => m.Key);
                // leave value empty
            }
        }
    }
}
