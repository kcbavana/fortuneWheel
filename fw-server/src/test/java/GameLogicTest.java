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
	void testAnimalCategory()
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
	void testGetGuesses()
	{
		assertEquals(6, game.getGuesses(), "getGuesses() failed");
	}
	
	@Test
	void testDecrementingGuesses()
	{
		game.setGuesses(game.getGuesses()-1);
		assertEquals(5, game.getGuesses(), "getGuesses() failed while decrementing");
		game.setGuesses(game.getGuesses()-1);
		assertEquals(4, game.getGuesses(), "getGuesses() failed while decrementing");
		game.setGuesses(game.getGuesses()-1);
		assertEquals(3, game.getGuesses(), "getGuesses() failed while decrementing");
	}
	
	/*
	 * Boolean winAnimal, winPlace, winMovie
	 * Test functionality of tracking users' completion of categories
	 * 		- setWin() is private
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
	 * 		-setLoss is private
	 */
	@Test
	void testGetLoss()
	{
		assertEquals(0, game.getLoss("Animals"));
		assertEquals(0, game.getLoss("Movies"));
		assertEquals(0, game.getLoss("Places"));
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
		// play 10 words, assert each word played is unique
		for (int i = 0; i < 10; i++)
		{
			game.playNextWord();
			for (String s:uniqueWordsPlayed)
			{
				assertNotEquals(s,game.getCurrentWord(),
						"non-unique word played in Animals Category");
			}
			System.out.println(game.getCurrentWord());
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
			System.out.println(game.getCurrentWord());
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
			System.out.println(game.getCurrentWord());
			uniqueWordsPlayed.add(game.getCurrentWord());
		}
		// Assert size of wordsPlayed == 10
		assertEquals(10,uniqueWordsPlayed.size(), 
				"incorrect uniqueWordsPlayed.size() for Places Category");
	}
	
	
	
	
}
