package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Carte;
import main.Couleur;
import main.Joueur;
import main.Symbole;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestJoueur {
	
	ArrayList<Carte> main;
	Joueur j1;
	Joueur j2;
	Joueur j3;
	Carte carte;

	@BeforeEach
    public void initialization() {
		main=new ArrayList<Carte>();
		main.add(new Carte(Symbole.CINQ, Couleur.BLEU));
		main.add(new Carte(Symbole.PASSER, Couleur.VERT));
		main.add(new Carte(Symbole.PLUS4, Couleur.SPECIAL));
		j1=new Joueur("truc", false);
		j2=new Joueur("machin", true);
		j3=new Joueur("chouette", main, false);
		carte=new Carte(Symbole.PLUS2, Couleur.ROUGE);
	}
	
	@Test
    void testBot() {
        assertTrue(j2.isBot);
        assertFalse(j1.isBot);
	}
	
	@Test
	void testNbCartes() {
		assertEquals(0, j1.getNbCarte());
		assertEquals(3,j3.getNbCarte());
	}
	
	@Test
	void testAddCarte() {
		j2.addCarte(carte);
		assertEquals(1,j2.getNbCarte());
	}
	
	@Test
	void testRemoveCarte() {
		j3.removeCarte(1);
		assertEquals(2,j3.getNbCarte());
	}
	
	@Test
	void testSetNext() {
		j1.setNext(j2);
		j2.setBefore(j1);
		assertEquals(j2, j1.getNext());
		assertEquals(j1, j2.getBefore());
	}

}
