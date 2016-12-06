package org.benchmarx.examples.familiestopersons.testsuite.scalability;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
		System.out.println("...........................................");
		
		System.out.println("Time for creating single (simpson) family 1 time");
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer1 = new BXToolTimer<>(new EMoflonFamiliesToPersons());
		System.out.println("eMoflon = "+timer1.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily) + "ms;");
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer2 = new BXToolTimer<>(new MediniQVTFamiliesToPersons());
		System.out.println("MediniQVT = "+timer2.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily) + "ms;");
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer = new BXToolTimer<>(new BiGULFamiliesToPersons());
		System.out.println("BiGIL = "+timer.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily) + "ms;");
	
		System.out.println("...........................................");
		System.out.println("Time for creating single (simpson) family 5 times");
		
		long sumeMoflon = 0;
		long sumMedini = 0;
		long sumBigul = 0;
		
		for(int i=0;i<4;i++)
		{
			sumeMoflon = sumeMoflon + timer1.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily); 
			sumMedini = sumMedini + timer2.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily);
			sumBigul = sumBigul + timer.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily);
		}
		
		System.out.println("eMoflon ="+sumeMoflon);
		System.out.println("MediniQVT ="+sumMedini);
		System.out.println("BiGIL ="+sumBigul);
		
		
			
		
		/*String fileTimingResults = "output.txt";
		
		try {
			PrintWriter outputStream = new PrintWriter(fileTimingResults);
			outputStream.println("eMoflon = "+timer1.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily)+"ms; MediniQVT = "+timer2.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily)+"ms; BiGUL = "+timer.timeSourceEditFromScratchInMS(helperFamily::createSimpsonFamily)+"ms;");
			outputStream.flush();
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		
	
	}
}
