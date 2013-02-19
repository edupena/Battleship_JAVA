package fr.ensma.ia.cpo.projet.combat;

import fr.ensma.ia.cpo.projet.flotte.Navire;

public class BlindageNormal implements IDefense {
	
	Navire nav;
		
	public BlindageNormal(Navire nav) {		
		this.nav=nav;		
	}
	
	public void blindage(){
		nav.increaseDefense(1);
	}
}

