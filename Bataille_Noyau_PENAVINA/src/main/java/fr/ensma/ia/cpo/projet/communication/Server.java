package fr.ensma.ia.cpo.projet.communication;

import java.net.*;
import java.io.*;

public class Server
{  
	
   private Socket          socket   = null;
   private ServerSocket    server   = null;
   private BufferedReader streamIn =  null;

   public Server(int port) throws Exception
   {    
	   	
	   server = new ServerSocket(port);	   	
	   socket = server.accept();   

         
}
   
   public String waitAndGetData() throws IOException{
       
	   streamIn = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));                  
	   String line = streamIn.readLine();	  
	   socket.close();
	   server.close();
	   return line.substring(2);
   }
   
  
}