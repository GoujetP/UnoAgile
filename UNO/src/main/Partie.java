package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Partie {
	Distrib distrib = new Distrib();
	ArrayList<Carte> pioche;
	private Carte mid_carte;
	private ArrayList<Joueur> joueurs;
	private Joueur current;

	public Partie(Joueur j, int places) {
		joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		for (int i = 0; i < places; i++) {
			joueurs.add(new Joueur("bot" + i, new ArrayList<Carte>()));
		}
		Collections.shuffle(joueurs);
		current = joueurs.get(0);
		next(current);
		pioche = distrib.initialDistribution();
	}
	
	
	

	public void piocher(Joueur j) {
		Carte c = this.pioche.get(this.pioche.size() - 1);
		this.pioche.remove(c);
		j.addCarte(c);
		System.out.println(j.getNom() + " pioche !!!!");

	}

	public void voirMidCarte() {
		System.out.println("La carte au milieu est " + mid_carte.toString());
	}

	public void init_partie() {
		for (Joueur j : joueurs) {
			for (int i = 0; i < 7; i++) {
				j.addCarte(distrib.distribuer(pioche));
			}
		}
		mid_carte = distrib.distribuer(pioche);
		
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public boolean peutJouerCarte(Carte c) {
		if (c.getCouleur().equals(mid_carte.getCouleur())) {
			return true;
		} else if (c.getSymbole().equals(mid_carte.getSymbole())) {
			return true;
		} else if (c.getCouleur().equals(Couleur.SPECIAL)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean peutJouer(List<Carte> main) {
		boolean res = false;
		for (Carte c : main) {
			if (peutJouerCarte(c)) {
				res = true;
			}
		}
		return res;
	}

	public void reverse() {
		for (int i = 0; i < joueurs.size() / 2; i++) {
			Joueur temp = joueurs.get(i);
			joueurs.set(i, joueurs.get(joueurs.size() - 1 - i));
			joueurs.set(joueurs.size() - 1 - i, temp);
		}
		next(current);
		System.out.println("Le joueur suivant est désormais: " + current.getNext().getNom());
		joueurSuivant();
	}

	public void next(Joueur j) {
		int idx = joueurs.indexOf(j);
		if ((idx + 1) == joueurs.size()) {
			idx = 0;
		} else {
			idx++;
		}
		j.setNext(joueurs.get(idx));
	}

	public void plus2() {
		for (int i = 0; i < 2; i++) {
			piocher(current.getNext());
		}
	}

	public void plus4(Couleur c) {
		for (int i = 0; i < 4; i++) {
			piocher(current.getNext());
		}

		changementCouleur(c);
	}

	public void changementCouleur(Couleur c) {
		mid_carte.setCouleur(c);
	}

	public String toString() {
		String res = "Partie: \n";
		for (Joueur j : joueurs) {
			res += j;
		}
		res += "\n";
		res += "joueur: " + current + "\n";
		res += "joueur suivant: " + current.getNext();
		return res;
	}

	public void joueurSuivant() {
		next(current);
		current = current.getNext();
		next(current);
	}

	public void passer() {
		joueurSuivant();
		System.out.println("Le joueur suivant est désormais: " + current.getNext());
		joueurSuivant();

	}

	public Carte choixCarte(Joueur j1) {
		Carte choix = null;
		boolean ok;
		do {
			ok = false;

			try {
				@SuppressWarnings("resource")
				Scanner keyboard = new Scanner(System.in);
				System.out.println("-------------------------------------------------\n" + j1.getMain().toString());
				System.out.println("Choix de la carte à poser : ");
				int indexChoix = keyboard.nextInt();
				choix = j1.getMain().get(indexChoix - 1);
				ok = true;
			} catch (ArrayIndexOutOfBoundsException e) {
				ok = false;
			} catch (Exception e) {
				ok = false;
			}
		} while (!ok);
		return choix;
	}

	public void poserCarte(Joueur j) {
		Carte choix = choixCarte(j);
		if (peutJouer(j.getMain())) {
			while (!peutJouerCarte(choix)) {
				choix = choixCarte(j);
			}
			j.getMain().remove(choix);
			mid_carte = choix;
			System.out.println("Vous avez posé la carte "+choix);
		}

	}

	public void poserCarteBot(Joueur bot, int index){
		Carte choix = bot.getMain().get(index);
		bot.getMain().remove(choix);
		mid_carte = choix;
		System.out.println(bot.getNom()+" a joué "+choix);
	}
	
	public void trier(Joueur j){
		ArrayList<Carte> res=new ArrayList<Carte>();
		Couleur[] ordreC=Couleur.values();
		Symbole[] ordreS=Symbole.values();
		for(Couleur c:ordreC) {
			for(Symbole s:ordreS) {
				for(Carte ca:j.getMain()) {
					if(ca.getCouleur().equals(c)&&ca.getSymbole().equals(s)) {
						res.add(ca);
					}
				}
			}
		}
		j.setMain(res);
	}

	public static void main(String[] args) {
		boolean deja_passe =false;
		boolean deja_passe2 =false;
		boolean win = false;
		Joueur winner = new Joueur("winner_test", false);
		String name = "";
		int nb = 0;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Veuillez entrer votre pseudo:");
			name = sc.nextLine();
			System.out.println("Bienvenue, " + name + "!");
			System.out.println("Vous êtes contre 3 bots.");
			nb = 3;
			Joueur j = new Joueur(name, false);
			Joueur reel = j;
			Partie p = new Partie(j, nb);
			p.init_partie();
			int cpt = 0;
			int cpt2 = 0;
			while (!win) {
				p.voirMidCarte();
				TimeUnit.SECONDS.sleep(4);
				
				if (p.mid_carte.getSymbole().equals(Symbole.REVERSE)){
					p.reverse();
					p.joueurSuivant();
					p.joueurSuivant();
				}
				else if (p.mid_carte.getSymbole().equals(Symbole.PASSER)) {
					p.passer();
				}
				else if (p.mid_carte.getSymbole().equals(Symbole.PLUS2) && !deja_passe  ) {
					p.plus2();
					p.joueurSuivant();
					cpt++;
					deja_passe=true;
				}
				else if (p.mid_carte.getSymbole().equals(Symbole.PLUS4)  && !deja_passe ) {
					Couleur c = Couleur.ROUGE;
					System.out.println("Quelle couleur jefe ? V pur Vert , B pour Bleu , R pour Rouge , J pur Jaune");
					Scanner sc1 = new Scanner(System.in);
					String color = sc1.nextLine();
					for (Couleur co: Couleur.values()) {
						if (co.equals(color)){
							c=co;
						}
					}
					p.plus4(c);
					p.joueurSuivant();
					cpt2++;
					deja_passe2=true;
					
				}
				else if (p.mid_carte.getSymbole().equals(Symbole.JOKER)) {
					Couleur c = Couleur.ROUGE;
					System.out.println("Quelle couleur jefe ? V pur Vert , B pour Bleu , R pour Rouge , J pur Jaune");
					Scanner sc1 = new Scanner(System.in);
					String color = sc1.nextLine();
					for (Couleur co: Couleur.values()) {
						if (co.equals(color)){
							c=co;
						}
					}
					p.joueurSuivant();
				}
				else if (p.current.equals(reel)) {
					if (p.peutJouer(p.current.getMain())) {
						p.poserCarte(reel);
						p.joueurSuivant();
					} else {
						p.piocher(p.current);
						p.joueurSuivant();
					}
				} else {
					if (p.peutJouer(p.current.getMain())) {
						ArrayList<Carte> carte_jouable = new ArrayList<Carte>();
						for (Carte c : p.current.getMain()) {
							if (p.peutJouerCarte(c)) {
								carte_jouable.add(c);
							}
						}
						Random r = new Random();
						int idx = r.nextInt(carte_jouable.size());
						p.poserCarteBot(p.current, idx);
						p.joueurSuivant();
					} else {
						p.piocher(p.current);
						p.joueurSuivant();
					}
				}
				if (p.current.getMain().size() == 0) {
					winner = p.current;
					win = true;
				}
				if (cpt==2) {
					deja_passe=false;
				}
				if (cpt2==2) {
					deja_passe2=false;
				}
				

			}
			System.out.println(winner.toString() + " a gagné!!!!!");
		} catch (NumberFormatException e) {
			System.out.println("Veuillez entrer un chiffre");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
