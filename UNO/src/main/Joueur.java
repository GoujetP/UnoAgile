package main;

import java.util.ArrayList;

public class Joueur {
	private ArrayList<Carte> main;

	public ArrayList<Carte> getMain() {
		return main;
	}

	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}

	public Joueur(ArrayList<Carte> main) {
		super();
		this.main = main;
	}
	
	
}
s