package fr.ensma.ia.cpo.projet.seeheim.controller;

import fr.ensma.ia.cpo.projet.seeheim.interf.Interf;
import fr.ensma.ia.cpo.projet.seeheim.presentation.Presentation;





public class Fin implements IAutomateTransitions {
	
	Automate aut;
	Interf inter;
	Presentation fen;
	
	public Fin(Automate aut, Presentation fen){		
		this.aut=aut;
		this.inter = aut.interf;
		this.fen=fen;
	}
	
	public void setInterf(Interf inter){this.inter=inter;}	
	/* Transitions */		

	
	public void click(String arg_str[], Integer int_args[], Boolean bool_arg[]){
		
	
		aut.setEtCourant(aut.getEtInitial());
		fen.setVisible(false);
		aut=new Automate();
		
	}	
	
	

	
}
