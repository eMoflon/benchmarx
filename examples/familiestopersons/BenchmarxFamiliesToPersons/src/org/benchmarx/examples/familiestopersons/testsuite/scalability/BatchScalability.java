package org.benchmarx.examples.familiestopersons.testsuite.scalability;

import org.benchmarx.BXToolTimer;
import org.benchmarx.examples.familiestopersons.families.core.FamilyHelper;
import org.benchmarx.examples.familiestopersons.implementations.bigul.BiGULFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.emoflon.EMoflonFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.persons.core.PersonHelper;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class BatchScalability {
	private static FamilyHelper helperFamily = new FamilyHelper();
	private static PersonHelper helperPersons = new PersonHelper();
	
	public static void main(String[] args) {
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer1 = new BXToolTimer<>(new EMoflonFamiliesToPersons());
		System.out.print(timer1.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily) + "ms;");
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer2 = new BXToolTimer<>(new MediniQVTFamiliesToPersons());
		System.out.print(timer2.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily) + "ms;");
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer = new BXToolTimer<>(new BiGULFamiliesToPersons());
		System.out.print(timer.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily) + "ms");
	}
}
