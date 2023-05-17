package uk.ac.york.ttc.miniyaml.model2yaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.yaml.snakeyaml.Yaml;

import miniyaml.MapEntry;
import miniyaml.MiniyamlFactory;
import miniyaml.Value;

public class MiniYAMLConverter {

	public static void main(String[] args) throws IOException {
		miniyaml.MiniyamlPackage.eINSTANCE.getName();
		if (args.length != 3) {
			System.err.println("Usage: java -jar file.jar (model in.yaml out.xmi | yaml in.xmi out.yaml)");
			System.exit(1);
		}

		final boolean toModel = "model".equals(args[0]);
		final File input = new File(args[1]);
		final File output = new File(args[2]);

		if (toModel) {
			new MiniYAMLConverter().convertToModel(input, output);
		} else {
			new MiniYAMLConverter().convertToYAML(input, output);
		}
	}

	public void convertToYAML(File input, File output) throws IOException {
		Resource r = new XMIResourceImpl(URI.createFileURI(input.getCanonicalPath()));
		r.load(null);
		miniyaml.Map source = (miniyaml.Map) r.getContents().get(0);

		Map<String, Object> result = convertToYAML(source);

		try (FileWriter fw = new FileWriter(output)) {
			new Yaml().dump(result, fw);
		}
	}

	public Map<String, Object> convertToYAML(miniyaml.Map source) {
		Map<String, Object> m = new LinkedHashMap<>();
		for (MapEntry entry : source.getEntries()) {
			m.put(entry.getKey(), convertToYAML(entry.getValue()));
		}
		return m;
	}

	private Object convertToYAML(Value value) {
		if (value instanceof miniyaml.Map) {
			return convertToYAML((miniyaml.Map) value);
		} else if (value instanceof miniyaml.List) {
			return ((miniyaml.List) value).getValues()
				.stream()
				.map(e -> convertToYAML(e))
				.collect(Collectors.toList());
		} else if (value instanceof miniyaml.Scalar) {
			return ((miniyaml.Scalar) value).getValue();
		} else {
			return null;
		}
	}

	public void convertToModel(final File input, final File output) throws IOException {
		try (FileReader fr = new FileReader(input); BufferedReader br = new BufferedReader(fr)) {
			Map<String, Object> m = new Yaml().load(br);
			miniyaml.Map result = convertToModel(m);

			Resource resource = new XMIResourceImpl(URI.createFileURI(output.getCanonicalPath()));
			resource.getContents().add(result);
			resource.save(null);
		}
	}

	public miniyaml.Map convertToModel(Map<String, Object> m) {
		miniyaml.Map result = MiniyamlFactory.eINSTANCE.createMap();
		for (Entry<String, Object> entry : m.entrySet()) {
			result.getEntries().add(convertToModel(entry));
		}
		return result;
	}

	private MapEntry convertToModel(Entry<String, Object> entry) {
		miniyaml.MapEntry result = MiniyamlFactory.eINSTANCE.createMapEntry();

		result.setKey(entry.getKey());
		result.setValue(convertToModel(entry.getValue()));
		
		return result;
	}

	@SuppressWarnings("unchecked")
	private Value convertToModel(Object value) {
		if (value instanceof Map) {
			return convertToModel((Map<String, Object>) value);
		} else if (value instanceof Iterable) {
			miniyaml.List result = MiniyamlFactory.eINSTANCE.createList();
			for (Object e : ((Iterable<?>)value)) {
				result.getValues().add(convertToModel(e));
			}
			return result;
		} else if (value != null) {
			miniyaml.Scalar result = MiniyamlFactory.eINSTANCE.createScalar();
			result.setValue(value.toString());
			return result;
		} else {
			return null;
		}
	}

}
