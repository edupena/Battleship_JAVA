package fr.ensma.ia.cpo.projet.combat;

import fr.ensma.ia.cpo.projet.communication.ICommunications;
import fr.ensma.ia.cpo.projet.flotte.Navire;

public class TirNormal implements IAttaque{

	Navire nav;
	ICommunications comm;
	
	public TirNormal(Navire nav, ICommunications comm){
		this.nav=nav;
		this.comm=comm;
	}
	
	
	public Integer tir(Integer position, Integer nombre_joueur_adversaire) throws Exception {
		int attaque = 1;

		if(nav.partiesCaches()!=0){attaque=nav.partiesCaches();}		
		return comm.sendAttackAndGetFeedBack(nombre_joueur_adversaire, position, attaque);
		
	}

}