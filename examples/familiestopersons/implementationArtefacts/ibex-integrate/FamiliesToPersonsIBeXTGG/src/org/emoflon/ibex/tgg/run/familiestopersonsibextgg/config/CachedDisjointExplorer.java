package org.emoflon.ibex.tgg.run.familiestopersonsibextgg.config;

import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;

import Families.FamilyRegister;
import FamiliesSmartEMF.Family;
import FamiliesSmartEMF.FamilyMember;
import Persons.PersonRegister;
import PersonsSmartEMF.Female;
import PersonsSmartEMF.Male;
import PersonsSmartEMF.Person;
import hipe.generic.actor.stateless.GenericStatelessSearchActor;
import hipe.generic.actor.stateless.StatelessDeltaMatch;
import hipe.generic.actor.stateless.search.DisjointExplorer;

public class CachedDisjointExplorer extends DisjointExplorer {

	private final Class<?> searchedType;
	private Map<String, Set<Object>> attributeToCandidates;
	private Collection<Resource> resources;

	public CachedDisjointExplorer(GenericStatelessSearchActor searchActor, Collection<Resource> resources, int index,
			Predicate<Object> instanceOfCheck, boolean isSignature, Class<?> searchedType) {
		super(searchActor, resources, index, instanceOfCheck, isSignature);
		this.resources = resources;
		this.searchedType = searchedType;
		createCache(null);

	}

	@Override
	protected Collection<Object> getCandidates(StatelessDeltaMatch match) {
		
		var attributeValues = getSearchedAttributeValueFromContext(match);

		var result = attributeValues.stream().map(value -> attributeToCandidates.getOrDefault(value, Set.of()))
				.flatMap(Collection::stream).collect(Collectors.toSet());

		return result;
	}

	private List<String> getSearchedAttributeValueFromContext(StatelessDeltaMatch match) {
		return switch (searchedType.getSimpleName()) {
		case "Male", "Female" -> {
			var family = getElement(match, Family.class);
			try {
				var familyMember = getElement(match, FamilyMember.class);
				yield List.of("%s, %s".formatted(family.getName(), familyMember.getName()));
			} catch (RuntimeException e) {
				Stream<FamilyMember> members = Stream.concat(Stream
						.concat(Stream.of(family.getFather(), family.getMother()), family.getDaughters().stream()),
						family.getSons().stream());
				yield members.map(m -> (FamilyMember) m).map(FamilyMember::getName)
						.map(name -> "%s, %s".formatted(family.getName(), name)).toList();
			}
		}
		case "Family" -> {
			var personName = getElement(match, Person.class).getName();
			yield List.of(personName.split(", ")[0]);
		}
		case "FamilyMember" -> {
			yield List.of(getElement(match, Person.class).getName());
		}
		case "PersonRegister" -> List.of("");
		case "FamilyRegister" -> List.of("");
		default ->
			throw new RuntimeException("Unknown search type %s given".formatted(searchedType.getCanonicalName()));
		};
	}

	private <T> T getElement(StatelessDeltaMatch m, Class<T> clazz) {
		for (var node : m.getNodes()) {
			if (node == null)
				continue;

			if (clazz.isInstance(node)) {
				return clazz.cast(node);
			}
		}
		throw new RuntimeException("Could not find a node of type %s in match".formatted(clazz.getCanonicalName()));
	}

	long duration = 0;

	boolean cacheInitialized = false;
	
	private void createCache(StatelessDeltaMatch match) {
		if(cacheInitialized)
			return;
		
		cacheInitialized = true;
		
		synchronized (resources) {
			resources.forEach(r -> r.eAdapters().add(new CacheEMFListener()));
		}
		
		var candidates = super.getCandidates(match);

		attributeToCandidates = switch (searchedType.getSimpleName()) {
		case "Male" ->
			candidates.stream().collect(Collectors.groupingBy(male -> ((Male) male).getName(), Collectors.toSet()));
		case "Female" -> candidates.stream()
				.collect(Collectors.groupingBy(female -> ((Female) female).getName(), Collectors.toSet()));
		case "Family" -> candidates.stream()
				.collect(Collectors.groupingBy(family -> ((Family) family).getName(), Collectors.toSet()));
		case "FamilyMember" -> {
			var result = candidates.stream().collect(Collectors.groupingBy(familyMember -> {
				var member = (FamilyMember) familyMember;
				return ((Family) member.eContainer()).getName() + ", " + member.getName();
			}, Collectors.toSet()));
			result.putAll(candidates.stream().collect(Collectors.groupingBy(familyMember -> {
				var member = (FamilyMember) familyMember;
				return ((Family) member.eContainer()).getName();
			}, Collectors.toSet())));
			yield result;
		}
		case "FamilyRegister" -> candidates.stream().collect(Collectors.groupingBy(register -> "", Collectors.toSet()));
		case "PersonRegister" -> candidates.stream().collect(Collectors.groupingBy(register -> "", Collectors.toSet()));
		default ->
			throw new RuntimeException("Unknown search type %s given".formatted(searchedType.getCanonicalName()));
		};
	}

	@Override
	public void reset() {
		super.reset();
	}

	public void print(String prefix) {
		System.out.println("%s Duration %s".formatted(prefix, (double) duration / 1000f));
	}

