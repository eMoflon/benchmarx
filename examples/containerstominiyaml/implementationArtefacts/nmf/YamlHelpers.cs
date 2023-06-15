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
        [LensPut(typeof(YamlHelpers), nameof(SetScalar))]
        public static IScalar? Scalar(this IMap? map, string key)
        {
            if (map == null)
            {
                return null;
            }
            var childEntry = (map.Entries.FirstOrDefault(x => x.Key == key))?.Value as IScalar;
            if (childEntry == null) { return null; }
            return childEntry;
        }

        [LensPut(typeof(YamlHelpers), nameof(SetScalar))]
        public static IScalar? Scalar(this IMapEntry? entry, string key)
        {
            if (entry == null)
            {
                return null;
            }
            var map = entry.Value as IMap;
            return map.Scalar(key);
        }

        public static void SetScalar(this IMapEntry? entry, string key, IScalar value)
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

        public static void SetScalar(this IMap map, string key, IScalar value)
        {
            var childEntry = map.Entries.FirstOrDefault(x => x.Key == key);
            if (childEntry == null)
            {
                map.Entries.Add(new MapEntry { Key = key, Value = value });
            }
            else
            {
                childEntry.Value = value;
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

        public static IList ForceList(this IMapEntry entry, string key)
        {
            var map = entry.Value as IMap;
            if (map == null)
            {
                map = new Map();
                entry.Value = map;
            }
            var childEntry = map.Entries.FirstOrDefault(x => x.Key == key);
            var list = childEntry?.Value as IList ?? new List();
            if (childEntry == null)
            {
                map.Entries.Add(new MapEntry { Key = key, Value = list });
            }
            else
            {
                childEntry.Value = list;
            }
            return list;
        }
    }
}
