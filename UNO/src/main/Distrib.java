package main;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


public class Distrib {
	private Pioche p=new Pioche();
	
	public ArrayList<Carte> initialDistribution(){
		/*
		Carte temp;
		Random r = new Random();
		int place;
		int size =input.size();
		for(Carte c:input) {
			temp=c;
			place=r.nextInt(input.size());
			c= input.get(place);
			input.set(place,temp);
		}
		*/
		ArrayList<Carte> distribution=new ArrayList<Carte>();
		distribution.addAll(p.getCartes());
		Collections.shuffle(distribution);
		return distribution;
	}
	
	public Carte distribuer(ArrayList<Carte> input) {
		Carte temp=input.get(0);
		input.remove(0);
		return temp;
	}
	
	public static void main(String[] args) {
		Distrib d=new Distrib();
		System.out.println(d.initialDistribution());
	}
}
