package fr.ensma.ia.cpo.projet.plateau;

import java.util.ArrayList;

import fr.ensma.ia.cpo.projet.communication.ICommunications;
import fr.ensma.ia.cpo.projet.flotte.Navire;

public interface IJoueur {

	public abstract IGrille getLa_grille();

	public abstract void setLa_grille(IGrille la_grille);

	public abstract String getNom();

	public abstract void setNom(String nom);

	public abstract ArrayList<Navire> getNavires();

	public abstract void setNavires(ArrayList<Navire> navires);

	public abstract void grilleAleatoire(ICommunications comm);
	
	public abstract Navire getNavireInCase(Integer nbcase);

}