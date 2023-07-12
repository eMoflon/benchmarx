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

class MiniYAML2Containers extends YAMTLModule {
	public MiniYAML2Containers(MiniyamlPackage YMM, ContainersPackage CMM) {
		
		header().in('ymm',YMM).out('cmm',CMM)
		
		ruleStore([
			
			rule('MainMap2Composition')
				.in('m', YMM.map).filter{ m.eContainer() == null }
				.out('c', CMM.composition),
			
			rule('MapEntry2Container')
			.in('m', YMM.mapEntry)
				.filter { m.eContainer()?.eContainer()?.key == 'services' }
			.out('c', CMM.container, { 
				c.name = m.key 
				addToRoot(m, c)
				
				def image = getFieldValue(m, 'image')
				if (image) 
					c.image = fetch(image, 'im', 'Scalar2Image', ['root':  m.eContainer().eContainer().eContainer() ])
					
					
				def replicas = getFieldValue(m, 'replicas')?.value 
				if (replicas) c.replicas = (replicas as int)
				
				def volumes = getFieldValue(m, 'volumes')?.values
				if (volumes) 
					c.volumeMounts += fetch(volumes, 'vm', 'Scalar2VolumeMount')
				
				def dependsOnItem = getFieldValue(m, 'depends_on')?.values?.value
				if (dependsOnItem) {
					dependsOnItem.each{ depName ->
						def list= fetch(allInstances(YMM.mapEntry)).findAll{it instanceof Container}
						def dep = list.find{ it.name==depName}
						if (dep) c.dependsOn.add(dep)
					}
				} 
			}),
			
			rule('Scalar2Image').isUniqueLazy()
			.in('s', YMM.scalar)
			.out('im', CMM.image, { 
				im.image = s.value
				def c = fetch(root)
				c.nodes.add( im )
				
			}),
			
			rule('Scalar2VolumeMount').isUniqueLazy()
			.in('s', YMM.scalar).filter { s.value.contains(':') }
			.out('vm', CMM.volumeMount, { 
				def parts = s.value.split(':')
				vm.volume = fetch(allInstances(YMM.mapEntry))
					.findAll{ it instanceof Volume }
					.find{ v -> v.name==parts[0]}
				vm.path = parts[1];
			}),
			
			rule('MapEntry2Volume')
			.in('m', YMM.mapEntry).filter { m.eContainer()?.eContainer()?.key == 'volumes' }
			.out('v', CMM.volume, { 
				v.name = m.key
				addToRoot(m, v) 
			})
			
		])
	}
	
	/*
	 * HELPERS
	 */
	def YFactory = MiniyamlFactory.eINSTANCE;
	
	def getFieldValue(m, key) {
		 m.value?.entries?.find{ me -> me.key==key }?.value
	}
	
	def addToRoot(miniyaml.MapEntry me, containers.Node n) {
		def root = fetch(me.eContainer().eContainer().eContainer())
		root.nodes.add(n)
	}
	
}
