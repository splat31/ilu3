package jeu;

import java.lang.module.ModuleDescriptor.Builder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private GestionCartes gestion;
	private List<Carte> listeCartes;
	private Carte[] carteSab;
	/*Linked car plus tard dans donnerjoueursuivant  on va avoir besoin d'assurer que l'ordre des
	elements est respecte hors un hash set ne le fais pas*/
	private LinkedHashSet<Joueur> participants;
	private int suivant =0;
	private int nbTour = 1;
	
	public Set<Joueur> getParticipants() {
		return participants; 
	}
	
	public Jeu (Carte[] tab) {
		this.gestion=new GestionCartes();
		this.listeCartes = new ArrayList<>(Arrays.asList(tab));
		
		this.listeCartes=gestion.melanger(listeCartes);
		this.carteSab = listeCartes.toArray(new Carte[0]);
		this.sabot = new Sabot(carteSab);
		this.participants = new LinkedHashSet<Joueur>();
	}
	
	public void distribuerCartes() {
		for (Joueur joueur : participants) {
			for (int i =0; i<6;i++) {
				Carte carte = sabot.piocher();
				joueur.donner(carte);
			}
		}
	}
	public void ajouterJoueur( Joueur j) {
		participants.add(j);
	}
	
	public boolean contientJoueur(Joueur j) {
		return participants.contains(j);
	}
	
	public String jouerTour(Joueur joueur) {
		StringBuilder tour = new StringBuilder();
		
		Carte pioche=joueur.prendreCarte(sabot);
		tour.append("Le joueur " + joueur.toString()+" a pioche "+pioche.toString()+"\n");
		
		tour.append("Il a dans sa main: [ " + joueur.afficherMain()+"]\n");
		
		Coup coup = joueur.choisirCoup(participants);

		tour.append(joueur.toString()+coup.toString());
		Carte carte = coup.getCarte();
		joueur.retirerDeLaMain(carte);
		if (coup.getCible()==null) {
			sabot.ajouterCarte(carte);
		} else {
			coup.getCible().deposer(carte);
		}
		
		tour.append(joueur + " a parcouru en tout: "+joueur.donnerKmParcourus()+"\n");
		tour.append("Voici l'etat de son jeu à la fin du tour:\n"+joueur.afficherEtatJoueur()+"\n\n");
		
		return tour.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		//mieux autrement
		int i=0;
		for (Iterator<Joueur> iterator = participants.iterator();iterator.hasNext();i++){
			Joueur joueur = iterator.next();
			if (i==suivant) {
				suivant = (suivant + 1)%participants.size();
				return joueur;
			}
			
		}
		return null;
	}
	private boolean aGagner(Joueur j) {
		return j.donnerKmParcourus()==1000;
	}
	private String affichageJoueur() {
		StringBuilder listj = new StringBuilder();
		for (Joueur j: participants) {
			listj.append("   -"+j+"\n");
		}
		return listj.toString();
	}
	public String lancer() {
		
		StringBuilder partie = new StringBuilder();
		partie.append("Début de la partie avec les joueurs:\n"+affichageJoueur()+"\n");
		boolean gagnant = false;
		Joueur joueur = null;
		while (!gagnant&&!sabot.estVide()) {
			joueur = donnerJoueurSuivant();
			partie.append("Tour "+nbTour+"\n"+jouerTour(joueur));
			gagnant = aGagner(joueur);
			nbTour++;
		}
		
		if (sabot.estVide()||joueur==null) {
			partie.append("\nLe Sabot a ete vide\n");
		} else {
			partie.append("\nLe joueur "+joueur+" a gagner la partie\n");
		}
		partie.append(toStringClassement());
		return partie.toString();
	}
	
	public NavigableSet<Joueur> classement() {
		NavigableSet<Joueur> ensemble = new TreeSet<>(new JoueurComparator());
		ensemble.addAll(participants);
		/*
		for (Joueur j : participants) {
			ensemble.add(j);
		}*/
		return ensemble;
		
	}
	
	public String toStringClassement() {
		StringBuilder n = new StringBuilder();
		n.append("Classement:\n");
		NavigableSet<Joueur> classement = classement();
		for (Joueur j : classement) {
			n.append("-"+j.toString()+"\n");
		}
		n.append("\n");
		return n.toString();
	}
	
}
