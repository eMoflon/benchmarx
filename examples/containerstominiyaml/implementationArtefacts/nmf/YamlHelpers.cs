using GeneratedCode.Miniyaml;
using NMF.Collections.Generic;
using NMF.Expressions;

namespace nmf
{
    internal static class YamlHelpers
    {
        [LensPut(typeof(YamlHelpers), nameof(ApplyDefault))]
        public static T? WithDefault<T>(this T? value, T defaultValue) where T : struct
        {
            if (EqualityComparer<T>.Default.Equals(value.GetValueOrDefault(defaultValue), defaultValue))
            {
                return null;
            }
            return value;
        }

        public static T? ApplyDefault<T>(this T? value, T defaultValue, T? newValue) where T : struct
        {
            if (newValue.HasValue && EqualityComparer<T>.Default.Equals(newValue.Value, default(T)))
            {
                return defaultValue;
            }
            return newValue.GetValueOrDefault(defaultValue);
        }

        [LensPut(typeof(YamlHelpers), nameof(SetScalar))]
        public static T? Scalar<T>(this IMap? map, string key)
        {
            if (map == null)
            {
                return default;
            }
            var childEntry = (map.Entries.FirstOrDefault(x => x.Key == key))?.Value as IScalar;
            if (childEntry?.Value == null) { return default; }
            return (T)Convert.ChangeType(childEntry.Value, typeof(T));
        }

        [LensPut(typeof(YamlHelpers), nameof(SetScalar))]
        [ObservableProxy(typeof(YamlHelpers), nameof(ScalarIncremental))]
        public static T? Scalar<T>(this IMapEntry? entry, string key)
        {
            if (entry == null)
            {
                return default;
            }
            var map = entry.Value as IMap;
            if (map == null)
            {
                map = new Map();
                entry.Value = map;
            }
            return map.Scalar<T>(key);
        }

        public static INotifyValue<T?> ScalarIncremental<T>(IMapEntry entry, string key)
        {
            var map = entry.Value as IMap;
            if (map == null)
            {
                map = new Map();
                entry.Value = map;
            }
            return new ScalarExpression<T>(map, key);
        }

        private class ScalarExpression<T> : NotifyExpression<T?>
        {
            private readonly INotifyCollection<IMapEntry> _map;
            private readonly string _key;

            public ScalarExpression(IMap map, string key)
            {
                _map = map.Entries.AsNotifiable();
                _map.Successors.Set(this);
                _key = key;
            }

            public override bool IsParameterFree => true;

            public override IEnumerable<INotifiable> Dependencies
            {
                get
                {
                    yield return _map;
                }
            }

            protected override INotifyExpression<T?> ApplyParametersCore(IDictionary<string, object> parameters, IDictionary<INotifiable, INotifiable> trace)
            {
                return this;
            }

            protected override T? GetValue()
            {
                var value = (_map.AsEnumerable().FirstOrDefault(entry => entry.Key == _key)?.Value as IScalar)?.Value;
                return value == null ? default : (T)System.Convert.ChangeType(value, typeof(T));
            }
        }

        public static void SetScalar<T>(this IMapEntry? entry, string key, T? value)
        {
            if (entry == null )
            {
                throw new ArgumentNullException(nameof(entry));
            }
            var map = entry.Value as IMap;
            if (map == null)
            {
                map = new Map();
                entry.Value = map;
            }
            map.SetScalar(key, value);
        }

        public static void SetScalar<T>(this IMap map, string key, T? value)
        {
            var childEntry = map.Entries.FirstOrDefault(x => x.Key == key);
            if (value == null)
            {
                if (childEntry != null)
                {
                    map.Entries.Remove(childEntry);
                }
            }
            else
            {
                var scalar = new Scalar { Value = value.ToString() };
                if (childEntry == null)
                {
                    map.Entries.Add(new MapEntry { Key = key, Value = scalar });
                }
                else if (childEntry.Value is IScalar scal)
                {
                    scal.Value = scalar.Value;
                }
                else
                {
                    childEntry.Value = scalar;
                }
            }
        }

        public static IOrderedSetExpression<IMapEntry> ForceEntries(this IMap map, string key)
        {
            var childEntry = (map.Entries.FirstOrDefault(x => x.Key == key));
            IMap? childMap;
            if (childEntry == null)
            {
                childMap = new Map();
                childEntry = new MapEntry { Key = key, Value = childMap };
                map.Entries.Add(childEntry);
            }
            else
            {
                childMap = childEntry.Value as IMap;
                if (childMap == null)
                {
                    childMap = new Map();
                    childEntry.Value = childMap;
                }
            }
            return childMap.Entries;
        }

        public static IMap ForceMap(this IMapEntry entry)
        {
            var map = entry.Value as IMap;
            if (map == null)
            {
                map = new Map();
                entry.Value = map;
            }
            return map;
        }
    }
}
