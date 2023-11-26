package de.ubt.ai1.m2m.bxtenddsl.connectors;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.emf.Comparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.ubt.ai1.m2m.bxtenddsl.BXtendTransformation;

public class BXToolForBXtendDsl<S extends EObject, T extends EObject, D> extends BXToolForEMF<S, T, D> {	
	
	private static final String RESULTPATH = "results/BXtend";
	private final String name;
	
	public BXToolForBXtendDsl(String name,
			Supplier<? extends BXtendTransformation> trafoFactory, S srcRoot, D[] options,
			Comparator<S> src, Comparator<T> trg) {
		super(src, trg);

		this.name = name;
		this.trafoFactory = trafoFactory;
		this.srcRoot = srcRoot;
		this.options = options;
		
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void initiateSynchronisationDialogue() {
		trafo = trafoFactory.get();
		trafo.getSource().getContents().add(EcoreUtil.copy(srcRoot));
		configurator = null;
		configureTrafo();
		trafo.sourceToTarget();
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<S> edit) {
		edit.accept(getSourceModel());
		configureTrafo();
		trafo.sourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<T> edit) {
		edit.accept(getTargetModel());
		configureTrafo();
		trafo.targetToSource();
	}

	@Override
	public void performIdleSourceEdit(Consumer<S> edit) {
		edit.accept(getSourceModel());
	}

	@Override
	public void performIdleTargetEdit(Consumer<T> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void setConfigurator(Configurator<D> configurator) {
		this.configurator = configurator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public S getSourceModel() {
		return (S) trafo.getSource().getContents().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getTargetModel() {
		return (T) trafo.getTarget().getContents().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public T getCorrModel() {
		return (T) trafo.getCorr().getContents().get(0);
	}
	
	private Supplier<? extends BXtendTransformation> trafoFactory;
	private S srcRoot;
	private BXtendTransformation trafo;
	private D[] options;
	private Configurator<D> configurator;
	
	private void configureTrafo() {
		for (D option : options) {
			trafo.setOption(option.toString(), configurator != null ? configurator.decide(option) : false);
		}
	}
	
	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "container.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "yaml.xmi");
		URI corrURI = URI.createFileURI(RESULTPATH + "/" + name + "corr.xmi");
		Resource resSource = set.createResource(srcURI);
		Resource resTarget = set.createResource(trgURI);
		Resource resCorr = set.createResource(corrURI);
		
		EObject colSource = EcoreUtil.copy(getSourceModel());
		EObject colTarget = EcoreUtil.copy(getTargetModel());
		EObject colCorr = EcoreUtil.copy(getCorrModel());
		
		resSource.getContents().add(colSource);
		resTarget.getContents().add(colTarget);
		resCorr.getContents().add(colCorr);
		
		
		try {
			resSource.save(null);
			resTarget.save(null);
			resCorr.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}