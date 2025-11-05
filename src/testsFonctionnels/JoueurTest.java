package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeu.*;
import carte.*;

class JoueurTest {
	private String ROUGE;
    private String RESET;
    
    private ZoneDeJeu zone1;
    private Joueur j1;
	@BeforeEach
	void setUp() throws Exception {
		ROUGE = "\u001B[31m";
	    RESET = "\u001B[0m";
		j1 = new Joueur("Joueur 1");
		
		
	}

	@Test
	void testgeneral() {
		System.out.println(ROUGE +"Debut test joueur generaux" + RESET);
		
		Borne b1=new Borne(25);
		Borne b2=new Borne(50);
		Borne b3=new Borne(75);
		DebutLimite dL1=new DebutLimite();
		FinLimite fL1=new FinLimite();
		j1.donner(b1);
		j1.donner(b2);
		j1.donner(b3);
		j1.donner(fL1);
		j1.donner(dL1);
		System.out.println("le joueur ("+j1.toString()+") a dans sa main:\n"+j1.getMain().toString());
		System.out.println("Deposer carte "+b1);
		j1.deposer(b1);
		
		System.out.println("Deposer carte "+b2);
		j1.deposer(b2);
		
		System.out.println("Deposer carte "+b3);
		j1.deposer(b3);
		System.out.println("le joueur "+j1.toString()+" a dans sa main:\n"+j1.getMain().toString());
		System.out.println("Total des bornes : "+j1.donnerKmParcourus());
		
		zone1=j1.getZone();
		System.out.println("Limite :" + zone1.donnerLimitationVitesse());
		System.out.println("Deposer carte "+dL1);
		j1.deposer(dL1);
		System.out.println("Limite : " + zone1.donnerLimitationVitesse());
		System.out.println("Deposer carte "+fL1);
		j1.deposer(fL1);
		System.out.println("Limite : " + zone1.donnerLimitationVitesse());
		
		System.out.println(ROUGE +"Fin test joueur generaux" + RESET);
	}
	
	@Test
	void testmain() {
		System.out.println(ROUGE +"\nDebut test main" + RESET);
		/*DE BASE ON DEVRA TOUJOURS UTILISER JOUEUR POUR TRAITER SA MAIN
		Mais pour facilier mes tests j'utilise la main directement
		ce qui implique de mettre ses fonction en public*/
		
		MainJoueur main = new MainJoueur();
		
		Borne b1=new Borne(25);
		Borne b2=new Borne(50);
		Borne b3=new Borne(75);
		DebutLimite dL1=new DebutLimite();
		FinLimite fL1=new FinLimite();
		
		main.prendre(b1);
		main.prendre(b2);
		main.prendre(b3);
		main.prendre(dL1);
		main.prendre(fL1);
		
		assertNotEquals("la main est vide",main.toString()); //elle n'est pas vide
		while (!main.MainIsEmpty()) {
			main.jouerderniere();
		}

		System.out.println(main.toString()); // "la main est vide"
		assertEquals("la main est vide",main.toString());
		
		//flemme de faire des try catch pour tester si on obtient bien des erreurs
		System.out.println(ROUGE +"Fin test main" + RESET);
	}

}
