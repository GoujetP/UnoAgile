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
		String res="Carte "+id+" : Symbole: "+symbole+" ,Couleur: "+ couleur + "\n";
		return res;
	}
}
