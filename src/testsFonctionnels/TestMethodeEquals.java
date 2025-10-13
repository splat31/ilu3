package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carte.Attaque;
import carte.Borne;
import carte.Parade;
import carte.Type;

class TestMethodeEquals {
	Borne borne1; 
	Borne borne2; 
	Attaque feur1; 
	Attaque feur2; 
	Parade feuv1;

	@BeforeEach
	void setUp() throws Exception {
		borne1 = new Borne(25);
		borne2 = new Borne(25);
		feur1 = new Attaque(Type.FEU);
		feur2 = new Attaque(Type.FEU);
		feuv1 = new Parade(Type.FEU);
	}

	@Test
	void test() {	
		assert(borne1.equals(borne2)==true);
		assert(feur1.equals(feur2)==true);
		assert(feur1.equals(feuv1)==false);
	}

}
