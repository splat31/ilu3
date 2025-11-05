package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import carte.*;

public class ZoneDeJeu {
	//TODO faire des piles pour limite et bataille
	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBatailles = new ArrayList<>();
	private List<Borne> pilebBornes = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (estPrioritaire()) {
			return 200;
		}
		if (pileLimite.isEmpty()) {
			return 200;
		}
		Limite limite = pileLimite.getLast();
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
			total = total + borne.getKm();
		}
		return total;
	}

	public void deposer(Carte c) {
		if (c instanceof Borne borne) {
			pilebBornes.add(borne);
		} else if (c instanceof Limite limite) {
			pileLimite.add(limite);
		} else if (c instanceof Bataille bataille) {
			pileBatailles.add(bataille);
		} else if (c instanceof Botte botte) {
			bottes.add(botte);
		} else {
			throw new IllegalArgumentException("On depose une carte non valide");
		}
	}

	public boolean peutAvancer() {
		if (pileBatailles.isEmpty()) {
			if (estPrioritaire()) {
				return true;
			}
			return false;
		}
		Bataille derniereBataille = pileBatailles.getLast();
		if (derniereBataille instanceof Parade p) {

			if (p.getType().equals(Type.FEU)||estPrioritaire()) {
				return true;
			} 
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
		if (donnerKmParcourus() + km > 1000) {
			return false;
		}
		return true;
	}

	private boolean estDepotFeuVerAutorise() {
		if (estPrioritaire()) {
			return false;
		}
		if (pileBatailles.isEmpty()) {
			return true;
		}
		Bataille sommet = pileBatailles.getLast();
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
			if (pileLimite.getLast() instanceof FinLimite) {
				return true;
			}
			return false;
		} else if (limite instanceof FinLimite) {
			if (!pileLimite.isEmpty()) {
				if (pileLimite.getLast() instanceof DebutLimite) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque att) {
			if (peutAvancer() && !estProtege(att)) {
				return true;
			}
			return false;
		}

		if (bataille instanceof Parade parade) {
			Type paradeType=parade.getType();
			if (paradeType==null) {
				throw new IllegalArgumentException("Paradetype est null\n");
			}
			if (paradeType.equals(Type.FEU)) {
				return estDepotFeuVerAutorise();
			} else if (!pileBatailles.isEmpty()) {
				if (pileBatailles.getLast() instanceof Attaque att) {
					if (att.getType().equals(paradeType) && !estProtege(att)) {
						return true;
					}
				}
				return false;
			}
		}
		return false; // n'arrivera pas car bataille est soit une attaque soit une parade.throw new
						// exception ?
	}

	public boolean estDepotAutorise(Carte c) {
		if (c instanceof Bataille b) {
			return estDepotBatailleAutorise(b);
		} else if (c instanceof Limite l) {
			return estDepotLimiteAutorise(l);
		} else if (c instanceof Borne bo) {
			return estDepotBorneAutorise(bo);
		}
		return true; // cas prioritaire a voir
	}

	// TP4
	// private Set<Botte> bottes = new HashSet<>();
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

	// Verifie si on est protege de l'attaque passe en parametre
	public boolean estProtege(Attaque att) {
		Type type = att.getType();
		return possedeBotte(new Botte(type));
	}
}
