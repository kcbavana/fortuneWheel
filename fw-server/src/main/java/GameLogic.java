import java.util.regex.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
	// current game state
	private String currentWord;
	private String currentCategory;
	Integer guesses;
	// Win count
	Boolean winAnimal;
	Boolean winMovie;
	Boolean winPlace;
	// Loss count
	Integer lossAnimal;
	Integer lossMovie;
	Integer lossPlace;
	// Name of each category
	//String Animals;
	//String Movies;
	//String Places;
	// ArrayLists of words to guess
	private ArrayList<String> wordsAnimal;
	private ArrayList<String> wordsMovie;
	private ArrayList<String> wordsPlace;
	private ArrayList<String> wordsUsed;
	// Array for tracking guessed letters
	String guessArray;
	// Generate random numbers
	Random random;
	
	// Constructor, init starting game values
	public GameLogic() 
	{
		// Start with no current word/category
		currentWord = null;
		currentCategory = null;
		guesses = 6;
		// no wins
		winAnimal = false;
		winMovie = false;
		winPlace = false;
		// no losses
		lossAnimal = 0;
		lossMovie = 0;
		lossPlace = 0;
		// Store category Names for less manual input
		//Animals = "Animals";
		//Movies = "Movies";
		//Places = "Places";
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
		wordsMovie.add("john wick");
		wordsMovie.add("logan");
		wordsMovie.add("hellraiser");
		wordsMovie.add("scott pilgrim vs the world");
		wordsMovie.add("step brothers");
		wordsMovie.add("parasite");
		wordsPlace = new ArrayList<String>();
		wordsPlace.add("chicago");
		wordsPlace.add("italy");
		wordsPlace.add("new york");
		wordsPlace.add("san francisco");
		wordsPlace.add("berlin");
		wordsPlace.add("prague");
		wordsPlace.add("paris");
		wordsPlace.add("london");
		wordsPlace.add("tokyo");
		wordsPlace.add("hong kong");
		wordsUsed = new ArrayList<String>();
		// null guessArray
		guessArray = null;
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
	
	/*private void setCurrentWord(String word)
	{
		currentWord = word;
	}*/
	
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
	
	// get/set guesses
	public Integer getGuesses()
	{
		return guesses;
	}
	
	public void setGuesses(Integer newGuesses)
	{
		guesses = newGuesses;
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
	
	/*
	 * Select unused word to be guessed, and add it to usedWords
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
	
	// add/clear words to usedWords
	/*
	private void addToUsedWords(String word)
	{
		wordsUsed.add("word");
	}
	
	private void clearUsedWords()
	{
		wordsUsed.clear();
	}*/
	
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
		wordsUsed.add(temp);
		currentWord = temp;
	}
	
	// 
	
	
	

	
	
}