package FamiliesToPersonsIBeXTGG.initfwd.hipe.engine.actor;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;

import akka.actor.ActorRef;

import hipe.engine.config.HiPEOptions;
import hipe.generic.actor.GenericNotificationActor;
import hipe.engine.util.IncUtil;
import hipe.engine.util.notifications.DeltaNotificationIndex;

public class NotificationActor extends GenericNotificationActor {
	
	public NotificationActor(ActorRef dispatchActor, IncUtil incUtil, DeltaNotificationIndex notificationIndex, HiPEOptions options) {
		super(dispatchActor, incUtil, notificationIndex, options);
	}
	
	@Override
	protected void initializeExploration() {
		explorationConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getFemale(), obj -> {
			Collection<EObject> children = new LinkedList<>();
			return children;
		});
		explorationConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getMale(), obj -> {
			Collection<EObject> children = new LinkedList<>();
			return children;
		});
		explorationConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily(), obj -> {
			Collection<EObject> children = new LinkedList<>();
			FamiliesSmartEMF.Family _family = (FamiliesSmartEMF.Family) obj;
			if(_family.getFather() != null)
				children.add(_family.getFather());
			if(_family.getMother() != null)
				children.add(_family.getMother());
			children.addAll(_family.getSons());
			children.addAll(_family.getDaughters());
			return children;
		});
		explorationConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(), obj -> {
			Collection<EObject> children = new LinkedList<>();
			return children;
		});
		explorationConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(), obj -> {
			Collection<EObject> children = new LinkedList<>();
			PersonsSmartEMF.PersonRegister _personregister = (PersonsSmartEMF.PersonRegister) obj;
			children.addAll(_personregister.getPersons());
			return children;
		});
		explorationConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPerson(), obj -> {
			Collection<EObject> children = new LinkedList<>();
			return children;
		});
		explorationConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(), obj -> {
			Collection<EObject> children = new LinkedList<>();
			FamiliesSmartEMF.FamilyRegister _familyregister = (FamiliesSmartEMF.FamilyRegister) obj;
			children.addAll(_familyregister.getFamilies());
			return children;
		});
	}
}

