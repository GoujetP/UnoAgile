package main;

import java.util.ArrayList;
import java.util.Scanner;

public class ChoisirCarte {

	Joueur j1;

	public Carte choixCarte(Joueur j1) {
		Carte choix = null;
		boolean ok;
		do {
			ok = false;

			try{
				@SuppressWarnings("resource")
				Scanner keyboard = new Scanner(System.in);
				System.out.println(j1.getMain().toString());
				System.out.println("Choix de la carte à poser : ");
				int indexChoix = keyboard.nextInt();
				choix = j1.getMain().get(indexChoix-1);
				ok = true;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				ok=false;
			}
			catch(Exception e) {
				ok=false;
			}
		}while(!ok);
		return choix;
	}

	public static void main(String[] args) {
		/*Carte c1 = new Carte(Symbole.CINQ, Couleur.BLEU);
		Carte c2 = new Carte(Symbole.HUIT, Couleur.ROUGE); 
		ArrayList<Carte> Ensemble = new ArrayList<Carte>();
		Ensemble.add(c1);
		Ensemble.add(c2);
		Joueur j = new Joueur();
		j.setMain(Ensemble);
		System.out.println(Ensemble);
		System.out.println(j.getNbCarte());
		ChoisirCarte c = new ChoisirCarte();

		System.out.println(c.choixCarte(j));*/

	}
}
