package fr.ensma.ia.cpo.projet.plateau;

public class Constantes {
	public int dimension;
	public int limites[];
	public int nbecase;
	
	/**
	 * Constructeur créant les constanstes nécessaires  selon le nombre de dimensions
	 * et mets chaque limites à 10
	 */
	public Constantes(int dim) {
		this.dimension=dim;
		limites=new int[dim];
		int x=1;
		for(int i=0;i<dim;i++) {
			this.limites[i]=10;
			x=x*10;
		}
		this.nbecase=x;
	}
	public int getDimension() {return dimension;}
	public void setDimension(int dimension) {this.dimension = dimension;}
	public int[] getLimites() {return limites;}
	public void setLimites(int[] limites) {
		this.limites = limites;
		this.dimension=limites.length;
		int x=1;
		for(int i=0;i<dimension;i++) {
			x=x*limites[i];
		}
		this.nbecase=x;
		this.dimension=limites.length;
	}
	public int getNbecase() {return nbecase;}
}
