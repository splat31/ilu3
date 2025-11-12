package jeu;

import java.util.Comparator;

public class JoueurComparator implements Comparator<Joueur>{

	@Override
	public int compare(Joueur o1, Joueur o2) {
		return o2.donnerKmParcourus()-o1.donnerKmParcourus();
	}

}
