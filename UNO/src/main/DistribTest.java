package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DistribTest {

	Distrib d=new Distrib();
	Pioche p=new Pioche();
	
	@Test
	void test() {
		assertFalse(p.getCartes().equals(d.initialDistribution()));
	}

}
