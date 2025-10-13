package utils;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import carte.Carte;
public class GestionCartes {
	Random rand= new Random();
	
	
	public Carte extraire (List<Carte> lcarte) {
		//supose non null
		int length=lcarte.size();
		int choix= rand.nextInt(length);
		Carte retour = lcarte.get(choix);
		lcarte.remove(choix);
		return retour;
		
	}
	
	public Carte extraire_it (List<Carte> lcarte) {
		int length=lcarte.size();
		int choix= rand.nextInt(length);
		int i = 0;
		Carte carte=null;
		for (ListIterator<Carte> iterator=lcarte.listIterator();iterator.hasNext()&&i>choix;i++) {
			carte=iterator.next();
			if (i==choix) {
				iterator.remove();
			}
		}
		return carte;
	}
 	
	
	/*rassembler
	methode 1 qui parcours liste rassembler jusqua trouver un nouvel element
	methode 2 verifie que plus aucune occurence des element avant l'arret de methode 1
	apparaisse en suite de liste et modifie en fonction
	*/
}
