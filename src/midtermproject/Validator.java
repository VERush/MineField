package midtermproject;

/*
 * 	Grand Circus Java Bootcamp
 * 		April 2018 Cohort
 * 		Midterm Project - Minefield
 * 			John Aoraha, Tim Pieniazek, Victoria Rush, Jonah Wooten
 * 			https://github.com/Jonah-Wooten/MineField
 */
import java.util.Scanner;

public class Validator {

	public static int getInt(Scanner sc, String prompt) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine();
		}
		return i;
	}

	public static int getInt(Scanner sc, String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i < min)
				System.out.println("Please enter a number between " + min + " and " + max + ".");
			else if (i > max)
				System.out.println("Please enter a number between " + min + " and " + max + ".");
			else
				isValid = true;
		}
		return i;
	}
	
    public static String getString(Scanner sc, String prompt)
    {
        System.out.print(prompt);
        String s = sc.next();  
        sc.nextLine();  
        return s;
    }
    
    public static String getYesOrNo(Scanner scan, String prompt) {
		String s = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.printf(prompt);
			s = scan.next(); // read user entry
			if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y")) {
				return "y";
			} else if (s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n")) {
				return "n";
			} else {
				System.out.print("\nInput not recognized. ");
				prompt = "(enter \"y\" or \"n\"): ";
			}
			
		}
		return s;
		
	}
}
