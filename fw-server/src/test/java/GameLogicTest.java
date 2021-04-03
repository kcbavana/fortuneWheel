import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
	
}