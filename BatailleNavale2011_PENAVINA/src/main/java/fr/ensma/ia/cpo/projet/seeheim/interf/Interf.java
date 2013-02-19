package fr.ensma.ia.cpo.projet.seeheim.interf;

import fr.ensma.ia.cpo.projet.plateau.GameMaster;

public class Interf {
	
	GameMaster gm;
	
	public Interf(){
		gm=new GameMaster();
		
	}
	
	public Interf (Boolean ia, Integer niveau, Boolean tricher, Boolean local, String ip, Integer port){
		
		gm=new GameMaster(ia, niveau, tricher, local, ip, port);

	}

	public GameMaster getGm() {
		return gm;
	}
	
	

}
