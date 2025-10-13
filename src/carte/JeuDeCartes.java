package carte;

public class JeuDeCartes {
	private Configuration[] cartesc = new Configuration[20];

	public String affichageJeuDeCartes() {
		if (cartesc == null)
			return "Aucun jeu créé.\n";
		StringBuilder bui = new StringBuilder();
		bui.append("JEU : \n");
		for (int i = 0; i < 20; i++) {
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
		for (int i = 0; i < 20; i++) {
			total += cartesc[i].getNbExemplaires();
		}

		Carte[] cartes = new Carte[total];

		for (int i = 0, index = 0; i < 20; i++) {
			for (int j = 0; j < cartesc[i].getNbExemplaires(); j++) {
				cartes[index] = cartesc[i].getCarte();
				index++;
			}
		}

		return cartes;
	}
	
	public boolean  checkCount() {
		//TODO
		return true;
	}

	//a suprimer ou modifier pour creer la config
	public void ajouterCarte(Carte carte, int nbExemplaires) {
		Configuration config = new Configuration(carte, nbExemplaires);
		cartesc[20] = config;
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
	}
}
