package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Partie {
	Distrib distrib=new Distrib();
	ArrayList<Carte> init = distrib.initialDistribution();
	private Carte mid_carte;
	private ArrayList<Joueur> joueurs;
	private Joueur current; 

	
	public Partie(ArrayList<Joueur> entrée) {
		joueurs=entrée;
		Collections.shuffle(joueurs);
		current=joueurs.get(0);
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
	
	public void piocher(Joueur j) {
		Carte c = init.get(init.size()-1);
		init.remove(c);
		
	}
	
	public void voirMidCarte() {
		System.out.println("La carte au milieu est " +mid_carte.toString());
	}
	

	public void init_partie() {
		for(Joueur j:joueurs) {
			for (int i = 0 ; i <7 ; i++) {
				j.addCarte( distrib.distribuer(init));
			}
		}
		mid_carte=distrib.distribuer(init);
		voirMidCarte();
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
			res+=j.getNom()+": "+ j.getMain()+"\n";
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
	
	public boolean verifierWin() {
		boolean res=false;
		for(Joueur j:joueurs) {
			if(j.getNbCarte()==0) {
				res=true;
			}
		}
		return res;
	}
	
	public boolean peutJouerCarte(Carte c) {
		if (c.getCouleur().equals(mid_carte.getCouleur()) || c.getSymbole().equals(mid_carte.getSymbole())
				|| (c.getCouleur().equals(Couleur.SPECIAL))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean peutJouer(Joueur j) {
		for (Carte c : j.getMain()) {
			if (peutJouerCarte(c)) {
				return true;
			}
		}
		return false;
	}

	
	public Carte choixCarte(Joueur j1) {
		/*
		Carte choix = null;
		boolean ok;
		do {
			ok = false;

			try {
				@SuppressWarnings("resource")
				Scanner keyboard = new Scanner(System.in);
				System.out.println(j1.getMain().toString());
				System.out.println("Choix de la carte à poser : ");
				int indexChoix = keyboard.nextInt();
				choix = j1.getMain().get(indexChoix - 1);
				ok = true;
			} catch (ArrayIndexOutOfBoundsException e) {
				ok = false;
			} catch (Exception e) {
				ok = false;
			}
		} while (!ok);
		return choix;
		*/
		
		Carte choix=null;
		boolean posee=false;
		boolean pioche=false;
		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Choix de la carte à poser : ");
			int indexChoix = keyboard.nextInt();
			choix = j1.getMain().get(indexChoix - 1);
		}catch (ArrayIndexOutOfBoundsException e) {};
		if (peutJouerCarte(choix)) {
			
		}
	}

	public void poserCarte(Joueur j) {
		Carte choix = choixCarte(j);
		if (peutJouer(j.getMain())) {
			while (!peutJouerCarte(choix)) {
				choix = choixCarte(j);
				System.out.println(choix);
			}
			j.getMain().remove(choix);
			mid_carte = choix;
			System.out.println(mid_carte);
		} else {
			piocher(j);
		}
	}
	
	public void poserCarteBot(Joueur bot,int index) {
		Carte choix = bot.getMain().get(index);
		if (peutJouerCarte(bot.getMain().get(index))) {
			bot.getMain().remove(choix);
			mid_carte = choix;
			System.out.println(mid_carte);
		}
		
	}

	
	public static void main(String[] args) {
		
		String name="";
		int nb=0;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Veuillez entrer votre pseudo:");
			name = sc.nextLine();
			System.out.println("Bienvenue, " + name +"!");
			System.out.println("Vous êtes contre 3 bots.");
			nb=3; 
			Joueur j=new Joueur(name);
			Partie p=new Partie(j,nb);
			p.init_partie();
			
			while(!p.verifierWin()) {
				if(p.current.equals(j)) {
					System.out.println("Voici votre main: \n");
					System.out.println(j);
					
				}else {
					j.setMain(new ArrayList<Carte>());
				}
			}
			System.out.println("fini!");
		} catch (NumberFormatException e) {
			System.out.println("Veuillez entrer un chiffre");
		}

	}

}
