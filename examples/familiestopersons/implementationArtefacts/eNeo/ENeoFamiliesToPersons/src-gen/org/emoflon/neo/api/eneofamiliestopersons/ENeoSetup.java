package org.emoflon.neo.api.eneofamiliestopersons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.URIConverter;

/**
 * Helper to dynamically extract bundled EMSL models and configure EMF platform
 * mappings for standalone/headless execution of eNeo.
 */
public class ENeoSetup {

	private static File baseDir;
	private static File pDir;
	private static File nDir;
	private static boolean initialized = false;

	public static synchronized void init() {
		if (initialized) {
			return;
		}
		try {
			baseDir = new File(System.getProperty("java.io.tmpdir"), "benchmarx_eneo_workspace");
			pDir = new File(baseDir, "EneoFamiliesToPersons");
			nDir = new File(baseDir, "org.emoflon.neo.neocore");

			baseDir.mkdirs();
			pDir.mkdirs();
			nDir.mkdirs();

			writeProjectFile(pDir, "EneoFamiliesToPersons");
			writeProjectFile(nDir, "org.emoflon.neo.neocore");

			// Extract specification models
			extractResource("org/benchmarx/eneo/f2p/Families.msl",
					new File(pDir, "src/org/benchmarx/eneo/f2p/Families.msl"),
					new File(pDir, "bin/org/benchmarx/eneo/f2p/Families.msl"));
			extractResource("org/benchmarx/eneo/f2p/Persons.msl",
					new File(pDir, "src/org/benchmarx/eneo/f2p/Persons.msl"),
					new File(pDir, "bin/org/benchmarx/eneo/f2p/Persons.msl"));
			extractResource("org/benchmarx/eneo/f2p/FamiliesToPersons.msl",
					new File(pDir, "src/org/benchmarx/eneo/f2p/FamiliesToPersons.msl"),
					new File(pDir, "bin/org/benchmarx/eneo/f2p/FamiliesToPersons.msl"));

			// Extract TGG models
			String[] tggModels = {
					"F2P_BWD", "F2P_BWD_OPT", "F2P_CC", "F2P_CO",
					"F2P_FWD", "F2P_FWD_OPT", "F2P_GEN", "F2P_MI"
			};
			for (String tgg : tggModels) {
				String resName = "org/benchmarx/eneo/f2p/tgg/" + tgg + ".msl";
				extractResource(resName,
						new File(pDir, "tgg-gen/org/benchmarx/eneo/f2p/tgg/" + tgg + ".msl"),
						new File(pDir, "bin/org/benchmarx/eneo/f2p/tgg/" + tgg + ".msl"),
						new File(pDir, "src/org/benchmarx/eneo/f2p/tgg/" + tgg + ".msl"));
			}

			// Extract neocore models
			String[] neoModels = {
					"AttributeConstraintsLibrary.msl", "NeoCore.msl", "RulesForImporter.msl"
			};
			for (String neo : neoModels) {
				extractResource(neo,
						new File(nDir, "model/" + neo),
						new File(nDir, "bin/" + neo),
						new File(nDir, neo));
			}

			URI pURI = URI.createFileURI(pDir.getAbsolutePath() + "/");
			URI nURI = URI.createFileURI(nDir.getAbsolutePath() + "/");
			URI bURI = URI.createFileURI(baseDir.getAbsolutePath() + "/");

			EcorePlugin.getPlatformResourceMap().put("EneoFamiliesToPersons", pURI);
			EcorePlugin.getPlatformResourceMap().put("org.emoflon.neo.neocore", nURI);

			URIConverter.URI_MAP.put(URI.createURI("platform:/plugin/org.emoflon.neo.neocore/"), nURI);
			URIConverter.URI_MAP.put(URI.createURI("platform:/resource/EneoFamiliesToPersons/"), pURI);
			URIConverter.URI_MAP.put(URI.createURI("platform:/resource/org.emoflon.neo.neocore/"), nURI);
			URIConverter.URI_MAP.put(URI.createURI("platform:/plugin/"), bURI);

			initialized = true;
		} catch (Exception e) {
			System.err.println("Warning: failed to initialize ENeoSetup: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void writeProjectFile(File projectDir, String name) throws IOException {
		File dotProject = new File(projectDir, ".project");
		if (!dotProject.exists()) {
			String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<projectDescription>\n\t<name>" + name + "</name>\n</projectDescription>\n";
			Files.writeString(dotProject.toPath(), content);
		}
	}

	private static void extractResource(String resourcePath, File... targets) throws IOException {
		InputStream in = ENeoSetup.class.getResourceAsStream("/" + resourcePath);
		if (in == null) {
			in = ENeoSetup.class.getResourceAsStream(resourcePath);
		}
		if (in == null) {
			in = ClassLoader.getSystemResourceAsStream(resourcePath);
		}
		if (in == null) {
			// Check if already extracted
			boolean allExist = true;
			for (File target : targets) {
				if (!target.exists()) {
					allExist = false;
					break;
				}
			}
			if (!allExist) {
				System.err.println("Warning: resource not found on classpath: " + resourcePath);
			}
			return;
		}
		byte[] bytes = in.readAllBytes();
		in.close();

		for (File target : targets) {
			target.getParentFile().mkdirs();
			Files.write(target.toPath(), bytes);
		}
	}

	public static String getPlatformResourceURI() {
		init();
		return baseDir != null ? baseDir.getAbsolutePath() : "../";
	}

	public static String getPlatformPluginURI() {
		init();
		return baseDir != null ? URI.createFileURI(baseDir.getAbsolutePath() + "/").toString() : "platform:/plugin/";
	}

	public static String getNeocoreURI() {
		init();
		return nDir != null ? URI.createFileURI(nDir.getAbsolutePath() + "/").toString() : "platform:/plugin/org.emoflon.neo.neocore/";
	}
}
