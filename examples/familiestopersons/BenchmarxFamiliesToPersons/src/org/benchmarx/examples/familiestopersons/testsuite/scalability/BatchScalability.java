package org.benchmarx.examples.familiestopersons.testsuite.scalability;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer2 = new BXToolTimer<>(new MediniQVTFamiliesToPersons());
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer = new BXToolTimer<>(new BiGULFamiliesToPersons());
		
		String commaDeli = ",";
		String newLineSep = "\n";
		String fileHeader = " , , , ,eMoflon,Medini,BiGUL";
		
		try {
			FileWriter testWriter = new FileWriter(new File("test2.csv"));
			testWriter.append(fileHeader);
			
			for(int k=1;k<=5;k++)
			{	
				testWriter.append(newLineSep);
				testWriter.append(newLineSep);
				testWriter.append("measurement "+String.valueOf(k)+" time");
				
				int i= 0;
				while(i<=30)
				{
					
					long sumeMoflon = 0;
					long sumMedini = 0;
					long sumBigul = 0;
					
					if(i==0)
					{
						sumeMoflon = timer1.timeSourceEditFromScratchInMS(helperFamily::createNewfamilyBachchanWithMembers); 
						sumMedini = timer2.timeSourceEditFromScratchInMS(helperFamily::createNewfamilyBachchanWithMembers);
						sumBigul = timer.timeSourceEditFromScratchInMS(helperFamily::createNewfamilyBachchanWithMembers);
						
						testWriter.append(newLineSep);
						testWriter.append("1 Famliy and 4 members created");
						
					}
					for(int j=0;j<i;j++)
					{
						sumeMoflon = sumeMoflon + timer1.timeSourceEditFromScratchInMS(helperFamily::createNewfamilyBachchanWithMembers); 
						sumMedini = sumMedini + timer2.timeSourceEditFromScratchInMS(helperFamily::createNewfamilyBachchanWithMembers);
						sumBigul = sumBigul + timer.timeSourceEditFromScratchInMS(helperFamily::createNewfamilyBachchanWithMembers);
					}
					
					if(i!=0)
					{
						testWriter.append(newLineSep);
						testWriter.append(String.valueOf(i)+" Famliies and "+String.valueOf(i*4) +" members created");
					}
					
					testWriter.append(commaDeli);
					testWriter.append(commaDeli);
					testWriter.append(commaDeli);
					testWriter.append(commaDeli);
					testWriter.append(String.valueOf(sumeMoflon));
					testWriter.append(commaDeli);
					testWriter.append(String.valueOf(sumMedini));
					testWriter.append(commaDeli);
					testWriter.append(String.valueOf(sumBigul));
					
				i=i+5;
				}
			}	
			testWriter.flush();
			testWriter.close();	
			System.out.println("csv created successfully!!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
