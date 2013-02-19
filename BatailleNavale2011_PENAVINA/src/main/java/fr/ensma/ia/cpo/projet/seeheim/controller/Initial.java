package fr.ensma.ia.cpo.projet.seeheim.controller;

import java.util.ArrayList;

import fr.ensma.ia.cpo.projet.seeheim.interf.Interf;
import fr.ensma.ia.cpo.projet.seeheim.presentation.Presentation;





public class Initial implements IAutomateTransitions{
	
	Automate aut;
	Interf inter;
	Presentation fen;
		
		public Initial(Automate aut, Presentation fen){		
			this.aut=aut;
			this.inter = aut.interf;
			this.fen=fen;
		}
		
		public void setInterf(Interf inter){this.inter=inter;}
		/* Transitions */
		
		public void click(String arg_str[], Integer int_args[], Boolean bool_arg[]){
						
			Boolean ia;
			Boolean local;
			String ip=arg_str[2];
			Integer port=int_args[1];
			Boolean first_attacking;
			Integer niveau=0;
			Boolean tricher=bool_arg[0];
			
			
			/* On dit l'Interface de creer le jeu*/
			if (arg_str[0]=="Jouer en local: Joueur1 VS Ordi"){
				ia=true;
				local=true;
				niveau=1;
			}
			else if (arg_str[0]=="Jouer en réseau"){
				ia=false;
				local=false;				
			}
			else{
				local=true;
				ia=false;				
			}
			
			
						
			
			inter = new Interf(ia, niveau, tricher, local, ip, port);
			aut.setInterface(inter);
			aut.getEtAttaque().setInterf(inter);
			aut.getEtDefendre().setInterf(inter);
			aut.getEtfin().setInterf(inter);
			aut.getEtInitial().setInterf(inter);
			
			
			fen.Jeu();
			
			fen.cacherGrille(1);
			fen.cacherGrille(2);
			
			fen.jeuEnCours();
			
			
			fen.activerGrille(1);
			fen.activerGrille(2);

			
			
			if(!this.inter.getGm().isLocal()){
				first_attacking=inter.getGm().getLastValueOfIp(inter.getGm().getMyIp())>inter.getGm().getLastValueOfIp(ip);				
				if (!first_attacking){					
					Integer cons;
					
						
						ArrayList<Integer> def;
						do{
							def =inter.getGm().waitForAttack();
						}while(def==null);
						
						cons=this.inter.getGm().attaquer(def.get(0),def.get(1));					
												
					
					
				}
			}
					
			if (inter.getGm().isTricher()){
				for (int i=0; i<100; i++){
					fen.showOnlyText(i, 1, String.valueOf(inter.getGm().getValueCase(0, i)));
					if (inter.getGm().isLocal()){
						fen.showOnlyText(i, 2, String.valueOf(inter.getGm().getValueCase(1, i)));
					}
					else{fen.showValue(i, 2, "?");}
				}
			}
			else{
				for (int i=0; i<100; i++){
					fen.showValue(i, 1, "?");
					fen.showValue(i, 2, "?");
				}
			}
			
			aut.setEtCourant(aut.getEtAttaque());
			fen.showQuiAttaque(1);
						
		}

		
		
}
