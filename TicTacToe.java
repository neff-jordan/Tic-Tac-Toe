import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {

		// This is the 2d array that prints the game board throughout the game. 
		
		char [][] gameboard = {{' ', '|', ' ', '|', ' '},
							   {'-', '+', '-', '+', '-'},
							   {' ', '|', ' ', '|', ' '},
							   {'-', '+', '-', '+', '-'},
							   {' ', '|', ' ', '|', ' '}};
		
		printgameboard(gameboard);
		
		/* lines 28-35 first prompt the user for a position to put their 'X'. It then prevents the user from placing their piece on a spot that is 
		 * already occupied by the CPU.
		 */
		
	while(true) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your placement (1-9):");
		int playerPos = scan.nextInt();
		while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions) ) {
			System.out.println("Position taken! Enter a correct position.");
			playerPos = scan.nextInt();
		}
			
		
		placePiece(gameboard, playerPos, "player");
		
		String result = checkWinner();
		if(result.length() > 0) {
			System.out.println(result);
			break;
		}	
		
		// lines 48-52 use the Random operator to place the CPU's 'O'. Also prevents it from using a taken spot on the board. 
		
		Random rand = new Random();
		int cpuPos = rand.nextInt(9) + 1;
		while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos) ) {
			cpuPos = rand.nextInt(9) + 1;
		}
		
		placePiece(gameboard, cpuPos, "cpu");
		
		printgameboard(gameboard);
		
		// 60-67 check the conditions of the pieces of the two players. Then prints the appropriate response. 
		
		result = checkWinner();
		if(result.length() > 0) {
			System.out.println(result);
			break;
		}
		System.out.println(result);
	}
	}
		
	public static void printgameboard(char [][] gameboard) {
	
		// This prints the game board 
		
		for(char[] row : gameboard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	
	}

	public static void placePiece(char[][] gameboard, int pos, String user) {
		
		// 86-94 assigns the player symbols and adds them to the game board. 
		
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		// Assigns locations to the entered symbols. i.e. [0][0] = top left. 
		
		switch(pos) {
		
			case 1:
				gameboard[0][0] = symbol;
				break;
			case 2:
				gameboard[0][2] = symbol;
				break;
			case 3:
				gameboard[0][4] = symbol;
				break;
			case 4:
				gameboard[2][0] = symbol;
				break;
			case 5:
				gameboard[2][2] = symbol;
				break;
			case 6:
				gameboard[2][4] = symbol;
				break;
			case 7:
				gameboard[4][0] = symbol;
				break;
			case 8:
				gameboard[4][2] = symbol;
				break;
			case 9:
				gameboard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		
		// 131-155 is creating the rules for how to win. across, diagonal, etc. 
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow); 
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		// Once the conditions above have been satisfied, 159-170 print the result of the game. 
		
		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations you won!";
			} else if(cpuPositions.containsAll(l) ) {
				return "CPU wins! Sorry :(";
			} else if(playerPositions.size() + cpuPositions.size() == 9) {
				return "Draw!";
			}
		}
		return "";
	}
}
