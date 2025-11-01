package jeu;

import java.util.ArrayList;
import java.util.List;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		GestionCartes gestion = new GestionCartes();
		
		Carte[] tabCartes = jeu.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		listeCartes.addAll(listeCartes);
		listeCartes=gestion.melanger(listeCartes);
		Carte[] carteSab = listeCartes.toArray(new Carte[0]);
		Sabot sabot = new Sabot(carteSab);
	}
}
