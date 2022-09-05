package main;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private String nom;
	private List<Carte> main;
	private int ordre;
	private Joueur next;
	public boolean isBot;

	public List<Carte> getMain() {
		return main;
	}

	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}

	public Joueur(String name, boolean bot) {
		super();
		this.nom=name;
		this.main =new ArrayList<Carte>();
		this.ordre=0;
		this.isBot=bot;
	}
	
	public Joueur(String name,List<Carte> main, boolean bot) {
		super();
		this.nom=name;
		this.main = main;
		this.ordre=0;
		this.isBot=bot;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	
	public void addCarte(Carte c) {
		this.main.add(c);
	}
	
	public void removeCarte(int index) {
		this.main.remove(index);
	}
	
	
	public void removeCarte(Carte c) {
		this.main.remove(c);
	}
	
	
	public int getNbCarte(){
		return this.main.size();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Joueur getNext() {
		return next;
	}

	public void setNext(Joueur next) {
		this.next = next;
	}
	
	public String toString() {
		String res=nom +": \n";
		int i=0;
		if(!isBot) {
			for(Carte c:main) {
				i++;
				res+=c;
				res+="\n";
				res+="(position "+i+") \n";
			}
		}else {
			System.out.println(nom+" a "+main.size()+" cartes.");
		}
		
		return res;
	}
}
