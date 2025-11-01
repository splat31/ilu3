package jeu;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import carte.*;

public class MainJoueur implements Iterable<Carte> {
	private List<Carte> main = new ArrayList<>();
	private int modCount = 0;
	
	@Override
	public String toString() {
		StringBuilder builder =null;
		builder.append("possède: ");
		for (Carte carte : main ) {
			builder.append(carte.toString());
		}
		return builder.toString();
	}
	
	void prendre(Carte carte) {
		main.add(carte);
		modCount++;
	}
	
	void jouer(Carte carte) {
		assertTrue(main.contains(carte));
		for (ListIterator<Carte> iterator= main.listIterator();iterator.hasNext();) {
			Carte carte2= iterator.next();
			if (carte.equals(carte2)) {
				iterator.remove();
				modCount++;
				return;
			}
		}
	}

	@Override
	public Iterator<Carte> iterator() {
		return new MainIterator();
	}
	
	private class MainIterator implements Iterator<Carte> {
        private int cursor = 0;               // position actuelle
        private int expectedModCount = modCount; // pour détecter les modifications
        private boolean canRemove = false;    // contrôle pour remove()

        @Override
        public boolean hasNext() {
            return cursor < main.size();
        }

        @Override
        public Carte next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true;
            return main.get(cursor++);
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("remove() ne peut être appelé qu’après next()");
            }
            if (expectedModCount != modCount) {
                throw new IllegalStateException("La main a été modifié pendant l’itération !");
            }

            // Décaler les cartes vers la gauche
            main.remove(--cursor);
            canRemove = false;
            modCount++;
            expectedModCount++;
        }
    }
}

