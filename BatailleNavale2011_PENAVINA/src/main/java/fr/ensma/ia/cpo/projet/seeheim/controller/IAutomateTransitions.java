package fr.ensma.ia.cpo.projet.seeheim.controller;

import fr.ensma.ia.cpo.projet.seeheim.interf.Interf;

public interface IAutomateTransitions {
			
	public void click(String arg_str[], Integer int_args[], Boolean bool_arg[]);
	
	public void setInterf(Interf inter);
	
}
