package main;

import java.util.ArrayList;

public class Joueur {
	private String nom;
	private ArrayList<Carte> main;
	private int ordre;
	private Joueur next;

	public ArrayList<Carte> getMain() {
		return main;
	}

	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}

	public Joueur(String name, ArrayList<Carte> main) {
		super();
		this.nom=name;
		this.main = main;
		this.ordre=0;
	}
	
	public Joueur(String name) {
		super();
		this.nom=name;
		this.main = new ArrayList<Carte>();
		this.ordre=0;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	
	public void addCarte(Carte c) {
		this.main.add(c);
	}
	
	public void removeCarte(int index) {
		this.main.remove(index);
	}
	
	
	public void removeCarte(Carte c) {
		this.main.remove(c);
	}
	
	
	public int getNbCarte(){
		return this.main.size();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Joueur getNext() {
		return next;
	}

	public void setNext(Joueur next) {
		this.next = next;
	}
	
	public String toString() {
		return nom;
		
	}
}
