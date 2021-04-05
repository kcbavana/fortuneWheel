import java.util.regex.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
	// current game state
	private String currentWord;
	private String currentCategory;
	private Integer guesses;
	// Win count
	private Boolean winAnimal;
	private Boolean winMovie;
	private Boolean winPlace;
	// Loss count
	private Integer lossAnimal;
	private Integer lossMovie;
	private Integer lossPlace;
	// ArrayLists of words to guess
	private ArrayList<String> wordsAnimal;
	private ArrayList<String> wordsMovie;
	private ArrayList<String> wordsPlace;
	private ArrayList<String> wordsUsed;
	// Array for tracking guessed letters
	private char[] guessArray;
	// Generate random numbers
	private Random random;
	
	// Constructor, init starting game values
	public GameLogic() 
	{
		// Start with no current word/category
		currentWord = null;
		currentCategory = null;
		guessArray = null;
		guesses = 6;
		// no wins
		winAnimal = false;
		winMovie = false;
		winPlace = false;
		// no losses
		lossAnimal = 0;
		lossMovie = 0;
		lossPlace = 0;
		// Init and populate ArrayLists (default size is 10)
		wordsAnimal = new ArrayList<String>();
		wordsAnimal.add("monkey");
		wordsAnimal.add("cheetah");
		wordsAnimal.add("wolf");
		wordsAnimal.add("leopard");
		wordsAnimal.add("elephant");
		wordsAnimal.add("giraffe");
		wordsAnimal.add("dog");
		wordsAnimal.add("zebra");
		wordsAnimal.add("horse");
		wordsAnimal.add("cat");
		wordsMovie = new ArrayList<String>();
		wordsMovie.add("shrek");
		wordsMovie.add("avatar");
		wordsMovie.add("spiderman");
		wordsMovie.add("interstellar");
		wordsMovie.add("it");
		wordsMovie.add("logan");
		wordsMovie.add("hellraiser");
		wordsMovie.add("brick");
		wordsMovie.add("hereditary");
		wordsMovie.add("parasite");
		wordsPlace = new ArrayList<String>();
		wordsPlace.add("chicago");
		wordsPlace.add("italy");
		wordsPlace.add("nashville");
		wordsPlace.add("california");
		wordsPlace.add("berlin");
		wordsPlace.add("prague");
		wordsPlace.add("paris");
		wordsPlace.add("london");
		wordsPlace.add("tokyo");
		wordsPlace.add("ontario");
		wordsUsed = new ArrayList<String>();
		// init random
		random = new Random();
	}
	
	/*
	 * Getters/Setters
	 * 
	 * Note to self: write unit tests for behavior of classes, not functions
	 */
	
	// get/set currentWord
	public String getCurrentWord() 
	{
		return currentWord;
	}

	// get/set currentCategory
	public String getCategory()
	{
		return currentCategory;
	}
	
	public void setCategory(String category)
	{
		switch(category)
		{
			case "Animals":	currentCategory = "Animals";
							break;
			case "Movies":	currentCategory = "Movies";
							break;
			case "Places":	currentCategory = "Places";
							break;
			default:		System.out.println("Invalid Category");
							break;			
		}
	}
	
	public Integer getGuesses()
	{
		return guesses;
	}
	
	// Did the user guess a word in this category?
	public Boolean getWin(String category)
	{
		switch(category)
		{
			case "Animals":	return winAnimal;
			case "Movies":	return winMovie;
			case "Places":	return winPlace;
			default:		System.out.println("Invalid Category");
							break;			
		}
		return false;
	}
	
	// set win to true/false for a given category
	private void setWin(String category, Boolean bool)
	{
		switch(category)
		{
			case "Animals":	winAnimal = bool;
							break;
			case "Movies":	winMovie = bool;
							break;
			case "Places":	winPlace = bool;
							break;
			default:		System.out.println("Invalid Category");
							break;			
		}
	}
	
	// get/set losses
	public Integer getLoss(String category)
	{
		switch(category)
		{
			case "Animals":	return lossAnimal;
			case "Movies":	return lossMovie;
			case "Places":	return lossPlace;
			default:		System.out.println("Invalid Category");
							break;			
		}
		return -1;
	}
	
	private void setLoss(String category, Integer newLoss)
	{
		switch(category)
		{
			case "Animals":	lossAnimal = newLoss;
							break;
			case "Movies":	lossMovie = newLoss;
							break;
			case "Places":	lossPlace = newLoss;
							break;
			default:		System.out.println("Invalid Category");
							break;			
		}
	}
	
	public char[] getGuessArray()
	{
		return guessArray;
	}
	
	/*
	 * Methods to initialize the game
	 */
	
	// get word from input category. No setter for the Category ArrayLists
	private String getWordFromCategory(String category)
	{
		switch(category)
		{
			case "Animals":	return wordsAnimal.get(random.nextInt(10));
			case "Movies":	return wordsMovie.get(random.nextInt(10));
			case "Places":	return wordsPlace.get(random.nextInt(10));
			default:		System.out.println("Invalid Category");
							break;			
		}
		return null;
	}
	
	// Check usedWords for input word
	private Boolean checkUsedWords(String word)
	{
		for(String s:wordsUsed)
		{
			if(s == word)
			{
				return true;
			}
		}
		return false;
	}
	
	// Set guessArray as array of underscores == currentWord.length()
	private void initGuessArray()
	{
		// init String identical to currentWord
		guessArray = currentWord.toCharArray();
		// set every value to _
		for(int i = 0; i < currentWord.length(); i++)
		{
			guessArray[i] = '_';
		}
	}
	
	// Use private functions to set next Word
	public void playNextWord()
	{
		// get random word
		String temp = getWordFromCategory(currentCategory);
		// pick another word if it has been played already
		while(checkUsedWords(temp))
		{
			temp = getWordFromCategory(currentCategory);
		}
		currentWord = temp;
		initGuessArray();
		wordsUsed.add(temp);
	}
	
	/*
	 * Methods that manage an ongoing game
	 */
	// Use locations String to 'fill in' guessArray with guessed char
	private void updateGuessArray(String locations, char guess)
	{
		for(char c: locations.toCharArray())
		{
			guessArray[Character.getNumericValue(c)] = guess;
		}
	}
	
	// input char, output String of numbers corresponding to array indices == char
	public String playNextGuess(char guess)
	{
		String locations = new String();
		// parse currentWord
		for(int i = 0; i < currentWord.length(); i++)
		{
			if (currentWord.charAt(i) == guess)
			{
				locations = locations + String.valueOf(i);
			}
		}
		// return -1 if locations is empty and use one guess
		if(locations.isEmpty())
		{
			guesses-=1;
			locations = "-" + String.valueOf(guesses);
		}
		// add guessed char to guessArray
		else
		{
			updateGuessArray(locations,guess);
		}
		return locations;
	}
	
	/*
	 * Methods that check and manage end-game states
	 */
	private Boolean checkWin()
	{
		// game is won if currentWord == guessArray
		if(currentWord == guessArray.toString())
		{
			return true;
		}
		return false;
	}
	
	private Boolean checkLoss()
	{
		// game is lost if guesses <=0
		if(guesses <= 0)
		{
			return true;
		}
		return false;
	}
	
	private Boolean checkWinGame()
	{
		// match is won if each category of wins is completed
		if (getWin("Animals") && getWin("Movies") && getWin("Places"))
		{
			return true;
		}
		return false;
	}
	
	private Boolean checkLoseGame()
	{
		// match is lost if each category has >= 3 losses
		if (getLoss("Animals") >= 3 && getLoss("Movies") >= 3 && getLoss("Places") >= 3)
		{
			return true;
		}
		return false;
	}
	
	// resets game state values
	private void newGame()
	{
		wordsUsed.clear();
		currentWord = null;
		currentCategory = null;
		guessArray = null;
		guesses = 6;
		// no wins
		winAnimal = false;
		winMovie = false;
		winPlace = false;
		// no losses
		lossAnimal = 0;
		lossMovie = 0;
		lossPlace = 0;
		
	}
	
	// Uses private functions to update GameLogic and return results to client
	public String playResult()
	{
		// return "win" if currentWord was successfully guessed
		if(checkWin())
		{
			setWin(currentCategory,true);
			return "win";
		}
		// return "loss" if player is out of guesses
		else if (checkLoss())
		{
			setLoss(currentCategory, (getLoss(currentCategory)+1));
			return "loss";
		}
		// return "wingame" if player has won the game
		else if (checkWinGame())
		{
			newGame();
			return "wingame";
		}
		// return "losegame" if player has lost the game
		else if (checkLoseGame())
		{
			newGame();
			return "losegame";
		}
		// return "continue" if game is unfinished
		return "continue";
	}
	
}