package fr.ensma.ia.cpo.projet.plateau;

public interface IPosition {
	public int[] getPosition();
	public void setPosition(int[] position);
	public boolean verifPosition(Constantes c);	
	public int ciblerDimension(Constantes c,int dim);
	public void ciblerPosition(Constantes c,int rang);
	public int passageCoordEntier(Constantes c);
	public int recupCoord(Constantes c,int indice) ;
	public void printCoordEntier(Constantes c);
	public boolean verifCoordFrontiere(Constantes c);
	public boolean verifCoordFrontiere2D(Constantes c);
}
