package org.benchmarx.examples.familiestopersons.implementations.funnyqt;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.junit.ComparisonFailure;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Keyword;

/**
 * Driver for the FunnyQT solution.
 * 
 * @see <a href"https://github.com/tsdh/ttc17-families2persons-bx">The soution's
 *      source code</a>
 *
 * @author Dr. Tassilo Horn &lt;tsdh@gnu.org&gt;
 */
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
        saveModels(name, getSourceModel(), getTargetModel());
    }

    private void saveModels(String name, FamilyRegister fr, PersonRegister pr) {
        saveModel(name, fr, false);
        saveModel(name, pr, false);
    }

    private void saveModel(String name, EObject model, boolean onlyViz) {
        ResourceSet set = new ResourceSetImpl();
        set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
                Resource.Factory.Registry.DEFAULT_EXTENSION,
                new XMIResourceFactoryImpl());
        final String path = RESULTPATH + File.separator + name + "-"
                + model.eClass().getName();
        final URI uri = URI.createFileURI(path + ".xmi");
        final Resource res = set.createResource(uri);
        final EObject copy = EcoreUtil.copy(model);

        res.getContents().add(copy);

        try {
            if (!onlyViz) {
                res.save(null);
            }
            V.invoke(res, path + ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assertPostcondition(FamilyRegister source,
            PersonRegister target) {
        try {
            super.assertPostcondition(source, target);
        } catch (AssertionError e) {
            dumpModels(e, source, target, "assertPostcondition");
            throw e;
        }
    }

    @Override
    public void assertPrecondition(FamilyRegister source,
            PersonRegister target) {
        try {
            super.assertPrecondition(source, target);
        } catch (AssertionError e) {
            dumpModels(e, source, target, "assertPrecondition");
            throw e;
        }
    }

    private static Pattern TESTCASE_PATTERN = Pattern.compile(
            "^org\\.benchmarx\\.examples\\.familiestopersons\\.testsuite\\.(.*)");

    private void dumpModels(AssertionError e, FamilyRegister source,
            PersonRegister target, String where) {
        boolean onlySource = false;
        if (e instanceof ComparisonFailure) {
            ComparisonFailure cf = (ComparisonFailure) e;
            if (cf.getExpected().contains("Family")
                    || cf.getExpected().contains(" families =")
                    || cf.getExpected().contains(" father =")
                    || cf.getExpected().contains(" mother =")
                    || cf.getExpected().contains(" daughters =")
                    || cf.getExpected().contains(" sons =")) {
                onlySource = true;
            }
        }

        String testCase = "unknown_testcase";
        try {
            throw new Exception();
        } catch (Exception ex) {
            for (StackTraceElement ste : ex.getStackTrace()) {
                final String cn = ste.getClassName();
                final Matcher m = TESTCASE_PATTERN.matcher(cn);
                if (m.matches()) {
                    testCase = m.group(1) + "-" + ste.getMethodName();
                    break;
                }
            }
        }

        final String n = testCase + "-" + where + "-"
                + configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD)
                + "-"
                + configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);

        saveModel(n + "-expected", onlySource ? source : target, true);
        saveModel(n + "-actual",
                onlySource ? getSourceModel() : getTargetModel(), true);
    }
}
