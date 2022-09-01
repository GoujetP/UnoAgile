package cartes;

import java.util.ArrayList;
import java.util.List;

public class Pioche {
	private ArrayList<Carte> cartes=new ArrayList<Carte>();

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	} 
	
	public void addCarte(Carte c) {
		cartes.add(c);
	}
	
	public Pioche() {
		ArrayList<Symbole> s=new ArrayList<Symbole>();
		for(Symbole sym:Symbole.values()) {
			s.add(sym);
		}
		ArrayList<Couleur> c=new ArrayList<Couleur>();
		for(Couleur cou:Couleur.values()) {
			c.add(cou);
		}
		for(int i=0; i<13; i++) {
			for(int j=0; i<4; i++) {
				if(!s.get(i).equals(Symbole.ZERO)){
					for(int k=0; i<2; i++) {
						cartes.add(new Carte(s.get(i),c.get(j)));
					}
				}else {
					cartes.add(new Carte(s.get(i),c.get(j)));
				}
			}
		}
		
		
	}
	
	public String toString() {
		String res="Les cartes sont: ";
		for(Carte c:cartes) {
			res+=" ";
			res+=c;
		}
		return res;
	}
	
	public static void main(String[] args) {
		Pioche p=new Pioche();
		System.out.println(p);
	}
}
