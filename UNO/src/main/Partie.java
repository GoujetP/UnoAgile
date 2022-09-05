package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Partie {
	Distrib distrib = new Distrib();
	public ArrayList<Carte> pioche;
	private Carte mid_carte;
	private ArrayList<Joueur> joueurs;
	public Joueur current;

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

	public Carte getMid() {
		return mid_carte;
	}

	public void setMid(Carte c) {
		mid_carte=c;
	}
	public void piocher(Joueur j) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Carte c = this.pioche.get(this.pioche.size() - 1);
		this.pioche.remove(c);
		j.addCarte(c);
		System.out.println(j.getNom() + " pioche !!!! \n il y'a maintenant "+j.getNbCarte()+ " cartes dans sa main.");

	}

	public void voirMidCarte() {
		System.out.println("-------------------------------------------------\n" );
		System.out.println("La carte au milieu est \n " + mid_carte.toString());

	}

	public void init_partie() {
		for (Joueur j : joueurs) {
			for (int i = 0; i < 7; i++) {
				j.addCarte(distrib.distribuer(pioche));
			}
			before(j);
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

	public void before(Joueur j) {
		int idx = joueurs.indexOf(j);
		if ((idx) == 0) {
			idx = joueurs.size()-1;
		} else {
			idx--;
		}
		j.setBefore(joueurs.get(idx));
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
				System.out.println("-------------------------------------------------\n"+j1);
				System.out.println("Choix de la carte à poser : ");
				System.out.println("-------------------------------------------------\n");
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

			j.getMain().remove(choix);
			setMid(choix);

			System.out.println("Vous avez posé la carte "+choix+"\n");
			System.out.println();
		}

	}

	public void poserCarteBot(Joueur bot, int index , ArrayList<Carte> jouable){
		Carte choix =jouable.get(index);
		bot.getMain().remove(choix);
		mid_carte = choix;
		//Timeout.seconds(2);
		System.out.println("\n"+bot.getNom()+" a joué \n"+choix);
		//Timeout.seconds(2);
		System.out.println("Il lui reste: "+bot.getNbCarte()+" cartes.");
	}

	public static void jouer() {
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
			while (!win) {
				plus2 =false;
				plus4 =false;
				joker =false;
				passer = false;
				reverse = false;
				p.voirMidCarte();
				TimeUnit.SECONDS.sleep(4);
				
				if (p.mid_carte.getSymbole().equals(Symbole.REVERSE) && !reverse){
					p.reverse();
					reverse = true;
					
				}
				if (p.mid_carte.getSymbole().equals(Symbole.PASSER) && !passer) {
					p.passer();
					passer=true;
					
				}
				if (p.mid_carte.getSymbole().equals(Symbole.PLUS2) && !plus2  ) {
					p.plus2();
					p.joueurSuivant();
					
					plus2=true;
				}

				if (p.current.equals(reel)) {
					if (p.peutJouer(p.current.getMain())) {
						p.poserCarte(reel);
						if (p.current.getNbCarte() == 1) {
							Scanner keyboard = new Scanner(System.in);
							final boolean[] doitPiocher = {true};

							Thread timer = new Thread() {
								public void run() {
									if (keyboard.nextLine().toLowerCase(Locale.ROOT).equals("uno")) {
										doitPiocher[0] = false;
									}
								}
							};

							timer.start();
							TimeUnit.SECONDS.sleep(2);
							timer.interrupt();

							if (doitPiocher[0]) {
								System.out.println("Contre UNO !");
								p.piocher(p.current);
							}
						}
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
						if (p.current.getNbCarte() == 1) {
                            Scanner keyboard = new Scanner(System.in);
                            final boolean[] doitPiocher = {false};

                            Thread timer = new Thread() {
                                public void run() {
                                    if (keyboard.nextLine().toLowerCase(Locale.ROOT).equals("contreuno")) {
                                        doitPiocher[0] = true;
                                    }
                                }
                            };

                            timer.start();
                            TimeUnit.SECONDS.sleep(4);
                            timer.interrupt();

                            if (doitPiocher[0]) {
                                System.out.println("\nContre UNO !");
                                p.piocher(p.current);
                                p.piocher(p.current);
                            }
                        }
						p.joueurSuivant();
					} else {
						p.piocher(p.current);
						System.out.println(p.current);
						p.joueurSuivant();
					}
				}

				if (p.mid_carte.getSymbole().equals(Symbole.PLUS4)  && !plus4 ) {
					
					if (p.current.getBefore().equals(reel)) {
						Couleur c = Couleur.ROUGE;
						System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
						Scanner sc1 = new Scanner(System.in);
						String color = sc1.nextLine();
						while ( !color.toUpperCase(Locale.ROOT).equals("V") && !color.toUpperCase(Locale.ROOT).equals("B") &&!color.toUpperCase(Locale.ROOT).equals("R") && !color.toUpperCase(Locale.ROOT).equals("J") ) {
							System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
							color = sc1.nextLine();

						}

						if (color.toUpperCase(Locale.ROOT).equals("V")) {
							c=Couleur.VERT;
						}
						else if (color.toUpperCase(Locale.ROOT).equals("B")) {
							c=Couleur.BLEU;
						}
						else if (color.toUpperCase(Locale.ROOT).equals("R")) {
							c=Couleur.ROUGE;
						}
						else {
							c=Couleur.JAUNE;
						}
						p.mid_carte.setCouleur(c);
						p.plus4(c);
						p.joueurSuivant();
						plus4=true;
					}
					else {
						Couleur[] couleur = Couleur.values();
						Random r = new Random();
						int idx = r.nextInt(couleur.length);
						p.changementCouleur(couleur[idx]);
						p.plus4(couleur[idx]);
						p.joueurSuivant();
						plus4=true;
					}


				}
				if (p.mid_carte.getSymbole().equals(Symbole.JOKER) && !joker) {

					
					if (p.current.getBefore().equals(reel)) {
						Couleur c = Couleur.ROUGE;
						System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
						Scanner sc1 = new Scanner(System.in);
						String color = sc1.nextLine();
						while ( !color.toUpperCase(Locale.ROOT).equals("V") && !color.toUpperCase(Locale.ROOT).equals("B") && !color.toUpperCase(Locale.ROOT).equals("R") && !color.toUpperCase(Locale.ROOT).equals("J") ) {
							System.out.println("Quelle couleur jefe ? V pour Vert , B pour Bleu , R pour Rouge , J pour Jaune");
							color = sc1.nextLine();

						}

						if (color.toUpperCase(Locale.ROOT).equals("V")) {
							c=Couleur.VERT;
						}
						else if (color.toUpperCase(Locale.ROOT).equals("B")) {
							c=Couleur.BLEU;
						}
						else if (color.toUpperCase(Locale.ROOT).equals("R")) {
							c=Couleur.ROUGE;
						}
						else {
							c=Couleur.JAUNE;
						}
						p.mid_carte.setCouleur(c);
						p.joueurSuivant();
						joker=true;
					}
					else {
						Couleur[] couleur = Couleur.values();
						Random r = new Random();
						int idx = r.nextInt(couleur.length);
						p.changementCouleur(couleur[idx]);
						p.joueurSuivant();
						joker=true;
					}
				}





				if (p.current.getMain().size() == 0) {
					winner = p.current;
					win = true;
				}

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(winner.toString() + " a gagné!!!!!");
	}

	



}
