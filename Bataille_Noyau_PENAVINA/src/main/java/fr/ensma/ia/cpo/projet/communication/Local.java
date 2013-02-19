package fr.ensma.ia.cpo.projet.communication;

import java.util.ArrayList;


import fr.ensma.ia.cpo.projet.plateau.GameMaster;
import fr.ensma.ia.cpo.projet.plateau.ICase;
import fr.ensma.ia.cpo.projet.plateau.Joueur;

public class Local implements ICommunications{
	
	GameMaster gm;	
	
	
	public Local (GameMaster gm){
		this.gm=gm;		
	}
	

	public Integer sendAttackAndGetFeedBack(Integer nombre_joueur_adversaire, Integer nombre_case, Integer puissance) throws Exception {
		
		ArrayList<Joueur> j =gm.getJoueurs();
		Joueur adv = j.get(nombre_joueur_adversaire);
		ICase[] grille_adversaire = adv.getLa_grille().getGrille();
		
		Integer etat_case = grille_adversaire[nombre_case].getEtat();
		
		/* Si eau */
			
		//grille_adversaire[nombre_case].setDecouvert(true);
		
		if (etat_case==-1){			
			return -1;
		}	
		
		/* Si pas eau */
		else{
			
			Integer etat_final=etat_case-puissance;
			/* Si touché normal */
			if (etat_final>0){
				grille_adversaire[nombre_case].setEtat(etat_final);
				return 1;
			}
			else{
				grille_adversaire[nombre_case].setEtat(0);
				/* Si coulé */
				if (gm.getJoueurs().get(nombre_joueur_adversaire).getNavireInCase(nombre_case).isCoule()){
					grille_adversaire[nombre_case].setEtat(0);
					return 0;
				}
				
				/* Si touché à fond */
				else {					
					return 2;
				}
				
			}
		}
		
		
	}	

	public ArrayList<Integer> waitForAttackAndInform() throws Exception {		
		return null;
	}

	public int[] retrieveGridLimits() throws Exception {


		
		return null;
	}

	public void sendGridLimits(int[] limites) throws Exception {


		
		
	}

}
