import java.util.Scanner;

public class Main {

	public static void main(String[] args) {		
		Field field = new Field();	//initialize a new field
		
		Scanner scanner = new Scanner(System.in);	//initializes a new Scanner to gain acces to user Input of the Console
			
		String[] players = setPlayerNames(scanner);
		String player1_X = players[0];
		String player2_O = players[1];
		
		String currentPlayer = selectStartingPlayer(player1_X, player2_O, scanner);	//randomly selects the starting Player
		
		while(checkWin(player1_X, player2_O, field).equals("null") && !field.isFull()) {	//run the game while it has not been finished
			currentPlayer = executeMove(player1_X, player2_O, currentPlayer, field, scanner);					
		}
		
		System.out.println("-----------------------------------------------");
		field.draw(player1_X, player2_O);		
		System.out.println("The game has come to an end.");
		
		String winner = "";
		if((winner = checkWin(player1_X, player2_O, field)) != null) 
			System.out.println("The winner is: " + winner);
		 else
			System.out.println("It´s a tie");
	}
	
	private static String selectStartingPlayer(String player1_X, String player2_O, Scanner scanner) {
		String startingPlayer = " ";
		
		while(startingPlayer != player1_X && startingPlayer != player2_O) {
			System.out.println("Who wants to start?");
			startingPlayer = scanner.nextLine();
			
			if((startingPlayer.equals(player1_X) || startingPlayer.equals(player2_O))) {
				return startingPlayer;
			} else {
				System.out.println("Not a valid choice! Please choose " + player1_X + " or " + player2_O);
			}
		}
		return null;
	}
	
	private static String[] setPlayerNames(Scanner scanner) {	
		String[] players = new String[2];
		
		System.out.println("What is the name of player1 (X)?");
		players[0] = scanner.nextLine();
		
		System.out.println("What is the name of player2 (O)?");
		players[1] = scanner.nextLine();
		
		if(players[0].equals(players[1])) {
			System.out.println("Both names are the same! Please select different names.");
			players = setPlayerNames(scanner);
		}
			
		return players;
	}
	
	private static String executeMove(String player1_X, String player2_O, String currentPlayer, Field field, Scanner scanner) {
		System.out.println("-----------------------------------------------");
		System.out.println("It´s " + currentPlayer + "´s turn. \n" + currentPlayer + ", please choose a number between 1 and 9 \nthat is not already occupied.");
		field.draw(player1_X, player2_O);		
				
		try {
			int pos = scanner.nextInt();
			
			if(pos >=1 && pos <=9) {
				if(field.isEmpty(pos)) {
					field.set(player1_X, player2_O, currentPlayer, pos);
					currentPlayer = currentPlayer.equals(player1_X) ? player2_O : player1_X;
					return currentPlayer;
				} else {	//field is already occupied
					System.out.println("Your input field is already occupied! Please select another one!");
					return currentPlayer;
				}
			} else {	//the input number was not a number between 1 and 9
				System.out.println("Your input is not valid! Please choose a value between 1 and 9.");
				return currentPlayer;
			}
		} catch(Exception e) {	//the input value was not a number
			scanner.next();
			System.out.println("Your input is not valid! Please choose an integer value.");
			return currentPlayer;
		}
	} 
	
	private static String checkWin(String player1_X, String player2_O, Field field) {	//check if a player has won
		String[] ar = field.getFieldArray();
		String[] combinations = new String[8];	//array of all possible combinations to win
		
		combinations[0] = ar[0] + ar[3] + ar[6];
		combinations[1] = ar[1] + ar[4] + ar[7];
		combinations[2] = ar[2] + ar[5] + ar[8];
		combinations[3] = ar[0] + ar[1] + ar[2];
		combinations[4] = ar[3] + ar[4] + ar[5];
		combinations[5] = ar[6] + ar[7] + ar[8];
		combinations[6] = ar[0] + ar[4] + ar[8];
		combinations[7] = ar[2] + ar[4] + ar[6];
		
		for(String i : combinations) {
			if(i.equals("XXX"))			//if there a 3 X´s in a row/column/diagonal, player 1 wins
				return player1_X;
			if(i.equals("OOO"))			//if there a 3 O´s in a row/column/diagonal, player 2 wins
				return player2_O;
		}
		return "null";					//return null if none of the two players has won
	}
}
