package fr.ensma.ia.cpo.projet.plateau;

public interface IGrille {
	public Constantes getCons();
	public void setCons(Constantes cons);
	public ICase[] getGrille();
	public void setGrille(ICase[] joueur);
	public boolean verifPositionsGrille();
	public ICase recupCase(int indice);
	public void affiche2D();
	public Integer etatCaseByNumber(int nbcase);
}
