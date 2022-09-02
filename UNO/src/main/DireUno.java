package main;

public class DireUno {
	
	Joueur j;
	
	
	public boolean DireUno(Joueur j) {
		if(j.getNbCarte()==1) {
			return true;
		}
		else {
			return false;
		}	
	}
	public static void main(String[] args) {
		
	}
}
