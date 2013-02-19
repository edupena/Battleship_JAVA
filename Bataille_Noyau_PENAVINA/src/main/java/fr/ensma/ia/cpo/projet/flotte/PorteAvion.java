package fr.ensma.ia.cpo.projet.flotte;

import fr.ensma.ia.cpo.projet.communication.ICommunications;

public class PorteAvion extends Navire {
	
	public PorteAvion(ICommunications comm) {
		super(5,"Porte avion",comm);
	}
}
