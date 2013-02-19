package fr.ensma.ia.cpo.projet.communication;

import java.net.*;
import java.io.*;

public class Client

{  
   private Socket socket              = null;   
   private DataOutputStream streamOut = null;

   /**
    * Constructeur d'un client ayant un nom et un port de serveur donné
    * @param serverName Son nom
    * @param serverPort Son port de serveur
    * @throws Exception
    */
   public Client(String serverName, int serverPort) throws Exception
   {  	   	  	  
	   this.socket = new Socket(serverName, serverPort);	   
	   this.streamOut = new DataOutputStream(socket.getOutputStream());
   }
   
   /**
    * Permet d'envoyer un message donné
    * @param line
    * @throws IOException
    */
   public void send(String line) throws IOException{
	   streamOut.writeUTF(line);
	   streamOut.flush();   
   }

   /**
    * Ferme le port de connexion
    * @throws IOException
    */
   public void close() throws IOException{
	   this.socket.close();
   }


}