	private class CacheEMFListener extends EContentAdapter {
		@Override
		public void notifyChanged(Notification notification) {
			var tic = System.currentTimeMillis();

			var feature = (EStructuralFeature) notification.getFeature();

			if (feature != null && feature.getName().contains("Inverse"))
				return;

			var notifierMatchesSearchType = searchedType.getSimpleName()
					.equals(notification.getNotifier().getClass().getSimpleName().replaceAll("Impl", ""));
			var oldValueMatchesSearchType = notification.getOldValue() != null && searchedType.getSimpleName()
					.equals(notification.getOldValue().getClass().getSimpleName().replaceAll("Impl", ""));
			var newValueMatchesSearchType = notification.getNewValue() != null && searchedType.getSimpleName()
					.equals(notification.getNewValue().getClass().getSimpleName().replaceAll("Impl", ""));

			var formerObjectCount = attributeToCandidates.values().stream().map(Collection::size).reduce(Integer::sum);

			switch (notification.getNotifier().getClass().getSimpleName().replaceAll("Impl", "")) {
			case "Male": {
				var currentName = ((Male) notification.getNotifier()).getName();
				if (feature != null && feature.getName().equals("name")) {
					attributeToCandidates.getOrDefault(notification.getOldValue(), new HashSet<>())
							.remove(notification.getNotifier());
					attributeToCandidates.computeIfAbsent(currentName, s -> new HashSet<>())
							.add(notification.getNotifier());
				}
				break;
			}
			case "Female": {
				var currentName = ((Female) notification.getNotifier()).getName();
				if (feature != null && feature.getName().equals("name")) {
					attributeToCandidates.getOrDefault(notification.getOldValue(), new HashSet<>())
							.remove(notification.getNotifier());
					attributeToCandidates.computeIfAbsent(currentName, s -> new HashSet<>())
							.add(notification.getNotifier());
				}
				break;
			}
			case "Family": {
				var currentName = ((Family) notification.getNotifier()).getName();
				if (notifierMatchesSearchType && feature != null && feature.getName().equals("name")) {
					attributeToCandidates.getOrDefault(notification.getOldValue(), new HashSet<>())
							.remove(notification.getNotifier());
					attributeToCandidates.computeIfAbsent(currentName, s -> new HashSet<>())
							.add(notification.getNotifier());
				} else if (oldValueMatchesSearchType || newValueMatchesSearchType) {
					if (notification.getEventType() == Notification.ADD
							|| notification.getEventType() == Notification.SET) {
						var member = (FamilyMember) notification.getNewValue();
						var key = ((Family) member.eContainer()).getName() + ", " + member.getName();

						attributeToCandidates.computeIfAbsent(key, s -> new HashSet<>())
								.add(notification.getNewValue());
					} else if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
						var member = (FamilyMember) notification.getOldValue();
						var key = ((Family) member.eContainer()).getName() + ", " + member.getName();

						attributeToCandidates.getOrDefault(key, new HashSet<>()).remove(notification.getOldValue());
					}
				}
				break;
			}
			case "FamilyMember": {
				if (notifierMatchesSearchType && feature != null && feature.getName().equals("name")) {
					var member = (FamilyMember) notification.getNotifier();
					var currentName = ((Family) member.eContainer()).getName() + ", " + member.getName();
					var oldName = ((Family) member.eContainer()).getName() + ", " + notification.getOldValue();

					attributeToCandidates.getOrDefault(oldName, new HashSet<>()).remove(notification.getNotifier());
					attributeToCandidates.computeIfAbsent(currentName, s -> new HashSet<>())
							.add(notification.getNotifier());
				}
				break;
			}
			case "Resource", "SmartEMFResource": {
				if (oldValueMatchesSearchType || newValueMatchesSearchType) {
					if (notification.getEventType() == Notification.ADD) {
						attributeToCandidates.computeIfAbsent("", s -> new HashSet<>()).add(notification.getNewValue());
					} else if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
						attributeToCandidates.getOrDefault("", new HashSet<>()).remove(notification.getOldValue());
					}
				}
				break;
			}
			case "FamilyRegister": {
				var family = (Family) (notification.getOldValue() != null ? notification.getOldValue()
						: notification.getNewValue());
				if (oldValueMatchesSearchType || newValueMatchesSearchType) {
					if (notification.getEventType() == Notification.ADD) {
						attributeToCandidates.computeIfAbsent(family.getName(), s -> new HashSet<>())
								.add(notification.getNewValue());
					} else if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
						attributeToCandidates.getOrDefault(family.getName(), new HashSet<>())
								.remove(notification.getOldValue());
					}
				}
				break;
			}
			case "PersonRegister": {
				var person = (Person) (notification.getOldValue() != null ? notification.getOldValue()
						: notification.getNewValue());
				if (oldValueMatchesSearchType || newValueMatchesSearchType) {
					if (notification.getEventType() == Notification.ADD) {
						attributeToCandidates.computeIfAbsent(person.getName(), s -> new HashSet<>())
								.add(notification.getNewValue());
					} else if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
						attributeToCandidates.getOrDefault(person.getName(), new HashSet<>())
								.remove(notification.getOldValue());
					}
				}
				break;
			}
			}
			duration += System.currentTimeMillis() - tic;

			if (notifierMatchesSearchType || newValueMatchesSearchType || oldValueMatchesSearchType) {
				var newObjectCount = attributeToCandidates.values().stream().map(Collection::size).reduce(Integer::sum);
				if (newObjectCount == formerObjectCount) {
					throw new RuntimeException("Same amount as before");
				}
			}

			super.notifyChanged(notification);
		}
	}
}
