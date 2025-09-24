package carte;

public class JeuDeCartes {
	public String affichageJeuDeCartes() {
		return "lol";
		
	}
	
	public Carte[] donnerCartes() {
		
	}
	
	private class Configuration {
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
