package carte;

public abstract class Probleme extends Carte {
	/*public Gaulois(String nom, int force) {
		super(nom, force);
	}*/
	
	private Type type;
	
	protected Probleme(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
	
	public abstract String toString(); //pour me forcer à redéfinir toString

}
