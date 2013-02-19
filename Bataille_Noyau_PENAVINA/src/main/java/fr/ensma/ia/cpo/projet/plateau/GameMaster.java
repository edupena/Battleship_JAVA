package fr.ensma.ia.cpo.projet.plateau;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import fr.ensma.ia.cpo.projet.combat.IAttaque;
import fr.ensma.ia.cpo.projet.communication.ICommunications;
import fr.ensma.ia.cpo.projet.communication.Local;
import fr.ensma.ia.cpo.projet.communication.Reseau;
import fr.ensma.ia.cpo.projet.flotte.ContreTorpilleur;
import fr.ensma.ia.cpo.projet.flotte.Croiseur;
import fr.ensma.ia.cpo.projet.flotte.Navire;
import fr.ensma.ia.cpo.projet.flotte.PorteAvion;
import fr.ensma.ia.cpo.projet.flotte.SousMarin;
import fr.ensma.ia.cpo.projet.flotte.Torpilleur;

public class GameMaster implements IGameMaster{

	ArrayList<Joueur> joueurs;	
	boolean ia;
	int niveau;
	Boolean local;
	ICommunications comm;
	boolean tricher;
	
	/**
	 * Constructeur d'une bataille navale classique 10x10 entre deux joueurs humains
	 */
	public GameMaster(){
		joueurs=new ArrayList<Joueur>();
		joueurs.add(new Joueur(new Grille(),"Joueur 1"));
		joueurs.add(new Joueur(new Grille(),"Joueur 2"));		
		ia=false;
		niveau=0;		
		local=true;
		tricher=false;
		comm = new Local(this);
		this.grilleAleatoire(0, "", 0);
		this.grilleAleatoire(1, "", 0);
	}
	
	/**
	 * Constructeur d'une bataille navale classique 10x10 permettant de dire s'il y a une ia
	 * et fixe son niveau
	 * @param ia true si on joue contre l'ordi
	 * @param niveau intelligence de l'ia
	 */
	public GameMaster(boolean ia,int niveau, boolean tricher, Boolean local, String ip, Integer port){
		joueurs=new ArrayList<Joueur>();
		joueurs.add(new Joueur(new Grille(),"Joueur 1"));
		joueurs.add(new Joueur(new Grille(),"Joueur 2"));		
		this.ia=ia;
		this.tricher=tricher;
		this.niveau=niveau;
		this.local=local;
		if (local){comm = new Local(this);}
		else {comm = new Reseau(this, ip, port, this.getJoueurs().get(0).getLa_grille());}
		this.grilleAleatoire(0, ip, port);
		this.grilleAleatoire(1, ip, port);
	}

	public Boolean isTricher(){
		return this.tricher;
	}
	
	public Boolean isCaseCachee(int joueur, int pos){
		return !this.getJoueurs().get(joueur).getLa_grille().getGrille()[pos].isDecouvert();
	}
	
	public ArrayList<Joueur> getJoueurs() {return joueurs;}
	public void setJoueurs(ArrayList<Joueur> joueurs) {this.joueurs = joueurs;}
	/* (non-Javadoc)
	 * @see fr.ensma.ia.cpo.projet.plateau.IGameMaster#getTour()
	 */	
	public boolean isIa() {return ia;}
	public boolean isLocal() {return local;}
	public void setIa(boolean ia) {this.ia = ia;}
	public int getNiveau() {return niveau;}
	public void setNiveau(int niveau) {this.niveau = niveau;}
	
	/**
	 * Pose un navire sur un grille 2D. Chaque type de bateau correspond à un entier.
	 * @param joueur Joueur concerné
	 * @param bateau Navire a poser
	 * @param rang Position de la proue
	 * @param horizontal Direction du navire
	 * @return true si le navire a été posé, false sinon
	 */
	
	public Integer getValueCase(Integer joueur, Integer pos){		
		return joueurs.get(joueur).getLa_grille().getGrille()[pos].getEtat();
		
	}
	
	public boolean poserNavire2D(int joueur, int bateau, int rang, boolean horizontal){
		Navire nav;
		switch (bateau)	{
		case 1 : nav=new PorteAvion(comm);
			break;
		case 2 : nav=new Croiseur(comm);
			break;
		case 3 : nav=new SousMarin(comm);
			break;
		case 4 : nav=new ContreTorpilleur(comm);
			break;
		case 5 : nav=new Torpilleur(comm);
			break;
		default : return false;
		}
		
		if(joueur<joueurs.size()){
			return joueurs.get(joueur).poserNavire2D(nav, rang, horizontal);
		}else{return false;}
	}
	
	/**
	 * Génère un grille aléatoire pour le joueur indiqué.
	 * @param joueur Numero du joueur
	 * @param ip IP du joueur
	 * @param port Port du reseau
	 * @return true si la grille a été crée, false sinon
	 */
	public boolean grilleAleatoire(int joueur, String ip, Integer port){
		if(joueur<joueurs.size()){
			if (local)
				{joueurs.get(joueur).grilleAleatoire(new Local(this));}
			else
				{joueurs.get(joueur).grilleAleatoire(new Reseau(this,ip,port,joueurs.get(joueur).getLa_grille()));}
			return true;
		}else{return false;}
	}
	
