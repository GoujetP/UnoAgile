package main;

import java.util.ArrayList;
import java.util.Random;


public class Partie {
	private Pioche pioche;
	private Joueur bot1;
	private Joueur bot2;
	private Joueur joueur;
	
	
	
	public Partie() {
		super();
		this.pioche =new Pioche();
		this.bot1 = new Joueur();
		this.bot2 = new Joueur();
		this.joueur =  new Joueur();
	}



	public void piocher(Joueur j) {
		Random r = new Random();
		Carte c = this.pioche.getCartes().get(r.nextInt(this.pioche.getCartes().size()));
		j.addCarte(c);
		this.pioche.getCartes().remove(c);
		
	}
	
	
	public static void main(String[] args) {
		Distrib distrib = new Distrib();
		Partie partie = new Partie();
		ArrayList<Carte> init = distrib.initialDistribution(partie.pioche.getCartes());
		for (int i = 0 ; i <7 ; i++) {
			partie.joueur.addCarte(distrib.distribuer(partie.pioche.getCartes()));
			partie.bot1.addCarte(distrib.distribuer(partie.pioche.getCartes()));
			partie.bot2.addCarte(distrib.distribuer(partie.pioche.getCartes()));
		}
		System.out.println(partie.joueur.getMain().toString());
		partie.piocher(partie.joueur);
		System.out.println(partie.joueur.getMain().toString());
	}
}
