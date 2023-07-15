package containerstominiyaml.yamtl

import static yamtl.dsl.Rule.rule;

import containers.Container
import containers.ContainersPackage
import containers.Volume
import miniyaml.MiniyamlFactory
import miniyaml.MiniyamlPackage
import yamtl.core.YAMTLModule
import yamtl.core.YAMTLModule.ExecutionMode
import yamtl.groovy.YAMTLGroovyExtensions

class YAMTLContainersToMiniYAML extends YAMTLModule {
	public YAMTLContainersToMiniYAML(ContainersPackage CMM, MiniyamlPackage YMM) {
		
		header().in('cmm',CMM).out('ymm',YMM)
		
		ruleStore([
			
			rule('Composition2MainMap')
				.in('c', CMM.composition)
				.out('m', YMM.map, {
					m.entries.add( mapEntry('version', scalar('2.4')) )
					m.entries.add( mapEntry('services', map( fetch(c.getNodes().findAll{it instanceof Container}) ))	)
					m.entries.add( mapEntry('volumes', map( fetch(c.getNodes().findAll{it instanceof Volume}) )) )
				}),
			
			rule('Container2MapEntry')
				.in('cn', CMM.container)
				.out('meContainer', YMM.mapEntry, {
					def cn = fetch('cn')
							def meContainer = fetch('meContainer')
					meContainer.key = cn.name
					meContainer.value = map
				})
				.out('map', YMM.map, {
					def cn = fetch('cn')
							def map = fetch('map')
					if (cn.image) 
						map.getEntries().add( mapEntry('image', scalar(cn.image.image)) )
					if (cn.replicas != 1) 
						map.getEntries().add( mapEntry('replicas', scalar(cn.replicas.toString())) )
					if (cn.volumeMounts)
						map.getEntries().add( mapEntry('volumes', list(fetch(cn.volumeMounts))) )
					if (cn.dependsOn)
						map.getEntries().add( mapEntry('depends_on', list(  cn.dependsOn.collect{scalar(it.name)} )) )
				}),
			
			rule('VolumeMount2Scalar')
			.in('vm',CMM.volumeMount)
			.out('s', YMM.scalar, {
				s.value = "${vm.volume.name}:${vm.path}"
			}),
			
			rule('Volume2MapEntry')
			.in('v',CMM.volume)
			.out('me', YMM.mapEntry, {
				me.key = v.name
			})
		])
	}
	
	/*
	 * HELPERS
	 */
	def YFactory = MiniyamlFactory.eINSTANCE;
	
	def scalar(String text) {
		def sc = YFactory.createScalar()
		sc.value = text
		sc
	}
	def map(entries) {
		def map = YFactory.createMap()
		map.entries += entries
		map
	}
	def mapEntry(key,value) {
		def me = YFactory.createMapEntry()
		me.key = key
		me.value = value
		me
	}
	def list(values) {
		def map = YFactory.createList()
		map.values += values
		map
	}
}
