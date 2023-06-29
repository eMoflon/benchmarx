using GeneratedCode.Miniyaml;
using Hsrm.TTC23.Containers;
using NMF.Collections.Generic;
using NMF.Expressions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
