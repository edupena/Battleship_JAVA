package fr.ensma.ia.cpo.projet.flotte;

import fr.ensma.ia.cpo.projet.communication.ICommunications;

public class SousMarin extends Navire {
	
	public SousMarin(ICommunications comm) {
		super(3,"Sous marin",comm);
	}
}
