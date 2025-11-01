package jeu;
import carte.Borne;
import carte.Carte;
import jeu.ZoneDeJeu;
public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main = new MainJoueur();
	
	
	public Joueur (String nom, ZoneDeJeu zoneDeJeu) {
		this.nom=nom;
		this.zoneDeJeu=zoneDeJeu;
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
}
