package main;

import java.util.List;

public class CarteMain {
	List<Carte> main;
	
	
	private int findDelim(String input,int sub) {
		int nbFois=0;
		int copy=0;
		while(sub>nbFois) {
		for(int cpt=0;cpt<input.length();cpt++) {
			copy=cpt;
			if(input.charAt(cpt)=='a') {
				nbFois++;
				
			}
		}
		return input.length();
		}
		return copy;
	}
	
	public CarteMain(List<Carte> main) {
		this.main= main;
	}
	
	@Override
	public String toString() {
		String neo="";
		for(int cpt=0;cpt<6;cpt++) {
			for(Carte c: main) {
				
				neo+=c.toString().substring(findDelim(c.toString(), cpt)+1, findDelim(c.toString(), cpt+1));
						}
			neo+="\n";
		}
		return neo;
	}

	public List<Carte> getMain() {
		return main;
	}

	public void setMain(List<Carte> main) {
		this.main = main;
	}
	
	
}
