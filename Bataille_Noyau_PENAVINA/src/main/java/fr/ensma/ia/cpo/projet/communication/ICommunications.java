package fr.ensma.ia.cpo.projet.communication;

import java.util.ArrayList;

public interface ICommunications {
	
	/*
	 * Renvoie un integer.
	 * -1 si eau
	 * 0 si coule
	 * 1 si touche normal
	 * 2 si touche à fond
	 * 
	 */

	public Integer sendAttackAndGetFeedBack(Integer nombre_joueur_adversaire, Integer nombre_case, Integer puissance) throws Exception;	
	
	public ArrayList<Integer> waitForAttackAndInform() throws Exception;
	public int[] retrieveGridLimits() throws Exception;
	public void sendGridLimits(int limites[]) throws Exception;

}