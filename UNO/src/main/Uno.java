package main;

public class Uno {
	public static void main(String[] args) {
		Joueur j = new Joueur("test", false);
		Partie p = new Partie(j,3);
		p.jouer();
	}
}
