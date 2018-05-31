package midtermproject;

/*
 * 	Grand Circus Java Bootcamp
 * 		April 2018 Cohort
 * 		Midterm Project - Minefield
 * 			John Aoraha, Tim Pieniazek, Victoria Rush, Jonah Wooten
 * 			https://github.com/Jonah-Wooten/MineField
 */
public class MinesNear {

	public static int calculateMinesNear(boolean mines[][], int x, int y) {



		// This method assumes grid coordinates are zero-based


		int minesNear = 0;
		int xBegin, xEnd, yBegin, yEnd;

		// Determine if we're on an edge and set indices so we don't go outside the
		// mines array
		if (x == 0) {
			xBegin = 0;
		} else {
			xBegin = x - 1;
		}
		if (x == mines.length - 1) {
			xEnd = mines.length - 1;
		} else {
			xEnd = x + 1;
		}

		if (y == 0) {
			yBegin = 0;
		} else {
			yBegin = y - 1;
		}
		if (y == mines.length - 1) {
			yEnd = mines.length - 1;
		} else {
			yEnd = y + 1;
		}

		if (mines[x][y]) {
			// Oh no! They hit a mine!
			minesNear = 9;
		} else {
			// Cycle through rows nearby
			for (int i = xBegin; i <= xEnd; i++) {
				// Cycle through columns nearby
				for (int j = yBegin; j <= yEnd; j++) {
					// if there's a mine, count it
					if (mines[i][j]) {
						minesNear++;
					}
				}
			}
		}

		return minesNear;
	}
}
