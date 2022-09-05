package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPartie {
	
	Partie p;
	Joueur j1;
	Joueur j2;
	Joueur j3;
	Carte test_mid;
	
	@BeforeEach
	void initialization() {
		j1=new Joueur("player", false);
		p=new Partie(j1, 3);
		test_mid=p.pioche.get(28); //28 puisque 7*4 (joueurs) = 28 et la carte du milieu est la 29ème (on commence à 0 donc 28)
		p.init_partie();
	}
	
	@Test
	void testNbPlayers() {
		assertEquals(4, p.getJoueurs().size());
	}
	
	@Test
	void testBots() {
		int nbBots=0;
		for(Joueur j : p.getJoueurs()) {
			if(j.isBot) {
				nbBots++;
			}
		}
		assertEquals(3, nbBots);
	}

	@Test
	void testNbCartes() {
		assertEquals(7, p.getJoueurs().get(0).getNbCarte());
		assertEquals(7, p.getJoueurs().get(1).getNbCarte());
		assertEquals(7, p.getJoueurs().get(2).getNbCarte());
		assertEquals(7, p.getJoueurs().get(3).getNbCarte());
	}
	
	@Test
	void testPiocher() {
		p.piocher(j1);
		assertEquals(8,j1.getNbCarte());
	}
	
	@Test
	void testCarteMilieu() {
		assertEquals(test_mid,p.getMid());
	}
	
	@Test
	void testPeutJouerCarte() {
		p.setMid(new Carte(Symbole.HUIT, Couleur.JAUNE));
		assertTrue(p.peutJouerCarte(new Carte(Symbole.HUIT, Couleur.ROUGE)));
		assertTrue(p.peutJouerCarte(new Carte(Symbole.REVERSE, Couleur.JAUNE)));
		assertFalse(p.peutJouerCarte(new Carte(Symbole.PASSER, Couleur.BLEU)));
		assertTrue(p.peutJouerCarte(new Carte(Symbole.JOKER, Couleur.SPECIAL)));
	}
	
	@Test
	void testPeutJouer() {
		p.setMid(new Carte(Symbole.HUIT, Couleur.JAUNE));
		j2=new Joueur("qqun", false);
		j2.addCarte(new Carte(Symbole.REVERSE, Couleur.BLEU));
		j2.addCarte(new Carte(Symbole.PLUS2, Couleur.BLEU));
		j2.addCarte(new Carte(Symbole.REVERSE, Couleur.ROUGE));
		j2.addCarte(new Carte(Symbole.PASSER, Couleur.BLEU));
		j2.addCarte(new Carte(Symbole.NEUF, Couleur.VERT));
		j2.addCarte(new Carte(Symbole.DEUX, Couleur.VERT));
		assertFalse(p.peutJouer(j2.getMain()));
		p.setMid(new Carte(Symbole.HUIT, Couleur.VERT));
		assertTrue(p.peutJouer(j2.getMain()));
		p.setMid(new Carte(Symbole.TROIS, Couleur.JAUNE));
		j2.addCarte(new Carte(Symbole.PLUS4, Couleur.SPECIAL));
		assertTrue(p.peutJouer(j2.getMain()));
	}
	
	/*
	@Test
	void testReverseEtJoueurSuivant() {
		Joueur bot1=new Joueur("bot1", true);
		Joueur bot2=new Joueur("bot2", true);
		ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
		joueurs.add(j1);
		joueurs.add(bot1);
		joueurs.add(bot2);
		p.setJoueurs(joueurs);
		assertEquals(j1,p.current);
		p.joueurSuivant();
		assertEquals(p.current, bot1);
		p.reverse();
		assertEquals(bot1.getNext(), j1);
	}
	*/
	
	//ce test ne peut pas être réalisé puisqu'il est basé sur un random, passer le tour aussi
	
	@Test
	
	void testPlus2et4() {
		Joueur jtest = p.current;
		p.plus4(Couleur.VERT);
		assertEquals(11, jtest.getNext().getNbCarte());
		p.joueurSuivant();
		Joueur jtest2 = p.current;
		p.plus2();
		assertEquals(9, jtest2.getNext().getNbCarte());
	}
	
	@Test
	void testPoserCarte() {
		Carte c=new Carte(Symbole.HUIT, Couleur.ROUGE);
		p.setMid(new Carte(Symbole.HUIT, Couleur.BLEU));
		j1.addCarte(c);
		p.poserCarte(j1);//entrer 8
		p.joueurSuivant();
		assertEquals(p.getMid(), c);
	}
	
	@Test
	void trier() {
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		Carte c1=new Carte(Symbole.UN, Couleur.ROUGE);
		Carte c2=new Carte(Symbole.CINQ, Couleur.BLEU);
		Carte c3=new Carte(Symbole.PASSER, Couleur.BLEU);
		Carte c4=new Carte(Symbole.HUIT, Couleur.JAUNE);
		Carte c5=new Carte(Symbole.JOKER, Couleur.SPECIAL);
		Carte c6=new Carte(Symbole.PLUS4, Couleur.SPECIAL);
		cartes.add(c1);
		cartes.add(c2);
		cartes.add(c3);
		cartes.add(c4);
		cartes.add(c5);
		cartes.add(c6);
		Collections.shuffle(cartes);
		ArrayList<Carte> cartes_triees = new ArrayList<Carte>();
		cartes_triees.add(c1);
		cartes_triees.add(c2);
		cartes_triees.add(c3);
		cartes_triees.add(c4);
		cartes_triees.add(c5);
		cartes_triees.add(c6);
	}
}
