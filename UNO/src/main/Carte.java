package cartes;

public class Carte {
	private Symbole symbole;
	private Couleur couleur;
	private int id;
	private static int NUM=0; 
	
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
		String res="Carte "+id+" : Symbole: "+symbole+" ,Couleur: "+couleur+"/";
		return res;
	}
}
