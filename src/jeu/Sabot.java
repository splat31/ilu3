package jeu;

import carte.Carte;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sabot implements Iterable<Carte> {
    private Carte[] cartes;
    private int nbCartes;
    private int modCount = 0; // nombre de modifications structurelles (pour détection)
    
    @Override
    public Iterator<Carte> iterator() {
        return new SabotIterator();
    }

    /**
     * Constructeur : reçoit un tableau de cartes initial
     */
    public Sabot(Carte[] cartesInitiales) {
        this.cartes = new Carte[cartesInitiales.length];
        for (int i = 0; i < cartesInitiales.length; i++) {
            this.cartes[i] = cartesInitiales[i];
        }
        this.nbCartes = cartesInitiales.length;
    }

    /**
     * Indique si le sabot est vide.
     */
    public boolean estVide() {
        return nbCartes == 0;
    }

    /**
     * Ajoute une carte à la fin du sabot.
     * @throws IllegalStateException si le sabot est plein.
     */
    public void ajouterCarte(Carte carte) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Le sabot est plein, impossible d’ajouter une carte !");
        }
        cartes[nbCartes] = carte;
        nbCartes++;
        modCount++;
    }

    

    /**
     * Méthode piocher : retire et retourne la première carte du sabot.
     * @throws NoSuchElementException si le sabot est vide.
     */
    public Carte piocher() {
    	if (estVide()) {
    		throw new NoSuchElementException("Le sabot est vide !");
    	} 
    	Iterator<Carte> it = iterator();
    	Carte premiere = it.next();
    	it.remove(); // supprime la première carte
    	modCount++;
    	return premiere;
    }


    /**
     * Classe interne : itérateur du sabot.
     */
    private class SabotIterator implements Iterator<Carte> {
        private int cursor = 0;               // position actuelle
        private int expectedModCount = modCount; // pour détecter les modifications
        private boolean canRemove = false;    // contrôle pour remove()

        @Override
        public boolean hasNext() {
            return cursor < nbCartes;
        }

        @Override
        public Carte next() {
        	if (expectedModCount != modCount) {
                throw new IllegalStateException("Le sabot a été modifié pendant l’itération 1!");
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true;
            return cartes[cursor++];
        }

        @Override
        public void remove() {
            if (!canRemove || nbCartes < 1) {
                throw new IllegalStateException("remove() ne peut être appelé qu’après next()");
            }
            if (expectedModCount != modCount) {
                throw new IllegalStateException("Le sabot a été modifié pendant l’itération 2!");
            }

            // Décaler les cartes vers la gauche
            for (int i = cursor - 1; i < nbCartes - 1; i++) {
                cartes[i] = cartes[i + 1];
            }
            cartes[nbCartes - 1] = null;
            nbCartes--;
            cursor--;
            canRemove = false;
            modCount++;
            expectedModCount=modCount;
        }
    }
}
