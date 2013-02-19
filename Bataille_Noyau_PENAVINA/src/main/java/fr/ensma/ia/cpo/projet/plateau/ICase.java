package fr.ensma.ia.cpo.projet.plateau;

public interface ICase {
	public boolean isDecouvert();
	public void setDecouvert(boolean decouvert);
	public IPosition getPos();
	public void setPos(IPosition pos) ;
	public void setPosViaEntier(Constantes c,int rang);
	public int getEtat();
	public void setEtat(int etat);
	public boolean verifPositionCase(Constantes c);
	public boolean verifCaseFrontiere(Constantes c);
}
