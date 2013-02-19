package fr.ensma.ia.cpo.projet.seeheim.controller;

import fr.ensma.ia.cpo.projet.seeheim.interf.Interf;
import fr.ensma.ia.cpo.projet.seeheim.presentation.Presentation;

public class Automate{

	Interf interf;
	
	IAutomateTransitions etAttaque;
	IAutomateTransitions etDefendre;
	IAutomateTransitions etfin;
	IAutomateTransitions etInitial;

	IAutomateTransitions etCourant;		
	Presentation fen;
	
	public Automate(){
						
				
		
		/* On initialice la présentation aussi*/
		
		interf = new Interf();
		Presentation fen = new Presentation(this, interf.getGm().getMyIp());
		etAttaque= new Attaquer(this, fen); 
		etDefendre = new Defendre(this, fen);
		etfin = new Fin(this, fen);
		etInitial = new Initial(this, fen);
		
		
		this.setEtCourant(etInitial); 		
	}	
	
	public IAutomateTransitions getEtCourant() {return etCourant;	}
	public void setEtCourant(IAutomateTransitions etCourant) {this.etCourant = etCourant;}
	
	/* API pour les transitions */
	
	public IAutomateTransitions getEtAttaque() {return etAttaque;}
	public IAutomateTransitions getEtDefendre() { return etDefendre;}
	public IAutomateTransitions getEtfin() {return etfin;}
	public IAutomateTransitions getEtInitial() {return etInitial;}
	
	
	public void setInterface(Interf interf){this.interf=interf;}
	
	public Interf getInterface(){ return this.interf;}
	
	
	public void click(String arg_str[], Integer int_args[], Boolean bool_arg[]){
		this.getEtCourant().click(arg_str,int_args, bool_arg);
		}	
	

	public static void main (String args[]){
		
		Automate aut = new Automate();
		
		
	}
	
}

