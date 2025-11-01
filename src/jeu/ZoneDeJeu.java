package jeu;

import java.util.ArrayList;
import java.util.List;

import carte.*;



public class ZoneDeJeu {
	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBatailles = new ArrayList<>();
	private List<Borne> pilebBornes = new ArrayList<>();
	
	
	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty()) {
			return 200;
		}
		Limite limite= pileLimite.getLast();
		if (limite instanceof FinLimite) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		if (pileLimite.isEmpty()) {
			return 0;
		}
		int total=0;
		for (Borne borne : pilebBornes ) {
			total=total+borne.getKm();
		}
		return total;
	}
}
