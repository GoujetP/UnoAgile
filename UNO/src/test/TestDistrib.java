package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDistrib {

	Distrib d=new Distrib();
	Pioche p=new Pioche();
	
	@Test
	void test() {
		assertFalse(p.getCartes().equals(d.initialDistribution()));
	}

	@Test
	void testDistribuer() {
		Carte c1=p.getCartes().get(0);
		assertEquals(c1, d.distribuer(p.getCartes()));
	}

}
