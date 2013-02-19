package fr.ensma.ia.cpo.projet.plateau;

public interface IGameMaster {

	

	public abstract int getNiveau();

	public abstract boolean poserNavire2D(int joueur, int bateau, int rang,
			boolean horizontal);

	public Boolean isTricher();
	
	public abstract boolean grilleAleatoire(int joueur, String ip, Integer port);

	public abstract Integer attaquer(Integer grille_origine,
			String nombat, Integer grille_attaquee, Integer pos_attaquee)
			throws Exception;
	
	public Integer attaqueAuto(int niveau);
	
	public String getMyIp();

	public Integer getLastValueOfIp (String ip);
	
	public Boolean isFinished(int joueur);
}