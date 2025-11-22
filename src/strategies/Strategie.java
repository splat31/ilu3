package strategies;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import jeu.Coup;

public interface Strategie {
	
	default Set<Coup> trierCoups(Set<Coup> ensemble) {
		Comparator<Coup> comp = (c1, c2) -> {
	        if (c1.equals(c2)) return 0;
	        Random r = new Random();
	        int valeur = r.nextBoolean() ? 1 : -1;
	        return valeur;

	    };
	    
		 return new TreeSet<>(comp) {{
		        addAll(ensemble);
		    }};
	}
}
