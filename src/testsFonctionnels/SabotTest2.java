package testsFonctionnels;

import java.util.Iterator;

import carte.*;
import jeu.Sabot;


//PUE SA MERE




public class SabotTest2 {
	JeuDeCartes jeu = new JeuDeCartes();
	Sabot sabota = new Sabot(jeu.donnerCartes());
	Sabot sabotb = new Sabot(jeu.donnerCartes());
	Sabot sabotc = new Sabot(jeu.donnerCartes());

	// 4.2.a
	public void questionA() {

		while (!sabota.estVide()) {
			Carte carte = sabota.piocher();
			System.out.println("Je pioche " + carte);
		}
//		Console :
//		Je pioche Accident
//		Je pioche Accident
//		Je pioche Accident
//		Je pioche R�paration
//		Je pioche R�paration
//		Je pioche R�paration
//		Je pioche As du volant
	}

	// 4.2.b
	public void questionB() {
		for (Iterator<Carte> iterator = sabotb.iterator(); iterator.hasNext();) {
			System.out.println("Je pioche " + iterator.next());
			iterator.remove();
		}
	}

	// 4.2.c
	public void questionC() {
		System.out.println("DEBUT C\n\n");
		Carte cartePiochee = sabotc.piocher();
		System.out.println("Je pioche " + cartePiochee);
		for (Iterator<Carte> iterator = sabotc.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			System.out.println("Je pioche " + carte);
			Carte cartePioche = sabotc.piocher();
		}
		Iterator<Carte> iterator = sabotc.iterator();
		iterator.hasNext();
		System.out.println("\nLa pioche contient encore des cartes ? " + sabotc.estVide());
	}

	public static void main(String[] args) {
		SabotTest2 testPioche = new SabotTest2();
		//testPioche.questionA();
		//testPioche.questionB();
		testPioche.questionC();
	}

}