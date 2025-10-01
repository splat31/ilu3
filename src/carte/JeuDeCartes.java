package carte;

public class JeuDeCartes {
	int nbconfig=0;
	private Configuration[] cartesc=null;
	public String affichageJeuDeCartes() {
		StringBuilder bui = new StringBuilder();
		bui.append("JEU : /n");
		int nbexemplaires;
		String nomCarte;
		for (int i=0;i<cartesc.length;i++) {
			nbexemplaires = cartesc[i].getNbExemplaires();
			nomCarte = cartesc[i].getCarte().toString();
			bui.append(nbexemplaires);
			bui.append(" "+nomCarte + "/n");
		}
		return bui.toString();
	}
	
	public Carte[] donnerCartes() {
		Carte[] cartes = null;
		int nbcarte=0;
		for (int i= 0;i>nbconfig;i++) {
			for (int j=0;j<cartesc[i].getNbExemplaires();j++) {
				cartes[nbcarte]=cartesc[i].getCarte();
				nbcarte++;
			}
		}
		/*
		for (Configuration configuration : cartesc) {
			if (configuration.getNbExemplaires() > 1) {
				cartes[i]=configuration.getCarte();
				i++;
			}
		}*/
		return cartes;
	}
	public Configuration creertableau (int) {
		
	}
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
