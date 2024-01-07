import java.text.DecimalFormat;

import javax.swing.JOptionPane;

/**
 * @author Ashwin
 * Date: May 18, 2022
 * Description: Asks the user for the number of hours they worked as well as their hourly wage. It then calls the 
 * checkInput method to check if the number of hours worked is valid. If it is valid, the calculateEarnings method is called to 
 * calculate the employee's total earnings and displays it to the user. 
 * Method List:
 * void main(String[] args) - main method
 * double calculateEarnings () - Calculates the earnings of an employee based on the hours worked and the hourly rate
 * boolean checkInput () - Checks if the input is valid. Valid inputs are non-negative and up to 24
 *
 */
public class EarningsCalculator {

	/*
	 * method to calculate the user's earnings using unputs of the number of hours worked 
	 * and the user's hourly wage
	 */
	public static double calculateEarnings (int numOfHours, double hourlyWage) {
	//declare and initialize variable for the earnings
		double earnings = 0;
		//if numOfHours is up to 8 hours (inclusive)
		if (numOfHours >= 0 && numOfHours <=8) {
			//calculate earnings
			earnings = hourlyWage * numOfHours;
		}
		//if numOfHours is > 8
		else {
			//calculate earnings
			earnings = (hourlyWage * 8) + ((hourlyWage * 1.5) * (numOfHours - 8));
		}
		//return the calculated earnings
		return earnings;
	}
	
	/*
	 * Method used to check if user inputed number of hours worked is valid using input if user inputed hours
	 */
	public static boolean checkInput (int input) {
		//if value of input not negative and up to 24 (inclusive)
		if (input >=0 && input <=24) {
			//return true
			return(true);
		}
		//if input is negative or > 24
		else {
			//return false
			return(false);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//declare and initialize variables for the number of hours worked, the hourly wage and a boolean variable to store if inputed hours is valid (true/false)
		int numOfHours;
		double hourlyWage, totalEarnings;
		boolean numberOfHours;

		//formatting for two decimal places
		DecimalFormat twoDigits = new DecimalFormat("0.00");

		//prompt for and get the number of hours worked
		numOfHours = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the number of hours worked (max 24)"));

		//call method checkInput to check if inputed number of hours is valid
		numberOfHours = checkInput(numOfHours);

		//loop while the inputed number of hours is not valid
		while (numberOfHours != true) {
			//display error message
			JOptionPane.showMessageDialog(null, "Error! Invalid input");
			//prompt for and get the number of hours worked from the user again
			numOfHours = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the number of hours worked (max 24)"));
			//call method checkInput to check if inputed number of hours is valid
			numberOfHours = checkInput(numOfHours);
		}
		
		//prompt for and get the user's hourly wage
		hourlyWage = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the hourly wage"));

		//call method calculateEarnings to calculate the user's total earnings
		totalEarnings = calculateEarnings(numOfHours, hourlyWage);

		//display the user's total earnings
		JOptionPane.showMessageDialog(null,  "you total earnings for " + numOfHours + " hours with $" + twoDigits.format(hourlyWage) + " hourly wage is $" + twoDigits.format(totalEarnings));
	}

}