	/**
	 * Attaque une position d'une grille via la position d'un bateau de la grille adverse. L'entier renvoyé indique ce que l'on a touché:
	 * -1 de l'eau, 0 si le bateau est coulé, 1 si un bateau est touché, 2 si la case a été touchée à fond mais que le bateau n'est pas coulé
	 * @param grille_origine Grille de l'attaquant
	 * @param pos_origine Position du bateau attaquant
	 * @param grille_attaquee Grille de l'attaqué
	 * @param pos_attaquee Position de la case attaquée
	 * @return état de la case après attaque
	 */
public Integer attaquer(Integer grille_origine, String nombat, Integer grille_attaquee, Integer pos_attaquee) throws Exception{
		
		Joueur j=this.getJoueurs().get(grille_origine);
		int i;
				
		Navire n;

		
		for (i=0; i<100; i++){
			n=this.getJoueurs().get(grille_origine).getNavireInCase(i);
			if(n!=null){				
				if (n.getNom()==nombat){					
					break;
					}			
			}
		}
		
		n = j.getNavireInCase(i);		
		
		IAttaque a=n.getAttaque();
		

		ICase case_attaquee = this.getJoueurs().get(grille_attaquee).getLa_grille().getGrille()[pos_attaquee];
		
		if (!case_attaquee.isDecouvert()){
			case_attaquee.setDecouvert(true);					
		}
		
		Integer resul=a.tir(pos_attaquee,grille_attaquee);
		
		if (resul==-1) { joueurs.get(grille_attaquee).tourInc();}
		else {joueurs.get(grille_attaquee).tourReset();}
		if (resul==0) { joueurs.get(grille_origine).increaseLife(1);}				
		
		if (joueurs.get(grille_attaquee).tourGet()>=5) {
			joueurs.get(grille_attaquee).tourReset();
			joueurs.get(grille_attaquee).increaseLife(1);
		}
		
		return resul;			
	}


	public Integer attaquer(int pos, int puiss){
		ICase case_att = this.getJoueurs().get(0).getLa_grille().getGrille()[pos];

		if (case_att.getEtat()==-1){
			joueurs.get(0).tourInc();
			
			if (joueurs.get(0).tourGet()>=5) {
				joueurs.get(0).tourReset();
				joueurs.get(0).increaseLife(1);
			}
			return -1;
			}
		else {
			joueurs.get(0).tourReset();
			if ((case_att.getEtat()-puiss)>0){
				case_att.setEtat(case_att.getEtat()-puiss);
				return 1;
			}
			else{				
				case_att.setEtat(0);										
				if (this.getJoueurs().get(0).getNavireInCase(pos)!=null){
					if (this.getJoueurs().get(0).getNavireInCase(pos).isCoule()){
						return 0;
					}				
					else { return 2;}
				}
				else {return 2;}
			}
		}
	}	
	
	
	
	/**
	 * Augmente la défense de chaques cases du bateau par la valeur indiquée.
	 * @param grille_concernee Grille concernée
	 * @param valeur Nombre de point à rajouter en défense
	 */
	public void increaseLife(Integer grille_concernee, Integer valeur){
		this.getJoueurs().get(grille_concernee).increaseLife(valeur);
	}
	
	
	public Integer getPuissance(int bateau, int joueur){		
		return this.getJoueurs().get(joueur).getNavires().get(bateau).partiesCaches();		
	}
	
	public Integer getPuissance(String bateau, int joueur){
		int batint=0;
		if (bateau=="Porte avion") { batint=2; }
		else if (bateau=="Croiseur") {batint=1; }
		else if (bateau=="Sous marin") {batint=3; }
		else if (bateau=="Contre torpilleur") { batint=0;}
		else if (bateau=="Torpilleur") {batint=4; }
		
		return this.getJoueurs().get(joueur).getNavires().get(batint).partiesCaches();		
	}
	
	public ArrayList<Integer> waitForAttack(){
		try {
			return this.comm.waitForAttackAndInform();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Integer attaqueAuto(int niveau){
		if (niveau==1){
			return ((int) (Math.random() * 100));
		}
		else{
			Integer pos=0;
			for (int i=0; i<this.getJoueurs().get(0).getLa_grille().getCons().nbecase;i++){
				if (this.getJoueurs().get(0).getLa_grille().getGrille()[i].getEtat()>0){ pos=i; break; }
			}
			return pos;
		}
	}
	
	public String getMyIp(){		
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "No internet address 0.0.0.0";
		} 
	}

	public Integer getLastValueOfIp (String ip){		
		return Integer.valueOf(ip.substring(ip.lastIndexOf(".")+1));
		
	}
	
	public Boolean isFinished(int joueur){
		
		for (int i=0;i<joueurs.get(joueur).getNavires().size();i++){
			if (!joueurs.get(joueur).getNavires().get(i).isCoule()){
				 return false;
			}
		}
		return true;
	}
	
}
	
