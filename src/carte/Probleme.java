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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Probleme carte) {
			return getClass()==carte.getClass()&&getType()==carte.getType();
		}
		return false;
	}
	
	public abstract String toString(); //pour me forcer � red�finir toString

}
