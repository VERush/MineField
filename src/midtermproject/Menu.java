package midtermproject;

import java.util.Scanner;

public class Menu {

	private static int input;
	private static String cont = "y";
	private static int gridSize;
	private boolean gameOver;

	public Menu() {
	}

	public void input(Scanner scan) {

		System.out.println("Please select a minefield size:");
		System.out.println("1. 3x3");
		System.out.println("2. 4x4");
		System.out.println("3. 5x5");
		System.out.println("4. Exit");

		input = Validator.getInt(scan, "Enter a selection: ", 1, 4);

		if (input == 1) {
			System.out.println("You've selected a 3x3 grid: ");
			gridSize = 3;
		}
		if (input == 2) {
			System.out.println("You've selected a 4x4 grid: ");
			gridSize = 4;
		}
		if (input == 3) {
			System.out.println("You've selected a 5x5 grid: ");
			gridSize = 5;
		}
		if (input == 4) {
//			gameOver = true;
			cont = "n";
		}
	}

	public static int getGridSize() {
		return gridSize;
	}

	public static int getInput() {
		return input;
	}

	public static String getCont() {
		return cont;
	}

}
