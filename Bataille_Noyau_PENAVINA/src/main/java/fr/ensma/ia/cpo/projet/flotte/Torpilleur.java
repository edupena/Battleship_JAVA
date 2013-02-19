package fr.ensma.ia.cpo.projet.flotte;

import fr.ensma.ia.cpo.projet.communication.ICommunications;

public class Torpilleur extends Navire {
	
	public Torpilleur(ICommunications comm) {
		super(2,"Torpilleur",comm);
	}
}
