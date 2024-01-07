import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Ashwin
 * Date: May 10, 2022
 * Description: Display's the user's salary increase given their salary and their percent increase.
 * 				It should prompt the user for 3 people's salaries and percent increases and display 3 
 * 				salary increases as well as final salary with salary increase added. 
 * Method List:
 * void main(String[] args)
 * double calculatePercent (double salary, double increaseRate) - method calculates the bonus/additional money ($) 
 *                                                                using the salary and the increase percent (%) as inputs
 */
public class SalaryCalculator {

	/*
	 * Method to calculate the percent increase
	 */
	public static double calculatePercent (double salary, double increaseRate) {
		//declare variable for the salary increase for salary
		double increase;

		//calculate percent increase
		increase = salary * (increaseRate/100);

		//return the salary increase 
		return increase;
	}


	public static void main(String[] args) {
		//declare variables for the radius and area
		double salary, increaseRate, bonus, finalSalary;
		
		//create text input fields for the salary and percent increase for the user to enter
		JTextField salaryInput = new JTextField();
		JTextField percentInput = new JTextField();
		
		//format to two decimal places
		DecimalFormat twoDigits = new DecimalFormat("0.00");

		
		for (int i = 0; i < 3; i++) {
			//erase previous inputs from user
			salaryInput.setText("");
			percentInput.setText("");
			//put fields in an array of "objects"
			Object [] fields = {
					"Salary for person " + (i+1) + " " + "($)", salaryInput,
					"Percent increase for person " + (i+1)+ " (%)", percentInput
			};
			//prompt for the Salary
			JOptionPane.showConfirmDialog(null, fields, "Input Salary ($) and Percent Increase (%)", JOptionPane.OK_CANCEL_OPTION);

			//translate inputed text into numerical value
			salary = Double.parseDouble(salaryInput.getText());
			increaseRate = Double.parseDouble(percentInput.getText());
			
			//while the inputed salary or the percent increase is < 0
			while (salary < 0 || increaseRate < 0) {
				//prompt for and get salary and increase rate
				JOptionPane.showConfirmDialog(null, fields, "ERROR! Input valid Salary ($) and Percent Increase (%)", JOptionPane.OK_CANCEL_OPTION);
				salary = Double.parseDouble(salaryInput.getText());
				increaseRate = Double.parseDouble(percentInput.getText());
			} //while loop ends
			
			//call the method to calculate and return the percent salary increase 
			bonus = calculatePercent(salary, increaseRate);

			//Display the salary including the percent salary increase
			finalSalary = salary + bonus;

			//display the salary increase as well as the salary after the increase
			JOptionPane.showMessageDialog(null, "the percent salary increase of person " + (i+1) + " is $" 
			+ twoDigits.format(bonus) + "\nThe salary of person " + (i+1) + " including the salary increase is $" 
			+ twoDigits.format(finalSalary));
		}
		//display thank you message at the end of the program
		JOptionPane.showMessageDialog(null, "Thank you for using this program");
	}
}
