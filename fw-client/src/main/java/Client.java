import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public static String curCategory;
	public static Integer animalWins = 0;
	public static Integer animalLoses = 0;
	public static Integer placesWins = 0;
	public static Integer placesLoses = 0;
	public static Integer moviesWins = 0;
	public static Integer moviesLoses = 0;
	public static Integer totalWins = 0;
	public static Integer totalLoses = 0;
	
	public String ip = ClientIntro.portNumberRes;
	public int portNumber = Integer.parseInt(ClientIntro.ipAddressRes);
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call){
	
		callback = call;
	}
	
	public void run() {
		
		try {
		socketClient= new Socket(ip,portNumber);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			 
			try {
			String message = in.readObject().toString();
			callback.accept(message);
			}
			catch(Exception e) {}
		}
	
    }
	
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}