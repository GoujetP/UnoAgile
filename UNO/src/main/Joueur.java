package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Joueur {
	private String nom;
	private List<Carte> main;
	private int ordre;
	private Joueur next;
	private Joueur before;
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
	
	public Joueur getBefore() {
		return before;
	}

	public void setBefore(Joueur before) {
		this.before = before;
	}

	public String toString() {
		String res=nom +": \n";
		int i=0;
		if(!isBot) {
			for(Carte c:main) {
				/*try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				i++;
				res+="\n"+c;
				res+="(position "+i+")";	
			}
		}else {
			System.out.println(nom+" a "+main.size()+" cartes."+"\n");
		}
		
		return res;
	}
	
	
}
