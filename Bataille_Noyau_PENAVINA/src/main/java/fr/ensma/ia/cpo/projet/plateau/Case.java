package fr.ensma.ia.cpo.projet.plateau;

public class Case implements ICase {
	
	private IPosition pos;
	private int etat;
	private boolean decouvert;
	
	/**
	 * Constructeur en initialisant une case comme de l'eau et correspondant à une coordonnée en entier
	 * @param c Constantes à vérifier
	 * @param rang Position en entier
	 */
	public Case(Constantes c,int rang) {
		this.etat=-1;
		this.pos=new Position(c);
		this.pos.ciblerPosition(c,rang);
		this.decouvert=false;
	}
		
	public Case(IPosition pos) {
		this.etat=-1;
		this.pos=pos;
		this.decouvert=false;
	}
	public boolean isDecouvert() {return decouvert;}
	public void setDecouvert(boolean decouvert) {this.decouvert = decouvert;}
	public IPosition getPos() {return pos;}
	public void setPos(IPosition pos) {this.pos = pos;}
	
	public void setPosViaEntier(Constantes c,int rang) {
		pos.ciblerPosition(c,rang);
	}
	public int getEtat() {return etat;}
	public void setEtat(int etat) {this.etat = etat;}
	
	/**
	 * Renvoit si la position vérifie les constantes c
	 */
	public boolean verifPositionCase(Constantes c){
		return this.pos.verifPosition(c);
	}
	
	/**
	 * Renvoit si la position est à une frontiére
	 */
	public boolean verifCaseFrontiere(Constantes c) {
		return this.pos.verifCoordFrontiere(c);
	}
}
