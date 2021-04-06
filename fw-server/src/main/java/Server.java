import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.scene.control.ListView;

// Server Class
public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	ServerThread server;
	private Consumer<Serializable> callback;
	GameLogic logic = new GameLogic();

	int portNumber = Integer.parseInt(ServerIntro.portNumber);
	Server(Consumer<Serializable> call){
	
		callback = call;
		server = new ServerThread();
		server.start();
	}
	
	// Server Thread inner class
	public class ServerThread extends Thread{
		
		public void run() {
			try(ServerSocket mysocket = new ServerSocket(portNumber)){
		    System.out.println("Server is waiting for a client!");
		  
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	
		// Client Thread inner class
		// Each client thread will run a GameLogic object
		class ClientThread extends Thread{
			
			int count;
			Socket connection;
			ObjectInputStream in;
			ObjectOutputStream out;
			GameLogic game;
			
			/*
			 * Constructor, inits GameLogic object
			 */
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;
				game = new GameLogic();
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}
			
			// parse String sent from client and respond accordingly
			public String parseMessage(String data)
			{
				//System.out.println(data);
				
				// Play word from new category
				if(data.equals("Animals") || data.equals("Movies") || data.equals("Places"))
				{
					game.setCategory(data);
					game.playNextWord();
					int wordSize = game.getCurrentWord().length();
					/*
					 *  Message denoting wordsize is prepended with 'w' to 
					 *  differentiate it from locations of correct guesses
					 */
					return "w" + String.valueOf(wordSize);
				}
				// Play next guess
				else if (data.length() == 1)
				{
					return game.playNextGuess(data.charAt(0));
				}
				// Check win conditions
				else if (data.equals("Result"))
				{
					return game.playResult();
				}
				// Return misc. game state info
				else 
				{
					switch(data)
					{
						case "winanimals":	return "todo";
						case "winmovies":	return "todo";
						case "winplaces":	return "todo";
						case "lossanimals":	return "todo";
						case "lossmovies":	return "todo";
						case "lossplaces":	return "todo";
						case "category":	return "todo";
					}
						
				}
				// should not reach here
				return "fail";
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				updateClients("lnew client on server: client #"+count);
					
				 while(true) {
					    try {
					    	String data = in.readObject().toString();
					    	
					    	// parse message from client
					    	String response = parseMessage(data);
					    	out.writeObject(response);
					    	callback.accept(response);
					    	//callback.accept("client: " + count + " sent: " + data);
					    	//updateClients("client #"+count+" said: "+data);
					    	
					    	}
					    catch(Exception e) {
					    	callback.accept("lOOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					    	updateClients("lClient #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}
