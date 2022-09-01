package main;

import java.util.ArrayList;
import java.util.Random;

public class Distrib {
	public ArrayList<Carte> initialDistribution(ArrayList<Carte> input){
		Carte temp;
		Random r;
		int place;
		int size =input.size();
		for(Carte c:input) {
			temp=c;
			place=r.nextInt();
			c= input.get(place);
			input.set(place,temp);
		}
		
		return input;
	}
	public Carte distribuer(ArrayList<Carte> input) {
		Carte temp=input.get(0);
		input.remove(0);
		return temp;
	}
}
