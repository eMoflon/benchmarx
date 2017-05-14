package org.benchmarx.examples.familiestopersons.implementations.funnyqt;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Keyword;

public class FunnyQTFamiliesToPerson
        extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {

    private static final String RESULTPATH = "results/funnyqt";

    private Resource srcModel;
    private Resource trgModel;

    private Configurator<Decisions> configurator;

    private final static IFn T;
    private final static IFn V;
    private final static Keyword LEFT;
    private final static Keyword RIGHT;

    static {
        LEFT = (Keyword) Clojure.read(":left");
        RIGHT = (Keyword) Clojure.read(":right");

        final String nsName = "ttc17-families2persons-bx.core";
        final String transName = "families2persons";

        final IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read(nsName));

        T = Clojure.var(nsName, transName);
        V = Clojure.var("funnyqt.visualization", "print-model");
    }

    public FunnyQTFamiliesToPerson() {
        this(new FamiliesComparator(), new PersonsComparator());
    }

    public FunnyQTFamiliesToPerson(Comparator<FamilyRegister> src,
            Comparator<PersonRegister> trg) {
        super(src, trg);
    }

    private void transform(Keyword direction) {
        System.out.println("Transforming in direction " + direction
                + "\n  with PREFER_CREATING_PARENT_TO_CHILD = "
                + configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD)
                + "\n  and PREFER_EXISTING_FAMILY_TO_NEW = "
                + configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW));
        T.invoke(srcModel, trgModel, direction,
                configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD),
                configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW));
    }

    @Override
    public String getName() {
        return "FunnyQT";
    }

    @Override
    public void initiateSynchronisationDialogue() {
        srcModel = new ResourceImpl();
        trgModel = new ResourceImpl();

        FamilyRegister familiesRoot = FamiliesFactory.eINSTANCE
                .createFamilyRegister();
        srcModel.getContents().add(familiesRoot);

        setConfigurator(new Configurator<Decisions>()
                .makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
                .makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));

        transform(RIGHT);
    }

    @Override
    public void performAndPropagateSourceEdit(Consumer<FamilyRegister> edit) {
        edit.accept(getSourceModel());
        transform(RIGHT);
    }

    @Override
    public void performAndPropagateTargetEdit(Consumer<PersonRegister> edit) {
        edit.accept(getTargetModel());
        transform(LEFT);
    }

    @Override
    public void performIdleSourceEdit(Consumer<FamilyRegister> edit) {
        edit.accept(getSourceModel());
    }

    @Override
    public void performIdleTargetEdit(Consumer<PersonRegister> edit) {
        edit.accept(getTargetModel());
    }

    @Override
    public void setConfigurator(Configurator<Decisions> configurator) {
        this.configurator = configurator;
    }

    @Override
    public FamilyRegister getSourceModel() {
        return (FamilyRegister) srcModel.getContents().get(0);
    }

    @Override
    public PersonRegister getTargetModel() {
        return (PersonRegister) trgModel.getContents().get(0);
    }

    @Override
    public void saveModels(String name) {
        ResourceSet set = new ResourceSetImpl();
        set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
                Resource.Factory.Registry.DEFAULT_EXTENSION,
                new XMIResourceFactoryImpl());
        final String srcPath = RESULTPATH + File.separator + name + "Family";
        final String trgPath = RESULTPATH + File.separator + name + "Person";
        final URI srcURI = URI.createFileURI(srcPath + ".xmi");
        final URI trgURI = URI.createFileURI(trgPath + ".xmi");
        final Resource resSource = set.createResource(srcURI);
        final Resource resTarget = set.createResource(trgURI);

        final EObject colSource = EcoreUtil.copy(getSourceModel());
        final EObject colTarget = EcoreUtil.copy(getTargetModel());

        resSource.getContents().add(colSource);
        resTarget.getContents().add(colTarget);

        try {
            resSource.save(null);
            V.invoke(resSource, srcPath + ".pdf");
            resTarget.save(null);
            V.invoke(resTarget, trgPath + ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
