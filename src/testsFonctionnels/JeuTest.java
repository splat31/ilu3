package testsFonctionnels;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carte.JeuDeCartes;
import jeu.Jeu;
import jeu.Joueur;

class JeuTest {
	JeuDeCartes jeuDeCartes;
	Jeu jeu;
	
	
	@BeforeEach
	void setUp() throws Exception {
		jeuDeCartes=new JeuDeCartes();
		jeu = new Jeu(jeuDeCartes.donnerCartes());
		jeu.ajouterJoueur(new Joueur("Bastien"));
		jeu.ajouterJoueur(new Joueur("Yoann"));
		//jeu.ajouterJoueur(new Joueur("Oscar"));
		//jeu.ajouterJoueur(new Joueur("Vira"));
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
