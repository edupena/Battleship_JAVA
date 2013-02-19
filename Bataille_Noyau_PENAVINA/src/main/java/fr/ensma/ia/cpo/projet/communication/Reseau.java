package fr.ensma.ia.cpo.projet.communication;

import java.util.ArrayList;

import fr.ensma.ia.cpo.projet.plateau.GameMaster;
import fr.ensma.ia.cpo.projet.plateau.IGrille;

public class Reseau implements ICommunications {

	private String ip;
	private Integer port;	
	private IGrille grille;	
	GameMaster gm;
	
	public Reseau (GameMaster gm, String ip, Integer port, IGrille grille){
		this.gm=gm;
		this.ip=ip;
		this.port=port;		
		this.grille = grille;
	}
	
	public Integer sendAttackAndGetFeedBack(Integer nombre_joueur_adversaire,Integer nombre_case, Integer puissance)
										throws Exception{
		
		String feedback;		
		
		/* Send attack */
		System.out.println("Open port attacking");
		Thread.currentThread();
		Thread.sleep(100);
		Client cl = new Client(ip,port);		
		cl.send(nombre_case.toString()+","+puissance.toString());
		cl.close();

		System.out.println("Close port attacking");
		
		/* Wait for feedback */			
		
		System.out.println("Waiting for data attacking");
		
		
		do{
			Server srv = new Server(port+1);
			feedback=srv.waitAndGetData();
		}while(feedback.length()==0);
		
		
		if (feedback.contains("-1")) {return -1; }
		else if (feedback.contains("0")) { return 0; }
		else if (feedback.contains("1")) { return 1; }
		else if (feedback.contains("2")) { return 2; }
		else {throw new Error(); }
		

	}
	
	
	public ArrayList<Integer> waitForAttackAndInform() throws Exception{
		
		ArrayList<Integer> attack_data= new ArrayList<Integer>();
		Integer nombre_case;
		Integer puissance;
		String raw;
		
		/* Wait for attack */
		System.out.println("Open port defending");
		
			Server srv = new Server(port);		
			raw=srv.waitAndGetData();
			System.out.println(raw);
		System.out.println("Close port defense");
		
		nombre_case=Integer.valueOf(raw.substring(0, raw.lastIndexOf(",")));
		puissance=Integer.valueOf(raw.substring(raw.lastIndexOf(",")+1));
		attack_data.add(nombre_case);
		attack_data.add(puissance);
		
		/* Sends feedback */
		String data_to_send="";
		
		
		/* Voir sur la grille qu'est-ce qui s'est passé */
		/* Si eau */
		
		
		Integer etat_case = grille.etatCaseByNumber(nombre_case);
				
		
		if (etat_case==-1){
			data_to_send=String.valueOf(-1);
		}
		
		/* Si pas eau */
		else{
			
			Integer etat_final=etat_case-puissance;
			/* Si touché normal */
			if (etat_final>0){
				grille.getGrille()[nombre_case].setEtat(etat_final);
				data_to_send=String.valueOf(1);
			}
			else if (etat_final==0){
				
				/* Si coulé */
				if (gm.getJoueurs().get(0).getNavireInCase(nombre_case).isCoule()){
					grille.getGrille()[nombre_case].setEtat(etat_final);
					data_to_send=String.valueOf(0);
				}
				
				/* Si touché à fond */
				else {
					grille.getGrille()[nombre_case].setEtat(0);
					data_to_send=String.valueOf(2);
				}
				
			}
		}
				
		
		/* Sends the data */
		System.out.println("Open port informing damage");
		Thread.currentThread();
		Thread.sleep(100);
		Client cl = new Client(ip,port+1);					
		cl.send(data_to_send);		
		cl.close();
				
		
		System.out.println("Close port informing damage");
		
		/* return ground zero*/
		return attack_data;
	}

	public int[] retrieveGridLimits() throws Exception{

		int[] limites;
		int dimension=1;
		String data;
		int init=0, end=0;		
		
		/* Retrieve data*/
		do{
			Server srv = new Server(port);		
			data=srv.waitAndGetData();
		}while(data.length()==0);

		for (int i=0;i<data.length();i++){
			if (data.substring(i, i+1).contains(",")){
				dimension++;
			}
		}		

		limites = new int[dimension];
		
		/* Parse data*/
		
		for (int i=0;i<dimension;i++){
			end=data.indexOf(",",end+1);
			if (end==-1) {end=data.length();}
			limites[i]=Integer.valueOf(data.substring(init, end));
			init=end+1;						
		}
				
		return limites;
		
	}
	
	public void sendGridLimits(int limites[]) throws Exception{
		
		String data="";
		/* Prepare data to send */
		data+=String.valueOf(limites[0]);
		for(int i=1;i<limites.length;i++) {
			data+=",";
			data+=String.valueOf(limites[i]);
			
		}
		
		/* Send data */
		Thread.currentThread();
		Thread.sleep(100);
		Client cl = new Client(ip,port); 
		cl.send(data);		
		cl.close();
		
		
	}
	
	

	
}
