import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;



public class SnakeNLadderTest {

	public static void main(String[] args) {
		SnakeNLadder s = new SnakeNLadder();
		s.startGame(); //It starts the game.

	}

}

class SnakeNLadder
{
	//It defines a constant WINPOINT with a value of 100.
	final static int WINPOINT = 100;
	
	//Hashmap will store the entries
	static Map<Integer,Integer> snake = new HashMap<Integer,Integer>();
	static Map<Integer,Integer> ladder = new HashMap<Integer,Integer>();

	// create a HashMap called snake and a HashMap called ladder.
	{
		snake.put(99,54);
		snake.put(70,55);
		snake.put(52,42);
		snake.put(25,2);
		snake.put(95,72);
		
		ladder.put(6,25);
		ladder.put(11,40);
		ladder.put(60,85);
		ladder.put(46,90);
		ladder.put(17,69);
	}
	

	//It simulates the rolling of a die.
	
	public int rollDice()
	{
		int n = 0;
		Random r = new Random();
		n=r.nextInt(7);  ////It returns a random number between 1 and 6.
		return (n==0?1:n); //It returns 1 if n is 0, otherwise it returns n.
	}
	
	public void startGame()
	{
		int player1 =0, player2=0;
		int currentPlayer=-1;  //It creates a variable called currentPlayer and sets it to -1.
		Scanner s = new Scanner(System.in);
		String str;
		int diceValue =0;
		do
		{

			//It first asks the user to press r to roll the dice.
			// It then rolls the dice and displays the value.
			System.out.println(currentPlayer==-1?"\n\nFIRST PLAYER TURN":"\n\nSECOND PLAYER TURN");
			System.out.println("Press r to roll Dice");
			str = s.next();
			diceValue = rollDice();
			
			//It then checks if the current player has won.
			//If the current player has won, it prints the winner and returns.
			if(currentPlayer == -1)
			{
				player1 = calculatePlayerValue(player1,diceValue);
				System.out.println("First Player :: " + player1);
				System.out.println("Second Player :: " + player2);
				System.out.println("------------------");
				if(isWin(player1))
				{
					System.out.println("First player wins");
					return;
				}
			}
			//If the current player has not won, it switches the current player.
			//It then asks the user to press r to roll the dice.
			else
			{
				player2 = calculatePlayerValue(player2,diceValue);
				System.out.println("First Player :: " + player1);
				System.out.println("Second Player :: " + player2);
				System.out.println("------------------");
				if(isWin(player2))
				{
					System.out.println("Second player wins");
					return;
				}
			}
			//If the current player has not won, it switches the current player.
			//it then goes back to step 1.
			currentPlayer= -currentPlayer;
			
			
			
		}
		//The code above will print out "r"
		while("r".equals(str));
	}
	
	//The method calculates the player value by adding the dice value to the player value.
	public int calculatePlayerValue(int player, int diceValue)
	{
		player = player + diceValue;

		//If the player value is greater than the WINPOINT, then the player value is subtracted by the dice value.
		if(player > WINPOINT)
		{
			player = player - diceValue;
			return player;
		}
		//If the player value is present in the snake map, then the player value is set to the value of the snake map.
		if(null!=snake.get(player))
		{
			System.out.println("swallowed by snake");
			player= snake.get(player);
		}
	//If the player value is present in the ladder map, then the player value is set to the value of the ladder map.
		if(null!=ladder.get(player))
		{
			System.out.println("climb up the ladder");
			player= ladder.get(player);
		}
		return player;
	}

	// The method isWin() takes a parameter player, which is the player who has won the game.
	public boolean isWin(int player)
	{
		// The method returns true if the player is equal to WINPOINT.
		// If the player is equal to WINPOINT, then the player has won the game.
		return WINPOINT == player;
	}
	
}