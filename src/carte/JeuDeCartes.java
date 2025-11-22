package carte;

import java.util.LinkedHashMap;
import java.util.Map;

public class JeuDeCartes {
	Map<Carte, Integer> cartes = new LinkedHashMap<>();
	public JeuDeCartes() {
	    cartes.put(new Borne(25), 10);
	    cartes.put(new Borne(50), 10);
	    cartes.put(new Borne(75), 10);
	    cartes.put(new Borne(100), 12);
	    cartes.put(new Borne(200), 4);

	    cartes.put(new Parade(Type.FEU), 14);
	    cartes.put(new FinLimite(), 6);
	    cartes.put(new Parade(Type.ESSENCE), 6);
	    cartes.put(new Parade(Type.CREVAISON), 6);
	    cartes.put(new Parade(Type.ACCIDENT), 6);

	    cartes.put(new Attaque(Type.FEU), 5);
	    cartes.put(new DebutLimite(), 4);
	    cartes.put(new Attaque(Type.ESSENCE), 3);
	    cartes.put(new Attaque(Type.CREVAISON), 3);
	    cartes.put(new Attaque(Type.ACCIDENT), 3);

	    cartes.put(new Botte(Type.FEU), 1);
	    cartes.put(new Botte(Type.ESSENCE), 1);
	    cartes.put(new Botte(Type.CREVAISON), 1);
	    cartes.put(new Botte(Type.ACCIDENT), 1);
	}
	
	public String affichageJeuDeCartes() {
		if (cartes.isEmpty())
			return "Aucun jeu créé.\n";
		StringBuilder bui = new StringBuilder();
		bui.append("JEU : \n");
		for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
			int nbexemplaires = entry.getValue();
			Carte nomCarte = entry.getKey();
			bui.append(nbexemplaires).append(" ").append(nomCarte).append("\n");
		}
		return bui.toString();
	}
	
	public Carte[] donnerCartes() {
		if (cartes.isEmpty())
			return new Carte[0];

		int total = 0;
		for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
			int nbexemplaires = entry.getValue();
			total=total+nbexemplaires;
		}

		Carte[] cartesr = new Carte[total];
		int index = 0;
		for (Map.Entry<Carte, Integer> entry2 : cartes.entrySet()) {
			int n = entry2.getValue();
			Carte nomCarte = entry2.getKey();
			for (int i = 0;i<n;i++) {
				cartesr[index]=nomCarte;
				index++;
			}
		}

		return cartesr;
	}
	
	public Carte[] donnerCartesSansRepetition() {
		if (cartes.isEmpty())
			return new Carte[0];
		Carte[] cartesr = new Carte[cartes.size()];
		int i = 0;
		for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
			cartesr[i]=entry.getKey();
			i++;
		}
		return cartesr;
	}
	
	public boolean  checkCount(Carte[] totest) {
		int index = 0;
		for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
			Carte tempcheck = entry.getKey();
			int tempnb = entry.getValue();
			for (int j = 0; j < tempnb; j++) {
				if (!totest[index].equals(tempcheck)) {
					return false;
				}
				index++;
			}
		}
		return true;
	}
	
	//ANCIENNE VERSION AVEC CONFIGURATION
	/*private Configuration[] cartesc = {new Configuration(new Borne(25), 10),
			new Configuration(new Borne(50), 10),
			new Configuration(new Borne(75), 10),
			new Configuration(new Borne(100), 12),
			new Configuration(new Borne(200), 4),
			new Configuration(new Parade(Type.FEU), 14),
			new Configuration(new FinLimite(),6),
			new Configuration(new Parade(Type.ESSENCE), 6),
			new Configuration(new Parade(Type.CREVAISON), 6),
			new Configuration(new Parade(Type.ACCIDENT), 6),
			new Configuration(new Attaque(Type.FEU), 5),
			new Configuration(new DebutLimite(),4),
			new Configuration(new Attaque(Type.ESSENCE), 3),
			new Configuration(new Attaque(Type.CREVAISON), 3),
			new Configuration(new Attaque(Type.ACCIDENT), 3),
			new Configuration(new Botte(Type.FEU), 1),
			new Configuration(new Botte(Type.ESSENCE), 1),
			new Configuration(new Botte(Type.CREVAISON), 1),
			new Configuration(new Botte(Type.ACCIDENT), 1),
			};

			
	public String affichageJeuDeCartes() {
		if (cartesc == null)
			return "Aucun jeu créé.\n";
		StringBuilder bui = new StringBuilder();
		bui.append("JEU : \n");
		for (int i = 0; i < cartesc.length; i++) {
			int nbexemplaires = cartesc[i].getNbExemplaires();
			Carte nomCarte = cartesc[i].getCarte();
			bui.append(nbexemplaires).append(" ").append(nomCarte).append("\n");
		}
		return bui.toString();
	}

	public Carte[] donnerCartes() {
		if (cartesc == null)
			return new Carte[0];

		int total = 0;
		for (int i = 0; i < cartesc.length; i++) {
			total += cartesc[i].getNbExemplaires();
		}

		Carte[] cartes = new Carte[total];

		for (int i = 0, index = 0; i < cartesc.length; i++) {
			for (int j = 0; j < cartesc[i].getNbExemplaires(); j++) {
				cartes[index] = cartesc[i].getCarte();
				index++;
			}
		}
		return cartes;
	}
	
	public Carte[] donnerCartesSansRepetition() {
		if (cartesc == null)
			return new Carte[0];
		Carte[] cartes = new Carte[cartesc.length];

		for (int i = 0; i < cartesc.length; i++) {
			cartes[i] = cartesc[i].getCarte();
		}
		return cartes;
	}
	
	public boolean  checkCount(Carte[] totest) {
		for (int i = 0, index = 0; i < cartesc.length; i++) {
			Carte tempcheck = cartesc[i].getCarte();
			int tempnb = cartesc[i].getNbExemplaires();
			for (int j = 0; j < tempnb; j++) {
				if (!totest[index].equals(tempcheck)) {
					return false;
				}
				index++;
			}
		}
		return true;
	}

	
	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;

		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
	}*/
}
