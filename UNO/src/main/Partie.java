package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Partie {
	private Pioche pioche;
	private Joueur bot1;
	private Joueur bot2;
	private Joueur joueur;
	private Carte mid_carte;
	
	
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
	
	public void voirMidCarte() {
		System.out.println("La carte au milieu est " +mid_carte.toString());
	}
	

	public void init_partie() {
		Distrib distrib = new Distrib();
		ArrayList<Carte> init = distrib.initialDistribution(pioche.getCartes());
		for (int i = 0 ; i <7 ; i++) {
			joueur.addCarte(distrib.distribuer(pioche.getCartes()));
			bot1.addCarte(distrib.distribuer(pioche.getCartes()));
			bot2.addCarte(distrib.distribuer(pioche.getCartes()));
		}
		mid_carte=distrib.distribuer(pioche.getCartes());
		voirMidCarte();
	}
	
	
	
	public static void main(String[] args) {
		Partie partie = new Partie();
		partie.init_partie();
		System.out.println(partie.joueur.getMain().toString());
	}

}
