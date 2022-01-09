import java.util.Scanner;

public class Main {

	public static void main(String[] args) {		
		Field field = new Field();
		Scanner scanner = new Scanner(System.in);	
		String player1_X = "";
		String player2_O = "";
		String players[] = setPlayers(player1_X, player2_O, scanner).split(",");
		player1_X = players[0];
		player2_O = players[1];
		field.getPlayers(player1_X, player2_O);
		
		String currentPlayer = selectStartingPlayer(player1_X, player2_O, scanner);
		while(checkWin(player1_X, player2_O, field).equals("null") && !field.isFull()) {
			currentPlayer = executeMove(player1_X, player2_O, currentPlayer, field, scanner);					
		}
		System.out.println("-----------------------------------------------");
		field.draw();		
		System.out.println("The game has come to an end.");
		
		if(checkWin(player1_X, player2_O, field).equals(player1_X)) 
			System.out.println("The winner is: " + player1_X);
		 else if(checkWin(player1_X, player2_O, field).equals(player2_O)) 
			System.out.println("The winner is: " + player2_O);
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
	
	private static String setPlayers(String player1_X, String player2_O,  Scanner scanner) {

			System.out.println("What is the name of player1 (X)?");
			player1_X = scanner.nextLine();
			System.out.println("What is the name of player2 (O)?");
			player2_O = scanner.nextLine();
			if(player1_X.equals(player2_O)) {
				System.out.println("Both names are the same! Please select different names.");
				setPlayers(player1_X, player2_O, scanner);
			}
			
		
		return player1_X + "," + player2_O;
	}
	
	private static String executeMove(String player1_X, String player2_O, String currentPlayer, Field field, Scanner scanner) {
		System.out.println("-----------------------------------------------");
		System.out.println("It´s " + currentPlayer + "´s turn. \n" + currentPlayer + ", please choose a number between 1 and 9 \nthat is not already occupied.");
		field.draw();
		int pos = 0;		
				
		if(scanner.hasNextInt()) {
			pos = scanner.nextInt();
			if(pos >=1 && pos <=9) {
				if(field.isEmpty(pos)) {
					field.set(player1_X, player2_O, currentPlayer, pos);
					currentPlayer = currentPlayer.equals(player1_X) ? player2_O : player1_X;
					return currentPlayer;
				} else {
					System.out.println("Your input field is already occupied! Please select another one!");
					return currentPlayer;
				}
			} else {
				System.out.println("Your input is not valid! Please choose a value between 1 and 9.");
				return currentPlayer;
			}
		} else {
			scanner.next();
			System.out.println("Your input is not valid! Please choose an integer value.");
			return currentPlayer;
		}
	} 
	
	private static String checkWin(String player1_X, String player2_O, Field field) {
		String[] ar = field.getFieldArray();
		String[] combinations = new String[8];
		combinations[0] = ar[0] + ar[3] + ar[6];
		combinations[1] = ar[1] + ar[4] + ar[7];
		combinations[2] = ar[2] + ar[5] + ar[8];
		combinations[3] = ar[0] + ar[1] + ar[2];
		combinations[4] = ar[3] + ar[4] + ar[5];
		combinations[5] = ar[6] + ar[7] + ar[8];
		combinations[6] = ar[0] + ar[4] + ar[8];
		combinations[7] = ar[2] + ar[4] + ar[6];
		
		for(String i : combinations) {
			if(i.equals("XXX"))
				return player1_X;
			if(i.equals("OOO"))
				return player2_O;
		}
		return "null";
	}
}
