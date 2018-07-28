package org.benchmarx.examples.ecore2sql.implementations.ibextgg;

import java.io.IOException;

import org.benchmarx.ecore.core.EcoreComparator;
import org.benchmarx.examples.ecore2sql.testsuite.Decisions;
import org.benchmarx.sql.core.SQLComparator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.emoflon.ibex.tgg.operational.strategies.sync.SYNC;
import org.emoflon.ibex.tgg.run.ibextggecoretosql.SYNC_App;

import sql.Schema;

public class IBeXTGGEcoreToSQL extends IBeXTGGAdapter<EPackage, Schema, Decisions, SYNC_App>{

	public IBeXTGGEcoreToSQL() {
		super(new EcoreComparator(), new SQLComparator());
	}

	@Override
	protected SYNC createSynchroniser() throws IOException {
		return new SYNC_App();
	}

	@Override
	protected EPackage createInitialSrc() {
		EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
		pack.setName("");
		pack.setNsPrefix("");
		pack.setNsURI("");
 		return pack;
	}

}
