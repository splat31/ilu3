package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carte.Borne;
import carte.Carte;
import carte.JeuDeCartes;
import carte.Parade;
import carte.Type;
import utils.GestionCartes;

class GestionCartesTest {
	private JeuDeCartes jeu;
	private JeuDeCartes jeu2;
	private GestionCartes gestion;
	private List<Carte> cartesl;
	private List<Carte> cartesl2;
	private String ROUGE;
    private String RESET;

	
	@BeforeEach
	void setUp() {
		
		ROUGE = "\u001B[31m";
	    RESET = "\u001B[0m";

		jeu = new JeuDeCartes();
        jeu2 = new JeuDeCartes();
        gestion = new GestionCartes();
       
        Carte[] jeux = jeu.donnerCartes();
        Carte[] jeux2 = jeu2.donnerCartes();
        
		cartesl = new ArrayList<>();
		for (int i = 0; i<jeux.length;i++) {
        	cartesl.add(jeux[i]);
        }
		
		cartesl2 = new ArrayList<>();
		for (int i = 0; i<jeux2.length;i++) {
        	cartesl2.add(jeux2[i]);
        }
        
	}

	@Test
	void testextraire() {
		System.out.println(ROUGE + "Debut test extraire\n" + RESET);
		Carte extraite1 = gestion.extraire(cartesl);
        Carte extraite2 = gestion.extraireit(cartesl);
        System.out.println("j'ai extrait "+extraite1);
        System.out.println("j'ai extrait "+extraite2);
        System.out.println(ROUGE +"\nFin test extraire\n" + RESET);
	}
	
	@Test
	void testmelanger() {
		System.out.println(ROUGE + "Debut test mélanger\n" + RESET);
		System.out.println("JE MELANGE\n");
		List<Carte> jeumelange1 = gestion.melanger(cartesl);
		List<Carte> jeumelange2 = gestion.melanger(cartesl2);
		
		System.out.println("Voici mon jeu1\n"); 
		for (int i = 0; i<jeumelange1.size();i++) {
        	System.out.println(jeumelange1.get(i));
        }
		System.out.println("\n\n\n");
		System.out.println("Voici mon jeu2\n"); 
		for (int i = 0; i<jeumelange2.size();i++) {
        	System.out.println(jeumelange2.get(i));
        }
		System.out.println("\n");
		
		assertTrue(gestion.verifierMelange(jeumelange1, jeumelange2),"jeu1 et jeu2 s'ont pas les memes cartes");
		jeumelange1.add(new Parade(Type.CREVAISON));
		assertFalse(gestion.verifierMelange(jeumelange1, jeumelange2),"jeu1 et jeu2 ont les memes cartes (devrait pas)");
		System.out.println(ROUGE+"\nFin test melanger\n"+RESET);	
	}
	
	
	@Test
	void testrassembler() {
		System.out.println(ROUGE+"Debut test rassembler\n"+RESET);								
		System.out.println("JE MELANGE ET RASSEMBLE\n");
		List<Carte> jeumelange1 = gestion.melanger(cartesl);
		List<Carte> jeurassemble = gestion.rassembler(jeumelange1);
		for (int i = 0; i<jeurassemble.size();i++) {
        	System.out.println(jeurassemble.get(i));
        }
		System.out.println("\n");
		assertTrue(gestion.verifierRassemblement(jeurassemble));
		jeurassemble.add(new Parade(Type.FEU));
		jeurassemble.add(new Parade(Type.CREVAISON));
		jeurassemble.add(new Parade(Type.FEU));
		jeurassemble.add(new Borne(75));
		jeurassemble.add(new Borne(75));
		
		
		assertFalse(gestion.verifierRassemblement(jeurassemble));
		System.out.println(ROUGE + "\nFin test rassembler\n" + RESET);
	}
	
	@Test
	void testgeneral() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
    
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = gestion.melanger(listeCartes);
		System.out.println(listeCartes);
    
		System.out.println(
				"liste mélangée sans erreur ? " + gestion.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = gestion.rassembler(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblée sans erreur ? " + gestion.verifierRassemblement(listeCartes));
	}
}
