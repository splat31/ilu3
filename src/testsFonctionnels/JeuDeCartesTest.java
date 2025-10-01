package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carte.Attaque;
import carte.JeuDeCartes;
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
		Attaque essence=new Attaque (Type.ESSENCE);
		Attaque feu=new Attaque (Type.FEU);
		jeu = new JeuDeCartes();
		jeu.ajouterCarte(essence, 11);
		jeu.ajouterCarte(feu, 11);
		//assertEquals("JEU : \n10 25KM\n10 50KM\n10 75KM\n12 100KM\n4 200KM\n14 Feu Vert\n6 Fin Limite\n6 Bidon d'essence\n6 Roue de secours\n6 Réparation\n5 Feu Rouge\n4 Limite 50\n3 Panne d'essence\n3 Crevaison\n3 Accident\n1 Prioritaire\n1 Citerne\n1 Increvable\n1 As du volant\n", jeu.affichageJeuDeCartes());
	}

}
