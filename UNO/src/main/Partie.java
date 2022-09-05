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
			joueurs.add(new Joueur("bot" + i, new ArrayList<Carte>(), true));
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
		System.out.println(j.getNom() + " pioche !!!! \n il y'a maintenant "+j.getNbCarte()+ " cartes dans sa main.");

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
		if ((c.getCouleur().equals(mid_carte.getCouleur()) || c.getSymbole().equals(mid_carte.getSymbole()) || c.getCouleur().equals(Couleur.SPECIAL))) {
			if (c.getSymbole().equals(Symbole.PLUS2) && mid_carte.getSymbole().equals(Symbole.PLUS2)) {
				return false;
			}
			else if (c.getSymbole().equals(Symbole.PLUS4) && mid_carte.getSymbole().equals(Symbole.PLUS4)) {
				return false;
			}
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
		joueurSuivant();
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
		System.out.println("Le joueur suivant est désormais: " + current.getNext().getNom());
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
				System.out.println("-------------------------------------------------\n" + j1);
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

	public void poserCarteBot(Joueur bot, int index , ArrayList<Carte> jouable){
		Carte choix =jouable.get(index);
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
		boolean plus2 =false;
		boolean plus4 =false;
		boolean joker =false;
		boolean passer = false;
		boolean reverse = false;
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
			int cpt_plus2 = 0;
			int cpt_plus4 = 0;
			int cpt_joker = 0;
			int cpt_passer = 0;
			int cpt_reverse = 0;			
			while (!win) {
				if(!p.current.isBot && p.current.getNbCarte()==1) {
					//scanner
				}
				p.voirMidCarte();
				TimeUnit.SECONDS.sleep(4);
				
				if (p.mid_carte.getSymbole().equals(Symbole.REVERSE) && !reverse){
					p.reverse();
					reverse = true;
					cpt_reverse++;
				}
				 if (p.mid_carte.getSymbole().equals(Symbole.PASSER) && !passer) {
					p.passer();
					passer=true;
					cpt_passer++;
				}
				 if (p.mid_carte.getSymbole().equals(Symbole.PLUS2) && !plus2  ) {
					p.plus2();
					p.joueurSuivant();
					cpt_plus2++;
					plus2=true;
				}
				
				 if (p.current.equals(reel)) {
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
						p.poserCarteBot(p.current, idx , carte_jouable);
						System.out.println(p.current);
						p.joueurSuivant();
					} else {
						p.piocher(p.current);
						System.out.println(p.current);
						p.joueurSuivant();
					}
				}
				if (p.current.getMain().size() == 0) {
					winner = p.current;
					win = true;
				}
				if (cpt_plus2==1) {
					plus2=false;
				}
				if (cpt_reverse==1) {
					reverse=false;
				}
				if (cpt_passer==1) {
					passer=false;
				}
				if (p.mid_carte.getSymbole().equals(Symbole.PLUS4)  && !plus4 ) {
					 if (p.current.equals(reel)) {
						Couleur c = Couleur.ROUGE;
						System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
						Scanner sc1 = new Scanner(System.in);
						String color = sc1.nextLine();
						while ( !color.equals("V") && !color.equals("B") &&!color.equals("R") && !color.equals("J") ) {
							System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
							color = sc1.nextLine();
		
						}
						
						if (color.equals("V")) {
							c=Couleur.VERT;
						}
						else if (color.equals("B")) {
							c=Couleur.BLEU;
						}
						else if (color.equals("R")) {
							c=Couleur.ROUGE;
						}
						else {
							c=Couleur.JAUNE;
						}
						p.mid_carte.setCouleur(c);
						p.plus4(c);
						p.joueurSuivant();
						cpt_plus4++;
						plus4=true;
					 }
					else {
						Couleur[] couleur = Couleur.values();
						Random r = new Random();
						int idx = r.nextInt(couleur.length);
						p.mid_carte.setCouleur(couleur[idx]);
						p.joueurSuivant();
						cpt_joker++;
						joker=true;
					}
					
					
				}
				if (p.mid_carte.getSymbole().equals(Symbole.JOKER) && !joker) {
					if (p.current.equals(reel)) {
						Couleur c = Couleur.ROUGE;
						System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
						Scanner sc1 = new Scanner(System.in);
						String color = sc1.nextLine();
						while ( !color.equals("V") && !color.equals("B") && !color.equals("R") && !color.equals("J") ) {
							System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
							color = sc1.nextLine();
							
						}
						
						if (color.equals("V")) {
							c=Couleur.VERT;
						}
						else if (color.equals("B")) {
							c=Couleur.BLEU;
						}
						else if (color.equals("R")) {
							c=Couleur.ROUGE;
						}
						else {
							c=Couleur.JAUNE;
						}
						p.mid_carte.setCouleur(c);
						p.joueurSuivant();
						cpt_joker++;
						joker=true;
					}
					else {
						Couleur[] couleur = Couleur.values();
						Random r = new Random();
						int idx = r.nextInt(couleur.length);
						p.mid_carte.setCouleur(couleur[idx]);
						p.joueurSuivant();
						cpt_joker++;
						joker=true;
					}
				}
				 
				 if (cpt_plus4==1) {
						plus4=false;
					}
					if (cpt_joker==1) {
						joker=false;
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
