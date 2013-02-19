package fr.ensma.ia.cpo.projet.seeheim.controller;

import java.util.ArrayList;

import fr.ensma.ia.cpo.projet.plateau.GameMaster;
import fr.ensma.ia.cpo.projet.seeheim.interf.Interf;
import fr.ensma.ia.cpo.projet.seeheim.presentation.Presentation;



public class Attaquer  implements IAutomateTransitions {
	
	Automate aut;
	Interf inter;
	Presentation fen;
	
	public Attaquer(Automate aut, Presentation fen){		
		this.aut=aut;
		this.inter = aut.interf;
		this.fen=fen;
	}
	
	public void setInterf(Interf inter){this.inter=inter;}
	
	/* Transitions */		
	
	public void click(String arg_str[], Integer int_args[], Boolean bool_arg[]){
		Integer result = null;
		Boolean fin = false;
		
		/*
		 * Renvoie un integer.
		 * -1 si eau
		 * 0 si coule
		 * 1 si touche normal
		 * 2 si touche à fond
		 * 
		 */
				
	

		if (arg_str[1]==null){ arg_str[1]="Porte avion"; }
		try {
			Integer cons;
			
				
			result = inter.getGm().attaquer(0, arg_str[1], 1, int_args[0]);			
					
			
			if (inter.getGm().isLocal()){

				fin=inter.getGm().isFinished(0) || inter.getGm().isFinished(1); 
			}
			
			if (fin){
				fen.fin(1);
				for (int i=0; i<100; i++){
					fen.showValue(i, 1, String.valueOf(inter.getGm().getValueCase(0, i)));
					fen.showValue(i, 2, String.valueOf(inter.getGm().getValueCase(1, i)));
				}
				aut.setEtCourant(aut.getEtfin());
			}
			else{
				switch (result){
					case -1:												
						if (!inter.getGm().isLocal()){
							/* Attendre attaque réseau*/
															
								ArrayList<Integer> def;
								do{
									def =inter.getGm().waitForAttack();
								}while(def==null);
																
								cons=this.inter.getGm().attaquer(def.get(0),def.get(1));								
																
							}
						break;
					case 0:						
						
						break;
					case 1:

						break;
					case 2:											
						break;			
				}
			}
			
		}
		catch (Exception e) { e.printStackTrace();}
		
		
		if (inter.getGm().isLocal()){
			if (inter.getGm().isIa()){
				/* Attaque ia */					
					try {
						Integer pos;
						if (inter.getGm().isTricher()){
							pos=inter.getGm().attaqueAuto(2);
						}
						else {pos=inter.getGm().attaqueAuto(1);}
						
						this.inter.getGm().attaquer(1, "Torpilleur", 0, pos);
														
					} catch (Exception e) { e.printStackTrace();}
			}

		}
		
		
		
		
		if (inter.getGm().isLocal() && !inter.getGm().isIa()){
			fen.activerGrille(1);
			fen.showQuiAttaque(2);
			aut.setEtCourant(aut.getEtDefendre());
		}
		
		for (int i=0; i<100; i++){			
			if (!inter.getGm().isCaseCachee(0, i)){
				fen.showValue(i, 1, String.valueOf((int)inter.getGm().getValueCase(0, i)));				
			}		
			if (!inter.getGm().isCaseCachee(1, i)){
				fen.showValue(i, 2, String.valueOf((int)inter.getGm().getValueCase(1, i)));
			}
		}
		

		/* On met a jour les puissances de l'adversaire */
		if (inter.getGm().isLocal() && !inter.getGm().isIa()){
			for (int i=0;i<5;i++){
				if (inter.getGm().getPuissance(i, 1)>0){
					fen.showAttaque(i, inter.getGm().getPuissance(i, 1));
				}
				else{
					fen.showAttaque(i,1);
				}
			}			
		}
		else{
			for (int i=0;i<5;i++){
				if (inter.getGm().getPuissance(i, 0)>0){
					fen.showAttaque(i, inter.getGm().getPuissance(i, 0));
				}
				else{
					fen.showAttaque(i, 1);
				}
			}			
		}

		
	}
}
	

