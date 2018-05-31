package midtermproject;

/*
 * 	Grand Circus Java Bootcamp
 * 		April 2018 Cohort
 * 		Midterm Project - Minefield
 * 			John Aoraha, Tim Pieniazek, Victoria Rush, Jonah Wooten
 * 			https://github.com/Jonah-Wooten/MineField
 */
import java.util.Scanner;

public class MainApp {

	// Global variables available to all methods in this file
	// Hidden array of mine field
	static boolean[][] bombs;
	// Play field stores current stat of display
	static String[][] array;
	// Keeps track of play determine the win condition
	static int winCount;
	static final String BLANK_CELL = "-";
	static final String BOMB_CELL = "!";
	static final String FLAG_CELL = "F";

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int input = 0; // input for user menu selection
		String fu; // User to select flag or uncover
		int row; // User selection to uncover or flag a cell
		int column; // User selection to uncover or flag a cell
		int max = 0; // integer used to define maximum row and column size
		String cont = "y"; // User input to play another game or end

		int mineCount; // Number of mines placed - used to determine win condition

		System.out.println("Welcome to minefield!");

		while (cont.equalsIgnoreCase("y")) {
			boolean gameOver = false;
			System.out.println("Please select a minefield size:");
			System.out.println("1. 3x3");
			System.out.println("2. 6x6");
			System.out.println("3. 10x10");
			System.out.println("4. Exit");

			input = Validator.getInt(scan, "Enter a selection: ", 1, 4);

			if (input == 1) {
				System.out.println("You've selected a 3x3 grid: ");
				max = 3;
			}
			if (input == 2) {
				System.out.println("You've selected a 6x6 grid: ");
				max = 6;
			}
			if (input == 3) {
				System.out.println("You've selected a 10x10 grid: ");
				max = 10;
			}
			if (input == 4) {

				gameOver = true;
				cont = "n";
			}
			// 20% of cells will be mines
			mineCount = (int) (max * max / 5);

			// keeps track of mines flagged to determine a win
			winCount = max * max - mineCount;
			// Set play field i.e. place bombs
			bombs = Grid.setBombs3(max, max, mineCount);
			// Display grid shows O on unclicked cells, F on flagged cells, and space on
			// cleared cells with no adjacent mines
			array = Grid.generateDisplay(max, max);

			if (input != 4) {
				while (!gameOver && winCount > 0) {
					System.out.println();
					Display.renderGrid(array); // display grid
					System.out.println();

					fu = Validator.getString(scan, "Would you like to (f)flag a mine or (u)uncover a cell?  ");

					if (fu.equalsIgnoreCase("f")) {
						System.out.println("You've chosen to flag a cell.  Which cell would you like to flag?");
						row = Validator.getInt(scan, "Enter row(x): ", 1, max);
						column = Validator.getInt(scan, "Enter column(y): ", 1, max);
						Display.clearScreen();
						toggleFlag(row - 1, column - 1);
						input = 4;
					} else if (fu.equalsIgnoreCase("u")) {
						System.out.println("You've chosen to uncover a cell.  Which cell would you like to uncover?");
						row = Validator.getInt(scan, "Enter row(x): ", 1, max);
						column = Validator.getInt(scan, "Enter column(y): ", 1, max);
						Display.clearScreen();

						// Don't step on a flagged mine
						if (!array[row - 1][column - 1].equals(FLAG_CELL)) {
							revealMine(row - 1, column - 1);
							if (bombs[row - 1][column - 1]) {
								gameOver = true;
							}
						}
						input = 4;
					} else {
						System.out.println("Invalid selection.");
					}

				}
				//
				if (winCount <= 0) {
					System.out.println("\nCongratulations! You've WON!!!\n");
					Display.renderGrid(array);
					Display.clearScreen();

				}

				if (gameOver) {
					gameOver();
				}

				cont = Validator.getYesOrNo(scan, "Would you like to continue (y/n?): ");
				System.out.println();
			}
		}

		System.out.println();
		System.out.println("Goodbye!");

	}

	// Method checks if the position in the display array is an "O"
	// if it is, then the space hasn't been clicked before so it
	// is safe to decrement our winCount
	public static void decWinCount(int x, int y) {
		if (array[x][y].equals(BLANK_CELL)) {
			winCount--;
		}
	}

	public static void toggleFlag(int x, int y) {

		if (array[x][y].equals(BLANK_CELL)) {
			array[x][y] = FLAG_CELL;
		} else if (array[x][y].equals(FLAG_CELL)) {
			array[x][y] = BLANK_CELL;
		}
	}

	public static void revealMine(int x, int y) {
		int i = MinesNear.calculateMinesNear(bombs, x, y);
		if (i == 0) {
			openSurroundingFields(x, y);
		} else if (i == 9) {
			array[x][y] = BOMB_CELL;
		} else {
			decWinCount(x, y);
			array[x][y] = Integer.toString(i);
		}

	}

	// Runs out of revealMine() if the space is " " aka zero.
	// cycles around that spot in a 3X3 grid to reveal those spot
	// if it finds another zero-spot (not already revealed), then it recurs itself
	public static void openSurroundingFields(int i, int e) {
		for (int j = i - 1; j < i + 2; j++)
			for (int h = e - 1; h < e + 2; h++)
				if ((j > -1) && (j < array.length) && (h > -1) && (h < array[0].length) && (array[j][h] != " ")) {
					if (MinesNear.calculateMinesNear(bombs, j, h) == 0) {
						decWinCount(j, h);
						array[j][h] = " ";
						openSurroundingFields(j, h);
					} else if (MinesNear.calculateMinesNear(bombs, j, h) != 9) {
						decWinCount(j, h);
						array[j][h] = Integer.toString(MinesNear.calculateMinesNear(bombs, j, h));
					}
				}
	}

	// This means you lost.. =(
	// fills out rest of gameboard, so you can know what you did wrong
	public static void gameOver() {
		Display.clearScreen();
		System.out.println("Game Over! Oh no, you hit a mine!");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				revealMine(i, j);
			}
		}
		Display.renderGrid(array);
		System.out.println();

	}
}