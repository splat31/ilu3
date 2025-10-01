package carte;

public class Botte extends Probleme {
	/*public Gaulois(String nom, int force) {
		super(nom, force);
	}*/
	public Botte(Type type) {
	 super(type);
	}
	
	@Override
	public String toString() {
		return getType().getParade();
	}

}
