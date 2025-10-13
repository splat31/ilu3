package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carte.Attaque;
import carte.Borne;
import carte.Botte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.JeuDeCartes;
import carte.Parade;
import carte.Type;

class JeuDeCartesTest {
	JeuDeCartes jeu;

	/*@BeforeEach
	void setUp() {
		Attaque essence=new Attaque (Type.ESSENCE);
		Attaque feu=new Attaque (Type.FEU);
		jeu = new JeuDeCartes();
		jeu.ajouterCarte(essence, 11);
		jeu.ajouterCarte(feu, 11);
	}*/

	@Test
	void testafffichagejeudecarte1() {
		JeuDeCartes jeu = new JeuDeCartes();
        //jeu.creertableau(18);

        // 🟦 Cartes de distance
        jeu.ajouterCarte(new Borne(25), 10);
        jeu.ajouterCarte(new Borne(50), 10);
        jeu.ajouterCarte(new Borne(75), 10);
        jeu.ajouterCarte(new Borne(100), 12);
        jeu.ajouterCarte(new Borne(200), 4);

        // 🟩 Cartes de parade
        jeu.ajouterCarte(new Parade(Type.FEU), 14); // Feu Vert
        jeu.ajouterCarte(new FinLimite(),6);
        jeu.ajouterCarte(new Parade(Type.ESSENCE), 6);        // Essence
        jeu.ajouterCarte(new Parade(Type.CREVAISON), 6);      // Roue de secours
        jeu.ajouterCarte(new Parade(Type.ACCIDENT), 6);       // Réparation

        // 🟥 Cartes d’attaque
        jeu.ajouterCarte(new Attaque(Type.FEU), 5);  // Feu Rouge
        jeu.ajouterCarte(new DebutLimite(),4);
        jeu.ajouterCarte(new Attaque(Type.ESSENCE), 3);       // Panne d’essence
        jeu.ajouterCarte(new Attaque(Type.CREVAISON), 3);     // Crevaison
        jeu.ajouterCarte(new Attaque(Type.ACCIDENT), 3);      // Accident

        // 🟨 Cartes de botte
        jeu.ajouterCarte(new Botte(Type.FEU), 1);             // Prioritaire
        jeu.ajouterCarte(new Botte(Type.ESSENCE), 1);         // Citerne
        jeu.ajouterCarte(new Botte(Type.CREVAISON), 1);       // Increvable
        jeu.ajouterCarte(new Botte(Type.ACCIDENT), 1);        // As du volant

	    
		assertEquals("JEU : \n10 25KM\n10 50KM\n10 75KM\n12 100KM\n4 200KM\n14 Feu Vert\n6 Fin Limite\n6 Bidon d'essence\n6 Roue de secours\n6 Reparation\n5 Feu Rouge\n4 Limite 50\n3 Panne d'essence\n3 Crevaison\n3 Accident\n1 Prioritaire\n1 Citerne\n1 Increvable\n1 As du volant\n", jeu.affichageJeuDeCartes());
	}
	
	void testcheckcount() {
		assert(1==1);
	}

}
