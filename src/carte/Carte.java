package carte;

public abstract class Carte {

	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass();
	}

}
