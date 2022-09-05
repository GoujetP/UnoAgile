package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPioche {

	Pioche p=new Pioche();
	
	@Test
	void testPioche() {
		assertEquals(108, p.getCartes().size());
	}

	@Test
	void testCartesBleues() {
		int cartesBleues=0;
		for(Carte c:p.getCartes()) {
			if(c.getCouleur().equals(Couleur.BLEU)) {
				cartesBleues++;
			}
		}
		assertEquals(25,cartesBleues);
	}
	
	@Test
	void cartesSpeciales() {
		int cartesSpeciales=0;
		for(Carte c:p.getCartes()) {
			if(c.getCouleur().equals(Couleur.SPECIAL)) {
				cartesSpeciales++;
			}
		}
		assertEquals(8,cartesSpeciales);
	}
	
	@Test
	void test0() {
		int cartes0=0;
		for(Carte c:p.getCartes()) {
			if(c.getSymbole().equals(Symbole.ZERO)) {
				cartes0++;
			}
		}
		assertEquals(4,cartes0);
	}
	
	@Test
	void testReverse() {
		int reverseCard=0;
		for(Carte c:p.getCartes()) {
			if(c.getSymbole().equals(Symbole.REVERSE)) {
				reverseCard++;
			}
		}
		assertEquals(8,reverseCard);
	}
	

}
