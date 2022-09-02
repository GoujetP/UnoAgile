package main;

import java.util.ArrayList;

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
		Carte.NUM=1;
		ArrayList<Symbole> s=new ArrayList<Symbole>();
		for(Symbole sym:Symbole.values()) {
			s.add(sym);
		}
		ArrayList<Couleur> c=new ArrayList<Couleur>();
		for(Couleur cou:Couleur.values()) {
			c.add(cou);
		}
		for(int i=0; i<13; i++) {
			for(int j=0; j<4; j++) {
				if(!s.get(i).equals(Symbole.ZERO)){
					for(int k=0; k<2; k++) {
						addCarte(new Carte(s.get(i),c.get(j)));
					}
				}else {
					addCarte(new Carte(s.get(i),c.get(j)));
				}
			}
		}
		
		for(int i=13; i<15; i++) {
			for(int j=4; j<5; j++) {
				for(int k=0; k<4; k++) {
					addCarte(new Carte(s.get(i),c.get(j)));
				}
			}
		}
	}
	
	public String toString() {
		String res="Les cartes sont: \n";
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
