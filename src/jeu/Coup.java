package jeu;
import java.util.Objects;

import carte.*;

public class Coup {
	private Joueur joueur;
	private Joueur cible;
	private Carte cartejoue; 
	
	public Coup (Joueur j,Carte c,Joueur jc) {
		this.joueur=j;
		this.cible=jc;
		this.cartejoue=c;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	public Joueur getCible() {
		//test pour reparer
		if (cible==null) {
			return null;
		}
		
		return cible;
	}
	public Carte getCarte() {
		return cartejoue;
	}
	
	public boolean estValide() {
		if (cible==null) {
			return true;
		}
		if (cible.equals(joueur)) {
			if (cartejoue instanceof Attaque || cartejoue instanceof DebutLimite) {
				return false;
			} 
			ZoneDeJeu zonej=joueur.getZone();
			return zonej.estDepotAutorise(cartejoue);
		} else if (cartejoue instanceof Attaque || cartejoue instanceof DebutLimite) {
			ZoneDeJeu zonec=cible.getZone();
			return zonec.estDepotAutorise(cartejoue);
		}
		return false;
	}
	
	@Override
    public boolean equals(Object obj) {
		
        if (obj instanceof Coup cp) {
        	boolean casnull = true;
        	if (cp.getCible()==null&&cible==null) {
        		casnull = true;
        	} if (cp.getCible()==null||cible==null) {
        		casnull = false;
        	} else {
        		casnull = cp.getCible().equals(cible);
        	}
        	return cp.getCarte().equals(cartejoue)&&cp.getJoueur().equals(joueur)&&casnull;
        }
        return false;
	}

    @Override
    public int hashCode() {
    	//J'ai du Chatgpt car cible peut etre null mais je ne peux pas enlever cible de hashcode
    	return Objects.hash(joueur, cible, cartejoue);
        //return 31 *(joueur.toString().hashCode()+cible.toString().hashCode()+cartejoue.toString().hashCode());
    }
    
    @Override
    public String toString() {
    	if (cible==null) {
    		return " defausse la carte "+cartejoue.toString()+"\n";
    	} else {
    		return " depose la carte " +cartejoue.toString()+" dans la zone de jeu de "+cible.toString()+"\n";
    	}
    }
}
