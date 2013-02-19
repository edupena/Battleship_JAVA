package fr.ensma.ia.cpo.projet.seeheim.controller;

import fr.ensma.ia.cpo.projet.plateau.GameMaster;
import fr.ensma.ia.cpo.projet.seeheim.interf.Interf;
import fr.ensma.ia.cpo.projet.seeheim.presentation.Presentation;


public class Defendre  implements IAutomateTransitions {
	
	Automate aut;
	Interf inter;
	Presentation fen;
	
	public Defendre(Automate aut, Presentation fen){		
		this.aut=aut;
		this.inter = aut.interf;
		this.fen=fen;
	}
	
	public void setInterf(Interf inter){this.inter=inter;}
	/* Transitions */		

	
	public void click(String arg_str[], Integer int_args[], Boolean bool_arg[]){
		Integer result = null;
		Boolean fin=false;

		
		
		/*
		 * Renvoie un integer.
		 * -1 si eau
		 * 0 si coule
		 * 1 si touche normal
		 * 2 si touche à fond
		 * 
		 */
		try {			
			result = inter.getGm().attaquer(1, arg_str[1], 0, int_args[0]);
			
			if (inter.getGm().isLocal()){
				fin=inter.getGm().isFinished(0) || inter.getGm().isFinished(1); 
			}
			
			if (fin){
				fen.fin(2);
				for (int i=0; i<100; i++){
					fen.showValue(i, 1, String.valueOf(inter.getGm().getValueCase(0, i)));
					fen.showValue(i, 2, String.valueOf(inter.getGm().getValueCase(1, i)));
				}				
				aut.setEtCourant(aut.getEtfin());
			}

		}
		catch (Exception e) {}

		fen.activerGrille(2);
		fen.showQuiAttaque(1);
		aut.setEtCourant(aut.getEtAttaque());
		
		for (int i=0; i<100; i++){			
			if (!inter.getGm().isCaseCachee(0, i)){
				fen.showValue(i, 1, String.valueOf((int)inter.getGm().getValueCase(0, i)));				
			}		
			if (!inter.getGm().isCaseCachee(1, i)){
				fen.showValue(i, 2, String.valueOf((int)inter.getGm().getValueCase(1, i)));
			}
		}
		
		/* On met a jour les puissances de l'adversaire */
		for (int i=0;i<5;i++){
			if (inter.getGm().getPuissance(i, 0)>0){
				fen.showAttaque(i, inter.getGm().getPuissance(i, 0));
			}
			else{
				fen.showAttaque(i,1);
			}
		}			

	}
	
}
