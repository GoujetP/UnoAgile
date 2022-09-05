package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Carte;
import main.Couleur;
import main.Symbole;

class TestCarte {

	Carte c1;
	Carte c2;
	Carte c3;
	Carte c4;
	Carte c5;
	
	@BeforeEach
	public void initialization() {
		c1=new Carte(Symbole.CINQ, Couleur.BLEU);
		c2=new Carte(Symbole.PASSER, Couleur.VERT);
		c3=new Carte(Symbole.HUIT, Couleur.JAUNE);
		c4=new Carte(Symbole.JOKER, Couleur.SPECIAL);
		c5=new Carte(Symbole.PLUS4, Couleur.SPECIAL);
	}
	@Test
	void testSymbole() {
		assertEquals(Symbole.CINQ, c1.getSymbole());
		assertEquals(Symbole.PLUS4, c5.getSymbole());
	}

	@Test
	void testCouleur() {
		assertEquals(Couleur.VERT, c2.getCouleur());
		assertEquals(Couleur.SPECIAL, c4.getCouleur());
	}
	
	@Test
	void testSetCouleur() {
		c2.setCouleur(Couleur.BLEU);
		assertEquals(Couleur.BLEU, c2.getCouleur());
	}
	
	@Test
	void testSetSymbole() {
		c2.setSymbole(Symbole.REVERSE);
		assertEquals(Symbole.REVERSE, c2.getSymbole());
	}
}
