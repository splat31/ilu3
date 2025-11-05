package jeu;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import carte.*;

public class MainJoueur implements Iterable<Carte> {
	private List<Carte> main = new ArrayList<>();
	private int modCount = 0;

	@Override
	public String toString() {
		if (main.isEmpty()) {
			return "la main est vide";
		}
		StringBuilder builder = new StringBuilder();
		for (Carte carte : main) {
			builder.append(carte.toString() + " ");
		}
		return builder.toString();
	}

	public void prendre(Carte carte) {
		main.add(carte);
		modCount++;
	}

	public void jouer(Carte carte) {
		assertTrue(main.contains(carte));
		main.remove(carte);
// une seule instruction
//		Iterator<Carte> it = iterator();
//		while (it.hasNext()) {
//			Carte c = it.next();
//			if (c.equals(carte)) {
//				it.remove();
//				return;
//			}
//		}
	}

	public void jouerderniere() {
		// VOLONTAIREMENT TRES NUL C'est pour tester plus facilement
		assertTrue(!main.isEmpty());
		Carte carte = main.getLast();
		Iterator<Carte> it = iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.equals(carte)) {
				it.remove();
				return;
			}
		}

	}

	public boolean contient(Carte c) {
		return main.contains(c);
	}

	// Pour les tests aussi
	public boolean MainIsEmpty() {
		return main.isEmpty();
	}

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}

	
}
