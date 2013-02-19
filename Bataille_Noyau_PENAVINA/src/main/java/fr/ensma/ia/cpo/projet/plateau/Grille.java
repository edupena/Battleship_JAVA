package fr.ensma.ia.cpo.projet.plateau;

import fr.ensma.ia.cpo.projet.plateau.Case;

public class Grille implements IGrille{
	
	private Constantes cons;
	private ICase grille[];
	
	/**
	 * Constructeur d'une grille classique 10x10
	 */
	public Grille() {
		this.cons=new Constantes(2);
		grille = new Case[cons.nbecase];
		for (int i=0;i<cons.nbecase;i++) {
			this.grille[i]=new Case(cons,i);
		};
	}
	
	/**
	 * Constructeur d'une grille respectant les constantes de c
	 * @param c Constantes
	 */
	public Grille(Constantes c) {
		this.cons=c;
		grille = new Case[c.nbecase];
		for (int i=0;i<c.nbecase;i++) {
			this.grille[i]=new Case(c,i);
		};
	}
	
	public Constantes getCons() {return cons;}
	public void setCons(Constantes cons) {this.cons = cons;}
	public ICase[] getGrille() {return grille;}
	public void setGrille(ICase[] grille) {this.grille = grille;}
	
	/**
	 * Verifie si les coordonnées de chaque case sont bien instancées
	 */
	public boolean verifPositionsGrille() {
		for (int i=0;i<cons.nbecase;i++) {
			if (!this.grille[i].verifPositionCase(cons)) {return false;}
		}
		return true;
	}
	
	/**
	 * Renvoit la case correspondant à l'entier
	 */
	public ICase recupCase(int indice) {
		if(indice<cons.nbecase) {
			return this.grille[indice];
		}
		else {return null;}
	}
	
	/**
	 * Affichage de la grille 2D dans la console
	 */
	public void affiche2D() {
		for(int i=0;i<cons.nbecase;i++) {
			if(this.grille[i].getEtat()==-1) {
				System.out.print("~");
			} else {
				System.out.print(this.grille[i].getEtat());
			};
			if(this.grille[i].getPos().verifCoordFrontiere2D(cons)) {
				System.out.println(" ");
			};
		};	
	}
	
	/**
	 * Renvoit l'état de la case correspondant à l'entier
	 */
	public Integer etatCaseByNumber(int nbcase){
		return grille[nbcase].getEtat();	
	}
	

} 
