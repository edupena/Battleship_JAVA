package fr.ensma.ia.cpo.projet.plateau;

import java.util.ArrayList;
import fr.ensma.ia.cpo.projet.communication.ICommunications;
import fr.ensma.ia.cpo.projet.flotte.ContreTorpilleur;
import fr.ensma.ia.cpo.projet.flotte.Croiseur;
import fr.ensma.ia.cpo.projet.flotte.Navire;
import fr.ensma.ia.cpo.projet.flotte.PorteAvion;
import fr.ensma.ia.cpo.projet.flotte.SousMarin;
import fr.ensma.ia.cpo.projet.flotte.Torpilleur;

public class Joueur implements IJoueur {
	private IGrille la_grille;
	private String nom;
	private ArrayList<Navire> navires;
	private Integer tour;
	
	public Joueur(IGrille une_grille, String nom){
		la_grille=une_grille;
		this.nom=nom;
		navires=new ArrayList<Navire>();
		tour=0;
	}

	public IGrille getLa_grille() {return la_grille;}
	public void setLa_grille(IGrille la_grille) {this.la_grille = la_grille;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public ArrayList<Navire> getNavires() {return navires;}
	public void setNavires(ArrayList<Navire> navires) {this.navires = navires;}

	/**
	 * Positionne aléatoirement les 5 bateaux sur la grille
	 */
	public void grilleAleatoire(ICommunications comm){
		navires.add(new ContreTorpilleur(comm));		
		navires.add(new Croiseur(comm));		
		navires.add(new PorteAvion(comm));		
		navires.add(new SousMarin(comm));		
		navires.add(new Torpilleur(comm));
		
		for(int i=0;i<5;i++){
			navires.get(i).poserAleatoire(la_grille);			
		}
	}
	
	/**
	 * Pose le bateau en mettant la proue du bateau sur la position indiquée par l'entier et vérifie
	 * si poupe du bateau est bien sur la grille
	 * @param bateau Le bateau à poser
	 * @param rang La coordonnée de la poupe
	 * @param horizontal La direction du bateau
	 * @return true si le bateau peut être poser, false sinon
	 */
	public boolean poserNavire2D(Navire bateau, int rang, boolean horizontal){
		navires.add(bateau);
		return bateau.poserNavire2D(la_grille, rang, horizontal);
	}
	
	
	public Navire getNavireInCase(Integer nbcase){
		for (int nav_idx=0;nav_idx<navires.size();nav_idx++){
			//for (int cas_idx=0; cas_idx<navires.get(nav_idx).getCases().length;cas_idx++){
			for (int cas_idx=0; cas_idx<navires.get(nav_idx).getTaille();cas_idx++){
				if (navires.get(nav_idx).getCases()[cas_idx].equals(la_grille.recupCase(nbcase))){
					return navires.get(nav_idx);
				}
			}
		}
		return null;
	}
	
	public void increaseLife(Integer valeur){
		for (int i=0; i<navires.size();i++){
			navires.get(i).increaseDefense(valeur);
		}
	}

	public void tourInc(){
		tour++;
	}
	
	public void tourReset(){
		tour=0;
	}
	
	
	public Integer tourGet(){
		return this.tour;
	}

}
