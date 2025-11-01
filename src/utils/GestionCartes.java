package utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import carte.Attaque;
import carte.Borne;
import carte.Botte;
import carte.Carte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.JeuDeCartes;
import carte.Parade;
import carte.Type;
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
	
	public Carte extraireit (List<Carte> lcarte) {
		int length=lcarte.size();
		int choix= rand.nextInt(length);
		int i = 0;
		Carte carte=null;
		for (ListIterator<Carte> iterator=lcarte.listIterator();iterator.hasNext()&&i<=choix;i++) {
			carte=iterator.next();
			if (i==choix) {
				iterator.remove();
			}
		}
		return carte;
	}
	
	public List<Carte> melanger (List<Carte> lcarte) {
		List<Carte> retour = new ArrayList<>();
		int n = lcarte.size();
		for (int i = 0;i<n;i++) {
			Carte c = extraire(lcarte);
			retour.add(c);
		}
		return retour;
	}
 	
	public boolean verifierMelange(List<Carte> lcarte,List<Carte> lcarte2) {
		
		JeuDeCartes jeutemp = new JeuDeCartes();
		Carte[] jeudecarte = jeutemp.donnerCartesSansRepetition();
		for (int i =0;i<jeudecarte.length;i++) {
			Carte cartetemp=jeudecarte[i];
			int freq1 = Collections.frequency(lcarte, cartetemp);
			int freq2 = Collections.frequency(lcarte2, cartetemp);
			if (freq1!=freq2) {
				return false;
			}
		}
		return true;
	}
	
	public List<Carte> getListCarteSansRepet(List<Carte> lcarte) {
		List<Carte> retour = new ArrayList<>(); 
		for (ListIterator<Carte> iterator=lcarte.listIterator();iterator.hasNext();) {
			Carte cartetemp = iterator.next();
			if (!retour.contains(cartetemp)) {
				retour.add(cartetemp);
			}
		}
		return retour;
	}
	
	public List<Carte> rassembler (List<Carte> lcarte) {
		List<Carte> retour= new ArrayList<>();
		List<Carte> sansrepet = getListCarteSansRepet(lcarte);
		
		for (ListIterator<Carte> iterator=sansrepet.listIterator();iterator.hasNext();) {
			Carte cartetemp = iterator.next();
		    for (ListIterator<Carte> iterator2 = lcarte.listIterator();iterator2.hasNext();) {
		    	Carte cartetemp2 = iterator2.next();
		    	if (cartetemp2.equals(cartetemp)) {
		    		retour.add(cartetemp2);
		    		iterator2.remove();
		    	}
		    }
			
		}
		
		
		return retour;
	}
	
	public boolean verifierRassemblement (List<Carte> lcarte) {
		ListIterator<Carte> iterator=lcarte.listIterator();iterator.hasNext();
		Carte cartetemp = iterator.next();
		
		for (;iterator.hasNext();) {
			Carte cartetemp2 = iterator.next();
			while (iterator.hasNext()&&cartetemp.equals(cartetemp2)) {
				cartetemp=cartetemp2;
				cartetemp2=iterator.next();
			}
			if (!iterator.hasNext()) {
				return true;
			}
			int position = iterator.nextIndex();
			

		    for (ListIterator<Carte> iterator2 = lcarte.listIterator(position);iterator2.hasNext();) {
		    	Carte cartetemp3 = iterator2.next();
		    	if (cartetemp.equals(cartetemp3)) {
		    		return false;
		    	}
		    }
			cartetemp=cartetemp2;
		}
		return true;
	}
	
	/*rassembler
	methode 1 qui parcours liste rassembler jusqua trouver un nouvel element
	methode 2 verifie que plus aucune occurence des element avant l'arret de methode 1
	apparaisse en suite de liste et modifie en fonction
	*/
}
