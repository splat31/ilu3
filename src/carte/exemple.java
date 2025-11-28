package carte;

public interface exemple {
	public static final String SE_STRING = "lol";
	
	public abstract int lol ();
	
	public default String lol2() {
		return "lol2";
	}
	
	public static void teststatic() {
		System.out.println("Fonction statique");
		//on peut faire exemple.teststatic mtn et exmple c directement le nom de l'interface
	}
}
