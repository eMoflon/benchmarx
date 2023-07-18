using GeneratedCode.Miniyaml;
using Hsrm.TTC23.Containers;
using NMF.Collections.ObjectModel;
using NMF.Expressions;
using NMF.Expressions.Linq;
using NMF.Synchronizations;

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
                    c => new ServicesCollection(c),
                    m => m.ForceEntries("services"));
                SynchronizeMany(SyncRule<Volume2MapEntry>(),
                    c => c.Nodes.OfType<INode, IVolume>(),
                    m => m.ForceEntries("volumes"));
            }

            private class ServicesCollection : CustomCollection<IContainer>
            {
                private readonly Composition _comp;

                public ServicesCollection(Composition comp)
                    : base(comp.Nodes.OfType<IContainer>())
                {
                    _comp = comp;
                }

                public override void Add(IContainer item)
                {
                    _comp.Nodes.Add(item);
                    if (item.Image != null)
                    {
                        var existingImage = _comp.Nodes.OfType<IImage>().SingleOrDefault(i => i.Image_ == item.Image.Image_);
                        if (existingImage != null)
                        {
                            item.Image = existingImage;
                        }
                        else
                        {
                            _comp.Nodes.Add(item.Image);
                        }
                    }
                }

                public override void Clear()
                {
                    foreach (var item in _comp.Nodes.OfType<IContainer>().ToList())
                    {
                        _comp.Nodes.Remove(item);
                    }
                }

                public override bool Remove(IContainer item)
                {
                    return _comp.Nodes.Remove(item);
                }
            }
        }

        public class Container2MapEntry : SynchronizationRule<IContainer, IMapEntry>
        {
            private static readonly ObservingFunc<IContainer, string?> _getImage = 
                ObservingFunc<IContainer, string?>.FromExpression(c => c.Image != null ? c.Image.Image_ : null);

            public override void DeclareSynchronization()
            {
                Synchronize(c => c.Name, me => me.Key);

                Synchronize(c => GetImage(c), me => me.Scalar<string>("image"));
                Synchronize(c => c.Replicas.WithDefault(1), me => me.Scalar<int>("replicas"));

                SynchronizeMany(
                    c => new VolumeMountCollection(c),
                    me => new ScalarCollection(me, "volumes"));
                SynchronizeMany(c => new DependsOnNameCollection(c),
                    me => new ScalarCollection(me, "depends_on"));
            }

            [ObservableProxy(typeof(Container2MapEntry), nameof(GetImageIncremental))]
            [LensPut(typeof(Container2MapEntry), nameof(SetImage))]
            public static string? GetImage(IContainer container) => _getImage.Evaluate(container);

            public static INotifyValue<string?> GetImageIncremental(IContainer container) => _getImage.Observe(container);

            public static void SetImage(IContainer container, string image)
            {
                if (image == null)
                {
                    container.Image = null;
                }
                else if (container.Image?.Image_ != image)
                {
                    IImage imageElement = null;
                    if (container.Parent is Composition composition)
                    {
                        imageElement = composition.Nodes.OfType<IImage>().SingleOrDefault(i => i.Image_ == image);
                        if (imageElement == null)
                        {
                            imageElement = new Image { Image_ = image };
                            composition.Nodes.Add(imageElement);
                        }
                    }
                    else
                    {
                        imageElement = new Image { Image_ = image };
                    }
                    container.Image = imageElement;
                }
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

                public ScalarCollection(IMapEntry entry, string key)
                    : this(entry.ForceMap(), key) { }

                public ScalarCollection(IMap map, string key)
                    : base(map.Entries
                              .Where(e => e.Key == key && e.Value is IList)
                              .SelectMany(e => ((IList)e.Value).Values.OfType<IScalar>())
                              .Select(s => s.Value))
                {
                    _map = map;
                    _key = key;
                }

                public override void Add(string item)
                {
                    var entry = _map.Entries.AsEnumerable().FirstOrDefault(e => e.Key == _key);
                    if (entry == null)
                    {
                        entry = new MapEntry { Key = _key, Value = new List() };
                        _map.Entries.Add(entry);
                    }
                    ((IList)entry.Value).Values.Add(new Scalar { Value = item });
                }

                public override void Clear()
                {
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
                    var entry = _map.Entries.AsEnumerable().FirstOrDefault(e => e.Key == _key);
                    if (entry != null && entry.Value is IList vals)
                    {
                        var scalar = vals.Values.AsEnumerable().FirstOrDefault(v => v is IScalar s && s.Value == item);
                        if (scalar != null)
                        {
                            var ret = vals.Values.Remove(scalar);
                            if (vals.Values.Count == 0)
                            {
                                _map.Entries.Remove(entry);
                            }
                            return ret;
                        }
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
