package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
	
}
