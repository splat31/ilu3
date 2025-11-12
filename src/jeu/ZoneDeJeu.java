package jeu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import carte.*;

public class ZoneDeJeu {
	private LinkedList<Limite> pileLimite = new LinkedList<>();
	private LinkedList<Bataille> pileBatailles = new LinkedList<>();
	private List<Borne> pilebBornes = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (estPrioritaire()) {
			return 200;
		}
		if (pileLimite.isEmpty()) {
			return 200;
		}
		Limite limite = pileLimite.getFirst();
		if (limite instanceof FinLimite) {
			return 200;
		}
		return 50;
	}

	public int donnerKmParcourus() {
		if (pilebBornes.isEmpty()) {
			return 0;
		}
		int total = 0;
		for (Borne borne : pilebBornes) {
			total += borne.getKm();
		}
		return total;
	}

	public void deposer(Carte c) {
		//TODO Utiliser un switch
		if (c instanceof Borne borne) {
			pilebBornes.add(borne);
		} else if (c instanceof Limite limite) {
			pileLimite.addFirst(limite);
		} else if (c instanceof Bataille bataille) {
			pileBatailles.addFirst(bataille);
		} else if (c instanceof Botte botte) {
			bottes.add(botte);
		} else {
			throw new IllegalArgumentException("On dépose une carte non valide");
		}
	}

	public boolean peutAvancer() {
		if (pileBatailles.isEmpty()) {
			return estPrioritaire();
		}
		Bataille derniereBataille = pileBatailles.getFirst();

		if (derniereBataille instanceof Parade p) {
			return p.getType().equals(Type.FEU) || estPrioritaire();
		}
		if (derniereBataille instanceof Attaque a) {
			if (a.getType().equals(Type.FEU) && estPrioritaire()) {
				return true;
			}
			if (estProtege(a) && estPrioritaire()) {
				return true;
			}
		}
		return false;
	}

	private boolean estDepotBorneAutorise(Borne b) {
		if (!peutAvancer()) {
			return false;
		}
		int km = b.getKm();
		if (km > donnerLimitationVitesse()) {
			return false;
		}
		return donnerKmParcourus() + km <= 1000;
	}

	private boolean estDepotFeuVerAutorise() {
		if (estPrioritaire()) {
			return false;
		}
		if (pileBatailles.isEmpty()) {
			return true;
		}
		Bataille sommet = pileBatailles.getFirst();
		if (sommet instanceof Attaque att) {
			if (att.getType().equals(Type.FEU) || estProtege(att)) {
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
		if (estPrioritaire()) {
			return false;
		}
		if (limite instanceof DebutLimite) {
			if (pileLimite.isEmpty()) {
				return true;
			}
			return pileLimite.getFirst() instanceof FinLimite;
		} else if (limite instanceof FinLimite) {
			return !pileLimite.isEmpty() && pileLimite.getFirst() instanceof DebutLimite;
		}
		return false;
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque att) {
			return peutAvancer() && !estProtege(att);
		}

		if (bataille instanceof Parade parade) {
			Type paradeType = parade.getType();
			if (paradeType == null) {
				throw new IllegalArgumentException("Type de parade nul");
			}
			if (paradeType.equals(Type.FEU)) {
				return estDepotFeuVerAutorise();
			} else if (!pileBatailles.isEmpty()) {
				Bataille sommet = pileBatailles.getFirst();
				if (sommet instanceof Attaque att && att.getType().equals(paradeType) && !estProtege(att)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean estDepotAutorise(Carte c) {
		if (c instanceof Bataille b) {
			return estDepotBatailleAutorise(b);
		} else if (c instanceof Limite l) {
			return estDepotLimiteAutorise(l);
		} else if (c instanceof Borne bo) {
			return estDepotBorneAutorise(bo);
		}
		return true; // pour les bottes par ex.
	}

	public void ajouterBotte(Botte b) {
		bottes.add(b);
	}

	public boolean possedeBotte(Botte b) {
		return bottes.contains(b);
	}

	public Set<Botte> getBottes() {
		return bottes;
	}

	public List<Bataille> getBatailles() {
		return pileBatailles;
	}

	public boolean estPrioritaire() {
		return possedeBotte(new Botte(Type.FEU));
	}

	public boolean estProtege(Attaque att) {
		Type type = att.getType();
		return possedeBotte(new Botte(type));
	}
}

