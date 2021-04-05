import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

class GameLogicTest
{

	private static GameLogic game;
	
	@BeforeEach
	void setup()
	{
		game = new GameLogic();
	}
	
	/*
	 *  Test Constructor
	 */
	@Test
	void testConstructor()
	{
		assertEquals("GameLogic", game.getClass().getName(),
				"GameLogic constructor failed");
	}

	/*
	 *  String currentWord
	 *  	- setter is private
	 *
	@Test
	void testGetCurrentWord()
	{
		assertEquals(1, game.getCurrentWord(), "game.getCurrentWord() failed");
	}*/
	
	/*
	 * String currentCategory
	 */
	@Test
	void testCategoryInit()
	{
		game.setCategory("Animals");
		assertEquals("Animals", game.getCategory(), "get/set Animals category failed");
		game.setCategory("Movies");
		assertEquals("Movies", game.getCategory(), "get/set Movies category failed");
		game.setCategory("Places");
		assertEquals("Places", game.getCategory(), "get/set Places category failed");
	}
	
	/*
	 * Integer guesses
	 */
	@Test
	void testGetGuessesInit()
	{
		assertEquals(6, game.getGuesses(), "getGuesses() failed");
	}
	/*
	@Test
	void testDecrementingGuesses()
	{
		game.setGuesses(game.getGuesses()-1);
		assertEquals(5, game.getGuesses(), "getGuesses() failed while decrementing");
		game.setGuesses(game.getGuesses()-1);
		assertEquals(4, game.getGuesses(), "getGuesses() failed while decrementing");
		game.setGuesses(game.getGuesses()-1);
		assertEquals(3, game.getGuesses(), "getGuesses() failed while decrementing");
	}*/
	
	/*
	 * Boolean winAnimal, winPlace, winMovie
	 * Test functionality of tracking users' completion of categories
	 */
	@Test
	void testGetWins()
	{
		
		assertFalse(game.getWin("Animals"));
		assertFalse(game.getWin("Movies"));
		assertFalse(game.getWin("Places"));
	}
	
	@Test
	void testGetWinOfCurrentCategory()
	{
		game.setCategory("Animals");
		assertFalse(game.getWin(game.getCategory()), 
				"get wins for Animlas category failed");
		game.setCategory("Movies");
		assertFalse(game.getWin(game.getCategory()), 
				"get wins for Movies category failed");
		game.setCategory("Places");
		assertFalse(game.getWin(game.getCategory()), 
				"get wins for Places category failed");
	}
	
	/*
	 * Integer lossAnimal, lossMovie, lossPlace
	 */
	@Test
	void testGetLoss()
	{
		assertEquals(0, game.getLoss("Animals"));
		assertEquals(0, game.getLoss("Movies"));
		assertEquals(0, game.getLoss("Places"));
	}
	
	/*
	 * Test guessArray function
	 */
	@Test
	void testGetGuessArrayAtStart()
	{
		assertNull(game.getGuessArray(), "guessArray init failed");
	}
	
	@Test
	void testInitGuessArray()
	{
		game.setCategory("Animals");
		game.playNextWord();
		// assert each character of guessArray is an underscore
		for(char c:game.getGuessArray())
		{
			assertEquals('_', c,"InitGuessArray() failed for Animals category");
		}
		
		game.setCategory("Movies");
		game.playNextWord();
		// assert each character of guessArray is an underscore
		for(char c:game.getGuessArray())
		{
			assertEquals('_', c,"InitGuessArray() failed for Movies category");
		}
		
		game.setCategory("Places");
		game.playNextWord();
		// assert each character of guessArray is an underscore
		for(char c:game.getGuessArray())
		{
			assertEquals('_', c,"InitGuessArray() failed for Places category");
		}
	}
	
