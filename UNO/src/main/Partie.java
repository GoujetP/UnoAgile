package main;

import java.util.ArrayList;
import java.util.Random;
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
	
	
	public boolean peutJouerCarte(Carte c) {
		if (c.getCouleur().equals(mid_carte.getCouleur()) || c.getSymbole().equals(mid_carte.getSymbole())|| (c.getCouleur().equals(Couleur.SPECIAL))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean peutJouer(ArrayList<Carte> main) {
		for (Carte c : main) {
			if(peutJouerCarte(c)) {
				return true;
			}
		}
		return false;
	}
	
	
	public Carte choixCarte(Joueur j1) {
		Carte choix = null;
		boolean ok;
		do {
			ok = false;

			try{
				@SuppressWarnings("resource")
				Scanner keyboard = new Scanner(System.in);
				System.out.println(j1.getMain().toString());
				System.out.println("Choix de la carte à poser : ");
				int indexChoix = keyboard.nextInt();
				choix = j1.getMain().get(indexChoix-1);
				ok = true;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				ok=false;
			}
			catch(Exception e) {
				ok=false;
			}
		}while(!ok);
		return choix;
	}
	
	
	public void poserCarte(Joueur j) {
		Carte choix = choixCarte(j);
		if (peutJouer(j.getMain())) {
			while( !peutJouerCarte(choix)) {
				choix=choixCarte(j);
				System.out.println(choix);
			}
			j.getMain().remove(choix);
			mid_carte=choix;
			System.out.println(mid_carte);
		}
		else {
			piocher(j);
		}
	}
	public static void main(String[] args) {
		Partie partie = new Partie();
		partie.init_partie();
		partie.poserCarte(partie.joueur);
		
	}

}
