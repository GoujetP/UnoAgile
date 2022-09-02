package main;

public class DireUno {
	
	Joueur j;
	
	
	public static boolean DireUno(Joueur j) {
		if(j.getNbCarte()==1) {
			return true;
		}
		else {
			return false;
		}
			
	}
}
