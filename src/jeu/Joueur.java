package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import carte.Botte;
import carte.Carte;
import carte.FinLimite;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
	private MainJoueur main = new MainJoueur();

	// Amelioration possible pas besoin de donner une zone en argument
	public Joueur(String nom) {
		this.nom = nom;
	}

	public MainJoueur getMain() {
		return main;
	}

	public ZoneDeJeu getZone() {
		return zoneDeJeu;
	}

	public void donner(Carte carte) {
		main.prendre(carte);
	}

	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}

	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}

	public void deposer(Carte c) {
		zoneDeJeu.deposer(c);
		/*
		 * Ancienne version car je pensais que deposer etait forcement effectue par le
		 * joueur hors le joueur peut se voir faire deposer une carte par un autre (Ex:
		 * Bastien attaque oscar donc oscar depose la carte de bastien qu'il n'as donc
		 * pas en possesion) if(!main.contient(c)) { throw new
		 * IllegalArgumentException("le joueur ne possede pas la carte qu'il veut deposer"
		 * ); } else {
		 * 
		 * zoneDeJeu.deposer(c); main.jouer(c); }
		 */
	}

	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> coupspossible = new HashSet<Coup>();
		for (Iterator<Carte> iterator = main.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			for (Joueur cible : participants) {
				Coup atest = new Coup(this, carte, cible);
				if (atest.estValide()) {
					coupspossible.add(atest);
				}
			}
			/*
			 * J'ai fait ca avant de lire la suite XD Coup atest2 = new Coup(this, carte,
			 * null); coupspossible.add(atest2);//forcement valide
			 */
		}
		return coupspossible;
	}

	private Set<Coup> coupsDefausse() {
		Set<Coup> coupspossible = new HashSet<Coup>();
		for (Iterator<Carte> iterator = main.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			Coup def = new Coup(this, carte, null);
			coupspossible.add(def);// forcement valide
		}
		return coupspossible;
	}

	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}

	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupp = coupsPossibles(participants);
		if (coupp.isEmpty()) {
			coupp = coupsDefausse();
		}
		Random rand = new Random();
		int choix = rand.nextInt(coupp.size());
		int i = 0;
		for (Coup coupchoisi : coupp) {
			if (i == choix) {
				return coupchoisi;
			}
			i++;
		}
		return null;
	}

	public String afficherEtatJoueur() {
		StringBuilder builder = new StringBuilder();

		builder.append("Mes bottes sont: \n");
		for (Botte botte : zoneDeJeu.getBottes()) {
			builder.append(botte.toString() + " ");
		}

		builder.append("\nSuis-je limite:\n");
		builder.append(zoneDeJeu.estDepotAutorise(new FinLimite()));

		builder.append("\nLa carte au somment de ma pile de bataille est:\n");

		if (!zoneDeJeu.getBatailles().isEmpty()) {
			builder.append(zoneDeJeu.getBatailles().getFirst());
		} else {
			builder.append("aucune bataille");
		}

		builder.append("\nMa main contient:\n[ ");
		builder.append(main.toString() + "]");
		return builder.toString();
	}

	public String afficherMain() {
		return main.toString();
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return nom.equals(joueur.toString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * nom.hashCode();
	}
}
