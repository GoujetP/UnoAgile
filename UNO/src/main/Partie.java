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
	
	private ArrayList<Joueur> joueurs;
	private Joueur current; 
	
	public Partie(ArrayList<Joueur> entrée) {
		joueurs=entrée;
		Collections.shuffle(joueurs);
	}
	
	public Partie(Joueur j, int places) {
		joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		for(int i=0; i<places; i++) {
			joueurs.add(new Joueur("bot"+i, new ArrayList<Carte>()));
		}
		Collections.shuffle(joueurs);
		current=joueurs.get(0);
		next(current);
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public void reverse() {
	    for (int i = 0; i < joueurs.size() / 2; i++) {
	        Joueur temp = joueurs.get(i);
	        joueurs.set(i, joueurs.get(joueurs.size() - 1 - i));
	        joueurs.set(joueurs.size() - 1 - i, temp);
	    }
	    next(current);
	    System.out.println("Le joueur suivant est désormais: "+ current.getNext());
	    joueurSuivant();
	}
	
	public void next(Joueur j) {
		int idx=joueurs.indexOf(j);
		if((idx+1)==joueurs.size()) {
			idx=0;
		}else {
			idx++;
		}
		j.setNext(joueurs.get(idx));
	}
	
	public String toString() {
		String res="Partie: ";
		for (Joueur j:joueurs) {
			res+=j.getNom()+": ["+ j.getMain()+"] ";
		}
		res+="\n"; 
		res+="joueur: " + current +"\n";
		res+="joueur suivant: " + current.getNext();
		return res;
	}
	
	public void joueurSuivant() {
		next(current);
		current=current.getNext();
		next(current);
	}
	
	public void passer() {
		joueurSuivant();
		System.out.println("Le joueur suivant est désormais: "+ current.getNext());
		joueurSuivant();
	}
	
	public static void main(String[] args) {
		Partie partie = new Partie();
		partie.init_partie();
		System.out.println(partie.joueur.getMain().toString());
		String name="";
		int nb=0;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Veuillez entrer votre pseudo:");
			name = sc.nextLine();
			System.out.println("Bienvenue, " + name +"!");
			System.out.println("Combien de joueurs(sans vous compter)?");
			nb = Integer.parseInt(sc.nextLine());
			nb=3; //à changer
			Joueur j=new Joueur(name, new ArrayList<Carte>());
			Partie p=new Partie(j,nb);
			System.out.println(p);
			p.reverse();
			System.out.println(p);
			p.joueurSuivant();
			System.out.println(p);
			p.passer();
			System.out.println(p);
		} catch (NumberFormatException e) {
			System.out.println("Veuillez entrer un chiffre");
		}

	}

}
