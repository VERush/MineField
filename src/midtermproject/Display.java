package midtermproject;

/*
 * 	Grand Circus Java Bootcamp
 * 		April 2018 Cohort
 * 		Midterm Project - Minefield
 * 			John Aoraha, Tim Pieniazek, Victoria Rush, Jonah Wooten
 * 			https://github.com/Jonah-Wooten/MineField
 */
public class Display {
	
	public static void renderGrid(String[][] displayGrid) {
		int x = displayGrid.length;
		int y = displayGrid[0].length;
		// i cycles through x-coordinates
		for (int i = 0; i <= x; i++) {
			
			// j cycles through y-coordinates
			for (int j = 0; j <= y; j++) {

				// first row displays " " + 1 to y
				if (i == 0 && j == 0) {
					System.out.print("  ");

				} else if (i == 0) {
					System.out.print(" " + j);
					
				// following rows display x + "OOOO..."
				} else if (j == 0) {
					System.out.println();
					System.out.print(String.format("%2s", i));
					
				} else {
									
					System.out.print(" " + displayGrid[i-1][j-1]);
				
				}
			}
		}
		System.out.println();
	}
	
	
	public static void clearScreen() {
		for (int i = 0; i < 2; i++) {
			System.out.println();
		}
	}
}
