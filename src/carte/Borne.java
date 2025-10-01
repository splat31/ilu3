package carte;

public class Borne extends Carte{
	private int km;
	public Borne(int km) {
		this.km=km;
	}
	private int getKm() {
		return km;
	}

	@Override
	public String toString() {
		return getKm()+"KM";
	}
}
