package midtermproject;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 	Grand Circus Java Bootcamp
 * 		April 2018 Cohort
 * 		Midterm Project - Minefield
 * 			John Aoraha, Tim Pieniazek, Victoria Rush, Jonah Wooten
 * 			https://github.com/Jonah-Wooten/MineField
 */
public class Grid {

	public boolean[][] randomGrid;
	public int mineCount;

	public String[][] displayGrid;

	public static boolean[][] generateRandom(int x, int y) {
		return new boolean[x][y];

	}

	public static String[][] generateDisplay(int x, int y) {
		String[][] temp = new String[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				temp[i][j] = MainApp.BLANK_CELL;
			}
		}
		return temp;
	}

	// public boolean[][] getRandom() {
	// return randomGrid;
	// }
	//
	// public void setRandom(boolean[][] random) {
	// this.randomGrid = random;
	// }
	//
	// public int getMineCount() {
	// return mineCount;
	// }
	//
	// public void setMineCount(int mineCount) {
	// this.mineCount = mineCount;
	// }
	//
	// public String[][] getDisplayGrid() {
	// return displayGrid;
	// }
	//
	// public void setDisplayGrid(String[][] displayGrid) {
	// this.displayGrid = displayGrid;
	// }
	//

	public static boolean[][] setBombs3(int x, int y, int mineCount) {

		boolean[][] temp = new boolean[x][y];
		ArrayList<Boolean> bombScramble = new ArrayList<>();

		for (int i = 0; i < mineCount; i++) {
			bombScramble.add(true);
		}
		int notBomb = x * y - mineCount;

		for (int i = 0; i < notBomb; i++) {
			bombScramble.add(false);
		}
		Collections.shuffle(bombScramble);

		int index = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				temp[i][j] = bombScramble.get(index++);
			}
		}
		return temp;
	}

}
