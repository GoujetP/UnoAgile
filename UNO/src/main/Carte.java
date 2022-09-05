package main;

public class Carte {
	private Symbole symbole;
	private Couleur couleur;
	private int id;
	public static int NUM=1; 
	
	public Carte(Symbole symbole, Couleur couleur) {
		super();
		this.symbole = symbole;
		this.couleur = couleur;
		this.id=NUM;
		NUM++;
	}

	public Symbole getSymbole() {
		return symbole;
	}

	public void setSymbole(Symbole symbole) {
		this.symbole = symbole;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	public String toString() {
		String ansi = "";
		if (couleur.equals(Couleur.ROUGE)) {
			ansi = "\u001B[31m";
		}
		else if (couleur.equals(Couleur.VERT)) {
			ansi = "\u001B[32m";
		}
		else if (couleur.equals(Couleur.BLEU)) {
			ansi = "\u001B[34m";
		}
		else if  (couleur.equals(Couleur.JAUNE)){
			ansi = "\u001B[33m";
		}
		else {
			ansi = "\u001B[35m";
		}
		String res= ansi+symbole+"\u001B[0m";
		return res;
	}
}
