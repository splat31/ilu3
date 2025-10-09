package histoire;

import carte.Attaque;
import carte.Borne;
import carte.Botte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.JeuDeCartes;
import carte.Limite;
import carte.Parade;
import carte.Type;

public class scenario {
	public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        //jeu.creertableau(18);

        // 🟦 Cartes de distance
        jeu.ajouterCarte(new Borne(25), 10);
        jeu.ajouterCarte(new Borne(50), 10);
        jeu.ajouterCarte(new Borne(75), 10);
        jeu.ajouterCarte(new Borne(100), 12);
        jeu.ajouterCarte(new Borne(200), 4);

        // 🟩 Cartes de parade
        jeu.ajouterCarte(new Parade(Type.FEU), 14); // Feu Vert
        jeu.ajouterCarte(new FinLimite(),6);
        jeu.ajouterCarte(new Parade(Type.ESSENCE), 6);        // Essence
        jeu.ajouterCarte(new Parade(Type.CREVAISON), 6);      // Roue de secours
        jeu.ajouterCarte(new Parade(Type.ACCIDENT), 6);       // Réparation

        // 🟥 Cartes d’attaque
        jeu.ajouterCarte(new Attaque(Type.FEU), 5);  // Feu Rouge
        jeu.ajouterCarte(new DebutLimite(),4);
        jeu.ajouterCarte(new Attaque(Type.ESSENCE), 3);       // Panne d’essence
        jeu.ajouterCarte(new Attaque(Type.CREVAISON), 3);     // Crevaison
        jeu.ajouterCarte(new Attaque(Type.ACCIDENT), 3);      // Accident

        // 🟨 Cartes de botte
        jeu.ajouterCarte(new Botte(Type.FEU), 1);             // Prioritaire
        jeu.ajouterCarte(new Botte(Type.ESSENCE), 1);         // Citerne
        jeu.ajouterCarte(new Botte(Type.CREVAISON), 1);       // Increvable
        jeu.ajouterCarte(new Botte(Type.ACCIDENT), 1);        // As du volant

        // ✅ Affichage du résultat attendu
        System.out.println(jeu.affichageJeuDeCartes());
    }
}
