package jeu;
import java.util.List;

import carte.Borne;
import carte.Carte;
import jeu.ZoneDeJeu;
public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main;
	
	public Joueur (String nom, ZoneDeJeu zoneDeJeu) {
		this.nom=nom;
		this.zoneDeJeu=zoneDeJeu;
		this.main = new MainJoueur();
	}
	public MainJoueur getMain() {
        return main;
    }
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals (Object obj) {
		if(obj instanceof Joueur joueur2) {
			return nom.equals(joueur2.toString());
		}
		return false;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte pioche= sabot.piocher();
		donner(pioche);
		return pioche;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	
	public void deposer (Carte c) {
		if(!main.contient(c)) {
			throw new IllegalArgumentException("le joueur ne possede pas la carte qu'il veut deposer");
		} else {
			
			zoneDeJeu.deposer(c);
			main.jouer(c);
		}
	}
}
