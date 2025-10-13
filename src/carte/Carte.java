package carte;

public abstract class Carte {

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carte carte) {
			return getClass() == carte.getClass();
		}
		return false;
	}

}
