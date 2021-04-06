import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	
	static ObjectOutputStream out;
	static ObjectInputStream in;
	
	public static char[] guessArray;
	public static Integer guesses;
	public static char currentGuess;
	public static String curCategory;
	public static Integer animalWins = 0;
	public static Integer animalLoses = 0;
	public static Integer placesWins = 0;
	public static Integer placesLoses = 0;
	public static Integer moviesWins = 0;
	public static Integer moviesLoses = 0;
	public static Integer totalWins = 0;
	public static Integer totalLoses = 0;
	
	public String ip = ClientIntro.ip;
	public int portNumber = Integer.valueOf(ClientIntro.port);
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call){
	
		callback = call;
	}
	
	public void run() {
		
		try {
			socketClient= new Socket(ip, portNumber);
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
	
	public static void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Parse server response into static variables to manage game state
	 */
	public static void parseServerResponse(String data)
	{
		System.out.println("Server Response:" + data);
		// received server message, prepended with 'l'
		if (data.startsWith("l"))
		{
			// do nothing
			return;
		}
		// received size of next word to guess with "w" prepended
		else if (data.startsWith("w"))
		{
			String wordSizeStr = data.substring(1);
			Integer wordSize = Integer.valueOf(wordSizeStr);
			System.out.println("WordsizeStr: " + wordSizeStr);
			System.out.println(wordSize);
			initGuessArray(wordSize);
			
			System.out.println("Guess Aray: " + String.valueOf(guessArray));
			guesses = 6;
			return;
		}
		/*
		 * Process guess
		 */
		else if (data.startsWith("g"))
		{
			// strip e
			String guess = data.substring(1);
			// Incorrect guess, received guesses remaining with "-" prepended
			if (guess.startsWith("-"))
			{
				String guessesLeftStr = guess.substring(1);
				Integer guessesLeft = Integer.valueOf(guessesLeftStr);
				guesses = guessesLeft;
				return;
			}
			// Received locations of correctly guessed letters
			else
			{
				//System.out.println("processed guess");
				updateGuessArray(guess,currentGuess);
				return;
			}
		}
		/*
		 * Process result of turn
		 */
		else if (data.equals("econtinue"))
		{
			// wait for next guess
			return;
		}
		else if (data.equals("ewin"))
		{
			switch(curCategory)
			{
				case "Animals":	animalWins = 1;
								break;
				case "Movies":	moviesWins = 1;
								break;
				case "Places":	placesWins = 1;
								break;
				default:		System.out.println("Invalid Category");
								break;
			}
		}
		else if (data.equals("eloss"))
		{
			System.out.println("loss acknowledged");
			switch(curCategory)
			{
				case "Animals":	animalLoses += 1;
								break;
				case "Movies":	moviesLoses += 1;
								break;
				case "Places":	placesLoses += 1;
								break;
				default:		System.out.println("Invalid Category");
								break;
			}
		}
		else if (data.equals("ewingame"))
		{
			///TODO
		}
		else if (data.equals("elosegame"))
		{
			///TODO
		}
		
	}
	
	/*
	 *  get game state variables
	 */
	public static Integer getWin(String category)
	{
		switch(category)
		{
			case "Animals":	return animalWins;
			case "Movies":	return moviesWins;
			case "Places":	return placesWins;
			default:		System.out.println("Invalid Category");
							break;			
		}
		return -1;
	}
	
	public static Integer getLoss(String category)
	{
		switch(category)
		{
			case "Animals":	return animalLoses;
			case "Movies":	return moviesLoses;
			case "Places":	return placesLoses;
			default:		System.out.println("Invalid Category");
							break;			
		}
		return -1;
	}
	
	// Set guessArray as array of underscores == currentWord.length()
	public static void initGuessArray(int wordSize)
	{
		//System.out.println("Wordsize in init: " + wordSize);
		int size = wordSize;
		guessArray = new char[size];
		//System.out.println("Wordsize in init: " + wordSize);
		// set every value to _
		for(int i = 0; i < wordSize; i++)
		{
			
			guessArray[i] = '_';
			//System.out.println("Guess Array in loop: " + guessArray[i]);
			//System.out.println("Guess Array in loop: " + guessArray.toString());
		}
		//guessArray[wordSize] = '\0';
		System.out.println("Guess Array in init: " + String.valueOf(guessArray));
	}
		
	public static void updateGuessArray(String locations, char guess)
	{
		for(char c: locations.toCharArray())
		{
			guessArray[Character.getNumericValue(c)] = guess;
		}
	}
	
	// Reset values for new game
	
	


}