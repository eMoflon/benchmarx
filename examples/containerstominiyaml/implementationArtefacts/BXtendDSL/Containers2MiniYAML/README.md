# BXtendDSL Examples for TTC 2023

## Containers2MiniYAML
  
At the moment, the only way to make the backward transformation pass the tests is to change the following lines in the generated Xtend class
Composition2Map, in the method targetToSource

```xtend
m.getEntries().forEach[corr.assertRuleId("Composition2Map", "Image2MapEntry", "Container2MapEntry", "Volume2MapEntry")]
			val _entImaImg = m.getEntries()
					.filter[corr.ruleId == "Image2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Image]
					.toList()
			val _entConC = m.getEntries()
					.filter[corr.ruleId == "Container2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Container]
					.toList()
			val _entVolV = m.getEntries()
					.filter[corr.ruleId == "Volume2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Volume]
					.toList()
```

to

```xtend
//m.getEntries().forEach[corr.assertRuleId("Composition2Map", "Image2MapEntry", "Container2MapEntry", "Volume2MapEntry")]
			val _entImaImg = m.getEntries().filter(typeof(miniyaml.MapEntry)).filter[me | me.key != "version" && me.key != "volumes" && me.key != "services"]
					.filter[corr.ruleId == "Image2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Image]
					.toList()
			val _entConC = m.getEntries().filter(typeof(miniyaml.MapEntry)).filter[me | me.key != "version" && me.key != "volumes" && me.key != "services"]
					.filter[corr.ruleId == "Container2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Container]
					.toList()
			val _entVolV = m.getEntries().filter(typeof(miniyaml.MapEntry)).filter[me | me.key != "version" && me.key != "volumes" && me.key != "services"]
					.filter[corr.ruleId == "Volume2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Volume]
					.toList()
```

The following changes in Container2MapEntry are required:

line 43: ``val _value = valueFrom(c.getImage(), c.getReplicas(), c.getDependsOn(), _volSc, me.value)``

line 85: ``def protected abstract Type4value valueFrom(containers.Image image, int replicas, List<containers.Container> dependsOn, List<miniyaml.Scalar> volSc, miniyaml.Value oldValue);``