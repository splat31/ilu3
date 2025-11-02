package testsFonctionnels;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


/* Permet de lancer tout les test a la suite 
 * L'ordre est censé ne pas etre respecte mais  sur plusieurs essai ce fut le meme ordre
 * Dernier coverage = 86.2%*/

@Suite
@SelectClasses({
    JeuDeCartesTest.class,
    SabotTest.class,
    SabotTest2.class,
    TestMethodeEquals.class,
    GestionCartesTest.class,
    ZoneDeJeuTest.class,
    JoueurTest.class,
    JeuTest.class
})
public class AllTests {
}
