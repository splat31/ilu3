package histoire;
import java.util.ArrayList;
import java.util.List;
import carte.Attaque;
import carte.Borne;
import carte.Botte;
import carte.Carte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.JeuDeCartes;
import carte.Limite;
import carte.Parade;
import carte.Type;
import utils.GestionCartes;

public class scenario {
	public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        JeuDeCartes jeu2 = new JeuDeCartes();
        
        //System.out.println(jeu.affichageJeuDeCartes());
        Carte[] jeux = jeu.donnerCartes();
        for (int i = 0; i<jeux.length;i++) {
        	System.out.println(jeux[i]);
        }
        if (jeu.checkCount(jeux)) {
        	System.out.println("donnerCartes marche");
        } else {
        	System.out.println("donnerCartes marche pas");
        }
        
        GestionCartes gestion = new GestionCartes();
        List<Carte> cartesl = new ArrayList<>();
        for (int i = 0; i<jeux.length;i++) {
        	cartesl.add(jeux[i]);
        }
           

        //Carte extraite1 = gestion.extraire(cartesl);
        //Carte extraite2 = gestion.extraireit(cartesl);
        //System.out.println("j'ai extrait "+extraite1+"\n");
        //System.out.println("j'ai extrait "+extraite2+"\n");
        System.out.println("JE MELANGE\n");
        List<Carte> cartes2 = gestion.melanger(cartesl);
        for (int i = 0; i<cartes2.size();i++) {
        	System.out.println(cartes2.get(i));
        }
        
        /*
        List<Carte> cartesl2 = new ArrayList<>();
        for (int i = 0; i<jeux.length;i++) {
        	cartesl.add(jeux[i]);
        }*/
        
        
        
    }
}
