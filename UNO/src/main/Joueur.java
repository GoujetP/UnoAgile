package main;

import java.util.ArrayList;

public class Joueur {
	private ArrayList<Carte> main;
	private int ordre;

	public ArrayList<Carte> getMain() {
		return main;
	}

	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}

	public Joueur(ArrayList<Carte> main) {
		super();
		this.main = main;
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
	
}
