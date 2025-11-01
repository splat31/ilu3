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
		if (pilebBornes.isEmpty()) {
			return 0;
		}
		int total=0;
		for (Borne borne : pilebBornes ) {
			total=total+borne.getKm();
		}
		return total;
	}
	
	public void deposer (Carte c) {
		if (c instanceof Borne borne) {
			pilebBornes.add(borne);
		} else if ( c instanceof Limite limite) {
			pileLimite.add(limite);
		} else if ( c instanceof Bataille bataille) {
			pileBatailles.add(bataille);
		} else {
			//peut arriver car on gere pas encore les bottes
			 throw new IllegalArgumentException(" zone de jeu.deposer = mauvais type " + c.getClass().getSimpleName());
		}
	}
	
	public boolean peutAvancer() {
		if (pileBatailles.isEmpty()) {
			return false;
		}
		Bataille derniereBataille = pileBatailles.getLast();
		if (derniereBataille instanceof Parade p ) {

			if (p.getType().equals(Type.FEU)) {
				return true;
			}
		}
		return false;
	}
	private boolean estDepotBorneAutorise(Borne b) {
		int km= b.getKm();
		if (km > donnerLimitationVitesse()) {
			return false;
		}
		if (donnerKmParcourus()+km>1000) {
			return false;
		}
		return true;
	}
	
	private boolean estDepotFeuVerAutorise() {
		if (pileBatailles.isEmpty()) {
			return true;
		}
		Bataille sommet = pileBatailles.getLast();
		if (sommet instanceof Attaque att) {
			if (att.getType().equals(Type.FEU)) {
				return true;
			}
		}
		if (sommet instanceof Parade p) {
			if (!p.getType().equals(Type.FEU)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite ) {
			if (pileLimite.isEmpty()) {
				return true;
			}
			if (pileLimite.getLast() instanceof FinLimite) {
				return true;
			}
			return false;
		} else if (limite instanceof FinLimite ) {
			if (!pileLimite.isEmpty()) {
				if (pileLimite.getLast() instanceof DebutLimite) {
					return true;
				}
			}
			return false;
		}
		return false; // n'arrivera pas car limite est soit un debut soit une fin.throw new exception ?
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			if (peutAvancer()) {
				return true;
			}
			return false;
		}
		
		if (bataille instanceof Parade p) {
			if (p.getType().equals(Type.FEU)) {
				return estDepotFeuVerAutorise();
			} else if(!pileBatailles.isEmpty()) {
				if (pileBatailles.getLast() instanceof Attaque att) {
					if (att.getType().equals(p.getType())) {
						return true;
					}
				}
				return false;
			}
		}
		return false; // n'arrivera pas car bataille est soit une attaque soit une parade.throw new exception ?
	}
	
	public boolean estDepotAutorise(Carte c) {
		if (c instanceof Bataille b) {
			return estDepotBatailleAutorise(b);
		} else if (c instanceof Limite l) {
			return estDepotLimiteAutorise(l);
		} else if (c instanceof Borne bo) {
			return estDepotBorneAutorise(bo);
		}
		return true; //cas prioritaire a voir
	}
}