	/*
	 * Test behavior of choosing unique, random words from
	 * each category
	 */
	@Test
	void testPlayNextWordAnimal()
	{
		ArrayList<String> uniqueWordsPlayed = new ArrayList<String>();
		game.setCategory("Animals");
		// play 10 words
		for (int i = 0; i < 10; i++)
		{
			game.playNextWord();
			for (String s:uniqueWordsPlayed)
			{
				// assert word is unique
				assertNotEquals(s,game.getCurrentWord(),
						"non-unique word played in Animals Category");
			}
			//System.out.println(game.getCurrentWord());
			uniqueWordsPlayed.add(game.getCurrentWord());
		}
		// Assert size of wordsPlayed == 10
		assertEquals(10,uniqueWordsPlayed.size(), 
				"incorrect uniqueWordsPlayed.size() for Animals Category");
	}
	
	@Test
	void testPlayNextWordMovies()
	{
		ArrayList<String> uniqueWordsPlayed = new ArrayList<String>();
		game.setCategory("Movies");
		// play 10 words, assert each word played is unique
		for (int i = 0; i < 10; i++)
		{
			game.playNextWord();
			for (String s:uniqueWordsPlayed)
			{
				assertNotEquals(s,game.getCurrentWord(),
						"non-unique word played in Movies Category");
			}
			//System.out.println(game.getCurrentWord());
			uniqueWordsPlayed.add(game.getCurrentWord());
		}
		// Assert size of wordsPlayed == 10
		assertEquals(10,uniqueWordsPlayed.size(), 
				"incorrect uniqueWordsPlayed.size() for Movies Category");
	}
	
	@Test
	void testPlayNextWordPlaces()
	{
		ArrayList<String> uniqueWordsPlayed = new ArrayList<String>();
		game.setCategory("Places");
		// play 10 words, assert each word played is unique
		for (int i = 0; i < 10; i++)
		{
			game.playNextWord();
			for (String s:uniqueWordsPlayed)
			{
				assertNotEquals(s,game.getCurrentWord(),
						"non-unique word played in Places Category");
			}
			//System.out.println(game.getCurrentWord());
			uniqueWordsPlayed.add(game.getCurrentWord());
		}
		// Assert size of wordsPlayed == 10
		assertEquals(10,uniqueWordsPlayed.size(), 
				"incorrect uniqueWordsPlayed.size() for Places Category");
	}
	
	/*
	 * Test methods that manage game State:
	 * 
	 * Since words are retrieved at random, I will guess each vowel to test
	 * these behaviors. There will be at least one hit and one miss that can
	 * validate the behaviors of these methods
	 */
	@Test
	void testPlayNextGuessOutput()
	{
		String vowelArray = "aeiou";
		String locations = null;
		game.setCategory("Movies");
		game.playNextWord();
		
		// Guess each vowel
		for(char c:vowelArray.toCharArray())
		{
			locations = game.playNextGuess(c);
			if(!locations.startsWith("-"))	// vowel found in currentWord
			{
				for(char ch:locations.toCharArray())
				{
					/*
					 * Does each char in locations correspond to an index
					 * of currentWord == char?
					 */
					assertEquals(c, game.getCurrentWord().charAt(Character.getNumericValue(ch)),
							"playNextGuess failed");
				}
			}
			else // vowel not found in currentword
			{
				for(char ch:game.getCurrentWord().toCharArray())
				{
					assertNotEquals(c,ch,"false negative in playNextGuess");
				}
			}
			
		} // end for loop
	} // end Test
	
	@Test
	void testUpdateGuessArray()
	{
		String vowelArray = "aeiou";
		String locations = null;
		game.setCategory("Movies");
		game.playNextWord();
		
		// Guess each vowel
		for(char c:vowelArray.toCharArray())
		{
			locations = game.playNextGuess(c);
			if(!locations.startsWith("-"))	// vowel found in currentWord
			{
				for(char ch:locations.toCharArray())
				{
					/*
					 * Does each char in locations correspond to an index
					 * of guessArray == char?
					 */
					assertEquals(c, game.getGuessArray()[Character.getNumericValue(ch)],
							"playNextGuess did not update guessArray properly");
				}
			}		
		} // end for loop
	} // end Test
	
	// TODO: test playResult()
}
