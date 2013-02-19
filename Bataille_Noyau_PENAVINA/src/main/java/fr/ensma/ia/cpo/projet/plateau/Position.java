package fr.ensma.ia.cpo.projet.plateau;

public class Position implements IPosition{

	private int coord[];
	
	/**
	 * Constructeur cr�ant un tableau d'entier suivant le nombre de dimension d�finie dans c
	 * @param c Constantes
	 */
	public Position(Constantes c) {
		this.coord = new int[c.dimension];
	}
	
	/**
	 * Constructeur cr�ant un tableau d'entier suivant le nombre de dimension indiqu�
	 * @param dim Nombre de dimensions
	 */
	public Position(int dim){
		this.coord = new int[dim];
	}
	
	public int[] getPosition() {return coord;}
	public void setPosition(int[] position) {this.coord = position;}
	
	/**
	 * V�rifie si la position respecte les constantes de c
	 */
	public boolean verifPosition(Constantes c) {
		int i;
		if (this.coord.length!=c.dimension) {return false;};
		for (i=0;i<c.dimension;i++) {
			if (this.coord[i]>c.limites[i]-1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Renvoie en base 10 l'�quivalent en base quelconque des constanstes c d'une certaine dimension
	 */
	public int ciblerDimension(Constantes c,int dim) {
		int cible=1;
		if (dim<c.dimension) {
				for(int i=0;i<dim;i++) {
					cible=cible*c.limites[i];
				}
			}
		return cible;
	}
	
	/**
	 * Modifie les coordonn�es de la position pour qu'elles correspondent � l'entier �quivalent en base 10
	 */
	public void ciblerPosition(Constantes c,int rang) {
		int j=rang;
		int k;
		if (rang<c.nbecase) {
			for (int i=(c.dimension-1);i!=(-1);i--) {
				k=(j/=ciblerDimension(c,i));
				coord[i]=(k);
				rang=rang-k*ciblerDimension(c,i);
				j=rang;
			}
		}
	}
	
	/**
	 * Renvoit les coordonn�es de la position en un entier en base 10
	 */
	public int passageCoordEntier(Constantes c){
		int i=0;
		if (this.verifPosition(c)) {
			for (int j=0;j<c.dimension;j++) {
				i=ciblerDimension(c,j)*coord[j]+i;
			}
			return i;
		}else{return -1;}
	}
	
	/**
	 * Renvoie la coordonn�e d'une certaine dimension si elle v�rifie les constantes c
	 */
	public int recupCoord(Constantes c,int indice) {
		if(indice<c.dimension & this.verifPosition(c)) {
			return this.coord[indice];
		}
		else {return -1;}
	}
	
	/**
	 * Renvoie sur la console l'�quivalent de la position en entier
	 */
	public void printCoordEntier(Constantes c) {
		System.out.print(this.passageCoordEntier(c));
	}
	
	/**
	 * V�rifie que la position n'est pas � une fronti�re
	 */
	public boolean verifCoordFrontiere(Constantes c) {
		for (int i=0;i<c.dimension;i++) {
			if(this.coord[i]==(c.limites[i]-1)) {
				return true;
			};
		};
		return false;
	}
	
	/**
	 * V�rifie que la position n'est pas � une fronti�re dans une repr�sentation 2D
	 */
	public boolean verifCoordFrontiere2D(Constantes c) {
		if(this.coord[0]==c.limites[0]-1) {return true;}
		else {return false;}
	}
}
