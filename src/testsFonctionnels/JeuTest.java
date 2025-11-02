package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carte.Carte;
import carte.JeuDeCartes;
import jeu.Jeu;
import jeu.Joueur;
import jeu.Sabot;
import jeu.ZoneDeJeu;

class JeuTest {
	JeuDeCartes jeuDeCartes;
	Jeu jeu;
	
	
	@BeforeEach
	void setUp() throws Exception {
		jeuDeCartes=new JeuDeCartes();
		jeu = new Jeu(jeuDeCartes.donnerCartes());
		jeu.ajouterJoueur(new Joueur("Bastien", new ZoneDeJeu()));
		jeu.ajouterJoueur(new Joueur("Yoann", new ZoneDeJeu()));
		//jeu.ajouterJoueur(new Joueur("Oscar", new ZoneDeJeu()));
		//jeu.ajouterJoueur(new Joueur("Vira", new ZoneDeJeu()));
		jeu.distribuerCartes();
	}

	@Test
	void test() {
		/*
		Set<Joueur> participants = jeu.getParticipants();
		
		for (int i = 0;i<9;i++) {
			for (Joueur joueur : participants) {
				System.out.println(jeu.jouerTour(joueur));
			}
		}*/
		System.out.println(jeu.lancer());
	}

}
