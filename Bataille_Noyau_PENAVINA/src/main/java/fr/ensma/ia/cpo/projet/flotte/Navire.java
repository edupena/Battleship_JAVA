package fr.ensma.ia.cpo.projet.flotte;

import java.util.Random;
import fr.ensma.ia.cpo.projet.combat.IAttaque;
import fr.ensma.ia.cpo.projet.combat.BlindageNormal;
import fr.ensma.ia.cpo.projet.combat.IDefense;
import fr.ensma.ia.cpo.projet.combat.TirNormal;
import fr.ensma.ia.cpo.projet.communication.ICommunications;
import fr.ensma.ia.cpo.projet.plateau.Case;
import fr.ensma.ia.cpo.projet.plateau.Constantes;
import fr.ensma.ia.cpo.projet.plateau.ICase;
import fr.ensma.ia.cpo.projet.plateau.IGrille;
import fr.ensma.ia.cpo.projet.plateau.IPosition;
import fr.ensma.ia.cpo.projet.plateau.Position;

public abstract class Navire{
	
	private int taille;
	private String nom;
	private int anciennete;
	private IAttaque attaque;
	private ICase cases[];	
	/**
	 * Constructeur d'un navire inconnu de taille nulle
	 */
	public Navire(ICommunications comm) {
		this.taille=0;
		this.nom="404";
		this.anciennete=0;
		new BlindageNormal(this);		
		attaque = new TirNormal(this, comm);
		cases = null;		
	}
	
	/**
	 * Constructeur d'un navire ayant la taille et le nom indiqué
	 * @param taille
	 * @param nom
	 * @param comm
	 */
	public Navire(int taille,String nom, ICommunications comm) {
		this.taille=taille;
		this.nom=nom;
		this.anciennete=0;
		new BlindageNormal(this);
		attaque = new TirNormal(this, comm);
		
		cases = new ICase[taille];
		for(int i=0;i<taille;i++){
			cases[i]=new Case(new Position(2));
		}
	}
	
	public Navire(Constantes c,int taille,int posinitiale,int dimension, String nom, ICommunications comm) {
		this.taille=taille;
		this.nom=nom;
		this.anciennete=0;
		new BlindageNormal(this);
		attaque = new TirNormal(this, comm);
		IPosition pos = new Position(c);
		for(int i=0;i<taille;i++){
			cases[i]=new Case(c,posinitiale+pos.ciblerDimension(c,dimension)*i);
		}
	}
	
	public ICase[] getCases(){ return this.cases; }
	public int getTaille() {return taille;}
	public void setTaille(int taille) {this.taille = taille;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public int getAnciennete() {return anciennete;}
	public void setAnciennete(int anciennete) {this.anciennete = anciennete;}
	public void setDefense(IDefense defense) {}
	public void setAttaque(IAttaque attaque) {this.attaque = attaque;}
	public IAttaque getAttaque(){return this.attaque;}		
	
	/**
	 * Pose le navire sur une grille 2D en commencant par la proue et en vérifiant que la poupe reste sur la grille.
	 * @param g Grille où poser le navire
	 * @param rang Position en entier de la proue
	 * @param hv Direction horizontale/verticale
	 * @return true si le navire est posé, false sinon
	 */
	public boolean poserNavire2D(IGrille g,int rang, boolean hv) {
		Constantes c=g.getCons();
		boolean possible=true;
		if (rang<c.nbecase) {
			if(g.getGrille()[rang].getEtat()==-1) {
				if(hv){
					if(rang+taille-1<c.nbecase) {
						for(int i=1;i<taille;i++) {
							if(g.getGrille()[rang+i].getEtat()!=-1 || g.getGrille()[rang+i-1].verifCaseFrontiere(c)) {possible=false;}
						}
						if(possible) {
							cases = new Case[c.nbecase];
							for(int i=0;i<taille;i++) {
								cases[i]=g.getGrille()[rang+i];
								cases[i].setEtat(taille);
							}
						}
					}else{possible=false;}
				}else{
					if(rang+(taille-1)*c.limites[0]<c.nbecase) {
						for(int i=1;i<taille;i++) {
							if(g.getGrille()[rang+i*c.limites[0]].getEtat()!=-1) {possible=false;}
						}
						if(possible) {
							cases = new Case[c.nbecase];
							for(int i=0;i<taille;i++) {
								cases[i]=g.getGrille()[rang+i*c.limites[0]];
								cases[i].setEtat(taille);
							}
						}
					}else{possible=false;}
				}
			}else{ possible=false;}
		}else{ possible=false;}
	
	return possible;
	}
	
	/**
	 * Dispose le navire aléatoirement sur la grille indiquée.
	 * @param g Grille où poser le navire
	 */
	public void poserAleatoire(IGrille g) {
		Constantes c=g.getCons();
		int random = (int)(Math.random()*(c.nbecase));
		Random hvrandom = new Random();
		boolean ok = poserNavire2D(g, random, hvrandom.nextBoolean());
		while(!ok) {
			random = (int)(Math.random()*(c.nbecase));
			hvrandom = new Random();
			ok = poserNavire2D(g, random, hvrandom.nextBoolean());
		}
	}
	
	/**
	 * Verifie si le navire a été coulé.
	 * @return true si le bateau est coulé, false sinon
	 */
	public Boolean isCoule(){
		
		for (int i=0; i<cases.length;i++){
			if (cases[i]!=null){
				if (cases[i].getEtat()!=0){					
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Vérifie le nombre de parties encore cachées du navire (ce qui correspond au parties encore non touchées).
	 * @return Nombre de parties cachées
	 */
	public int partiesCaches(){
		int x=0;
		for(int i=0; i<this.getTaille();i++){
			if(!cases[i].isDecouvert()){x++;}
		}
		return x;
	}
	
	/**
	 * Augmente la défense de chaque partie non détruite du navire
	 * @param points Nombre de défense a rajouter
	 */
	public void increaseDefense(int points){
		for (int i=0; i<cases.length;i++){
			if (cases[i]!=null){
				if(cases[i].getEtat()>0){
					cases[i].setEtat(cases[i].getEtat()+points);
				}
			}
		}		
	}
}

