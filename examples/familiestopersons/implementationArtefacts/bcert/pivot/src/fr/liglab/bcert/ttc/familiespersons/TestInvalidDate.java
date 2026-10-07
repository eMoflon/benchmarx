package fr.liglab.bcert.ttc.familiespersons;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;


import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class TestInvalidDate {

	 public static void main(String[] args) {
		 Date original 	= (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(), "0000-1-1");
		 String saved	= EcoreFactory.eINSTANCE.convertToString(EcorePackage.eINSTANCE.getEDate(), original);
		 Date reloaded	= (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(), saved);
		 
		 System.out.println("original converted by EMF to string  "+saved);
		 
		 System.out.println("original to string  "+original);
		 System.out.println("reloaded to string  "+reloaded);
		 
		 DateFormat fullFormat = new SimpleDateFormat("G yyyy-MM-dd'T'HH:mm:ss'.'SSSZ",Locale.ENGLISH);
		 System.out.println("original to full string  "+fullFormat.format(original));
		 System.out.println("reloaded to full string  "+fullFormat.format(reloaded));

		 assertEquals(original,reloaded);
	}
}
