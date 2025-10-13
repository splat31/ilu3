package testsFonctionnels;

import carte.*;
import jeu.Sabot;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SabotTest {

    /**
     * a) Test : piocher toutes les cartes jusqu’à ce que le sabot soit vide
     */
    @Test
    void testPiocher() {
        Carte[] cartes = {
            new Borne(25),
            new Borne(25),
            new Borne(50),
            new Attaque(Type.FEU),
            new Parade(Type.FEU),
            new Botte(Type.ACCIDENT)
        };

        Sabot sabot = new Sabot(cartes);

        while (!sabot.estVide()) {
            Carte c = sabot.piocher();
            System.out.println("je pioche " + c);
        }

        assertTrue(sabot.estVide(), "Le sabot devrait être vide après avoir tout pioché.");
    }

    /**
     * b) Test : même résultat en utilisant un itérateur et remove()
     */
    @Test
    void testIterateurRemove() {
        Carte[] cartes = {
            new Borne(25),
            new Borne(25),
            new Borne(50),
            new Attaque(Type.FEU),
            new Parade(Type.FEU),
            new Botte(Type.ACCIDENT)
        };

        Sabot sabot = new Sabot(cartes);

        Iterator<Carte> it = sabot.iterator();
        while (it.hasNext()) {
            Carte c = it.next();
            System.out.println("je pioche " + c);
            it.remove(); // supprime la carte piochée
        }

        assertTrue(sabot.estVide(), "Le sabot doit être vide après suppression de toutes les cartes.");
    }
    
    
    
    
    
    
    
    
    

    /**
     * c) Test : exceptions attendues
     * - piocher pendant une itération
     * - ajouter une carte pendant une itération
     */
    /*
     * 
    @Test
    void testExceptions() {
        Carte[] cartes = {
            new Borne(25),
            new Borne(50),
            new Botte(Type.ACCIDENT)
        };

        // --- Cas 1 : piocher pendant une itération ---
        Sabot sabot = new Sabot(cartes);
        Iterator<Carte> it = sabot.iterator();

        assertThrows(IllegalStateException.class, () -> {
            sabot.piocher(); // modification concurrente
            it.next();       // déclenche IllegalStateException
        }, "Piocher pendant une itération doit provoquer une IllegalStateException.");

        // --- Cas 2 : ajouter une carte pendant une itération ---
        sabot = new Sabot(cartes);
        it = sabot.iterator();
        if (it.hasNext()) it.next(); // avance d’un cran

        assertThrows(IllegalStateException.class, () -> {
            sabot.ajouterCarte(new Botte(Type.FEU)); // modification concurrente
            it.next();                               // déclenche IllegalStateException
        }, "Ajouter une carte pendant une itération doit provoquer une IllegalStateException.");
    }*/
}
