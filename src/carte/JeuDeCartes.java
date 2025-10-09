package carte;

public class JeuDeCartes {
	int nbconfig=0;
	private Configuration[]  cartesc = new Configuration[20];
	public String affichageJeuDeCartes() {
		if (cartesc == null) return "Aucun jeu créé.\n";
        StringBuilder bui = new StringBuilder();
        bui.append("JEU : \n");
        for (int i = 0; i < nbconfig; i++) {
            int nbexemplaires = cartesc[i].getNbExemplaires();
            String nomCarte = cartesc[i].getCarte().toString();
            bui.append(nbexemplaires).append(" ").append(nomCarte).append("\n");
        }
        return bui.toString();
	}
	
	public Carte[] donnerCartes() {
		if (cartesc == null) return new Carte[0];

        // 1. Compter le nombre total de cartes
        int total = 0;
        for (int i = 0; i < nbconfig; i++) {
            total += cartesc[i].getNbExemplaires();
        }

        // 2. Créer le tableau final
        Carte[] cartes = new Carte[total];

        // 3. Remplir le tableau
        int index = 0;
        for (int i = 0; i < nbconfig; i++) {
            for (int j = 0; j < cartesc[i].getNbExemplaires(); j++) {
                cartes[index] = cartesc[i].getCarte();
                index++;
            }
        }

        return cartes;
	}
	/*
	public void creertableau(int taille) {
	    cartesc = new Configuration[taille];
	    nbconfig = 0; // on réinitialise le compteur de configurations
	}*/

	public void ajouterCarte (Carte carte, int nbExemplaires) {
		Configuration config= new Configuration(carte,nbExemplaires);
		cartesc[nbconfig]=config;
		nbconfig++;
	}
	
	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;
		private Configuration (Carte carte, int nbExemplaires) {
			this.carte=carte;
			this.nbExemplaires=nbExemplaires;
		}
		public int getNbExemplaires() {
			return nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
	}
}
