using GeneratedCode.Miniyaml;
using Hsrm.TTC23.Containers;
using NMF.Collections.ObjectModel;
using NMF.Expressions;
using NMF.Expressions.Linq;
using NMF.Synchronizations;
using System;
using System.Collections.Generic;
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
                SynchronizeLeftToRightOnly(_ => new Scalar { Value = "2.4" }, m => m.Scalar("version"));

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

                Synchronize(c => c.Image != null ? new Scalar { Value = c.Image.Image_ } : null, me => me.Scalar("image"));
                Synchronize(c => c.Replicas == 1 ? null : new Scalar { Value = AsString(c.Replicas) },
                    me => me.Scalar("replicas"));

                SynchronizeMany(SyncRule<VolumeMount2Scalar>(),
                    c => c.VolumeMounts,
                    me => me.ForceList("volumes").Values.OfType<IValue, IScalar>());
                SynchronizeMany(c => new DependsOnNameCollection(c),
                    me => new ScalarCollection(me.ForceList("depends_on")));
            }

            [LensPut(typeof(Container2MapEntry), nameof(ParseInteger))]
            public static string? AsString(int? value) => value?.ToString();

            public static int? ParseInteger(string? value)
            {
                if (value == null) return null;
                if (int.TryParse(value, out var integer)) return integer;
                return null;
            }

        }

        public class VolumeMount2Scalar : SynchronizationRule<IVolumeMount, IScalar>
        {
            public override void DeclareSynchronization()
            {
                Synchronize(vm => vm.Volume.Name + ":" + vm.Path, s => s.Value);
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
            private readonly IList _list;

            public ScalarCollection(IList list) : base(list.Values.OfType<IValue, IScalar>()
                                                           .Select(s => s.Value))
            {
                _list = list;
            }

            public override void Add(string item)
            {
                _list.Values.Add(new Scalar { Value = item });
            }

            public override void Clear()
            {
                _list.Values.Clear();
            }

            public override bool Remove(string item)
            {
                var scalar = _list.Values.AsEnumerable().FirstOrDefault(v => v is IScalar s && s.Value == item);
                if (scalar != null)
                {
                    return _list.Values.Remove(scalar);
                }
                return false;
            }
        }
    }
}
