import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 */

/**
 * @author Ashwin
 * Date:
 * Description: Asks the user for the number of hours they worked as well as their hourly wage and their income tax. It then calls the 
 * checkInput method to check if the number of hours worked is valid and also checks if the inputed hourly wage and income tax is valid 
 * If all the inputs are valid, the calculateEarnings method is called to calculate the employee's total earnings. Program uses the returned 
 * value and calls calculatePercent method to calculate the income tax and subtracts it from the users total earnings and displays it to the 
 * user. The final earnings can also be displayed converted to USD and EURO if the user chooses to. 
 * Method List (Imported from EarningCalculator.java and SalaryCalculator.java):
 * void main(String[] args) - main method
 * double calculateEarnings (int numOfHours, double hourlyWage) - Calculates the earnings of an employee based on the hours worked and the hourly rate
 * boolean checkInput (int input) - Checks if the input is valid. Valid inputs are non-negative and up to 24
 * double calculatePercent (double salary, double increaseRate) - method calculates the bonus/additional money ($) 
 *                                                                using the salary and the increase percent (%) as inputs
 *
 *Sources: 
 *To play audio: https://stackoverflow.com/questions/43991004/playing-audio-file-in-eclipse 
 *JRadioButton: https://www.youtube.com/watch?v=cWvtzxmKoII
 *Setting background of window: https://www.codespeedy.com/how-to-change-the-background-color-of-the-jframe-in-java/
 *String put in HTML format: previous knowledge from experience programming in JavaScript
 *Error trapping (try-catch): 10.3 course notes
 */
public class EarningsCalculatorGUI extends JFrame implements ActionListener {
	//variables for labels, buttons, text fields and bubble choice buttons/radio buttons (JRadioButton)
	JLabel lblNumHours, lblHourlyWage, lblIncomeTax, lblPic, lblFinal, lblConvert; 
	JButton btnCalculate, btnClear, btnEnd;
	JTextField hourInput, wageInput, taxInput;
	JRadioButton usd, euro;

	//variable to store final output message in html format so that it can be formatted when put into JLabel
	String finalSalary = "<html></html>";

	//AUDIO: setting path to the audio files and creating a file object for each file path

	//gets the path to the file located in the project and stores it in the variable
	String path = new File("").getAbsolutePath() + "\\mixkit-click-error-1110.wav";
	//create a file object with a path to the audio file
	File erase = new File(path);

	//gets the path to the file located in the project and stores it in the variable
	String path2 = new File("").getAbsolutePath() + "\\mixkit-select-click-1109.wav";
	//create a file object with a path to the audio file
	File click = new File(path2);

	//gets the path to the file located in the project and stores it in the variable
	String path3 = new File("").getAbsolutePath() + "\\Windows error.wav";
	//create a file object with a path to the audio file
	File error = new File(path3);

	/**
	 * Constructor method to build the window
	 */
	public EarningsCalculatorGUI() {
		super("Earnings Calculator"); //title of window

		//create the labels for hours worked, hourly wage, income tax, conversion options and message to display final earnings
		lblNumHours = new JLabel("Hours Worked: ");
		lblHourlyWage = new JLabel("Hourly Wage ($): ");
		lblIncomeTax = new JLabel("Income Tax (%): ");
		lblConvert = new JLabel ("Convert salary to: ");
		lblFinal = new JLabel ("<html>Final salary will appear here<br/>once you click 'calculate'<html>"); //put into html format to format text in a JLabel

		//creating label to display picture also using ImageIcon
		lblPic = new JLabel(new ImageIcon("rsz_1rsz_salary.jpg"));

		//create text input fields for hours worked, hourly wage and income tax
		hourInput = new JTextField ();
		wageInput = new JTextField ();
		taxInput = new JTextField ();

		//Create the buttons for Calculate, Clear and End
		btnCalculate = new JButton ("Calculate");
		btnClear = new JButton ("Clear");
		btnEnd = new JButton ("End");

		//create radio button for usd and euro conversion options
		usd = new JRadioButton("USD");
		usd.setBackground(new Color (255, 204, 203));
		euro = new JRadioButton("EURO");
		euro.setBackground(new Color (255, 204, 203));

		//set the layout so that components can be placed where ever we want on the window 
		setLayout(null);

		//set the bounds of the components and add/place the components

		//labels
		lblNumHours.setBounds(20, 10, 130, 15);
		add(lblNumHours);
		lblHourlyWage.setBounds(20, 40, 130, 15);
		add(lblHourlyWage);
		lblIncomeTax.setBounds(20, 70, 170, 15);
		add(lblIncomeTax);		
		lblFinal.setBounds(10, 150, 200, 140);
		add(lblFinal);
		lblConvert.setBounds(200, 150, 120, 20);
		add(lblConvert);

		//picture
		lblPic.setBounds(150, 160, 200, 200);
		add(lblPic);

		//input text fields
		hourInput.setBounds(150, 10, 160, 25);
		add(hourInput);
		hourInput.setBackground(new Color (207, 238, 250));
		wageInput.setBounds(150, 40, 160, 25);
		add(wageInput);
		wageInput.setBackground(new Color (207, 238, 250));
		taxInput.setBounds(170, 70, 140, 25);
		add(taxInput);
		taxInput.setBackground(new Color (207, 238, 250));

		//buttons
		btnCalculate.setBounds(10, 120, 130, 25);
		add(btnCalculate);
		btnClear.setBounds(150, 120, 80, 25);
		add(btnClear);
		btnEnd.setBounds(240, 120, 80, 25);
		add(btnEnd);

		//radio buttons (conversion options)
		usd.setBounds(200, 170, 100, 20);
		add(usd);
		euro.setBounds(200, 190, 100, 20);
		add(euro);		

		//add the buttons as listeners in this class
		btnCalculate.addActionListener(this);
		btnClear.addActionListener(this);
		btnEnd.addActionListener(this);

		setSize(350, 340); //set dimensions of window
		setLocationRelativeTo(null); //center window on the middle of user's screen
		setDefaultCloseOperation(EXIT_ON_CLOSE); //close/end application when the "x" is  clicked
		Container c = getContentPane(); //retrieves the content pane layer so that objects can be added onto it
		c.setBackground(new Color (255, 204, 203)); //set the colour of the background for the window
		setVisible(true); //make the window visible
	}

	/*
	 * Method to listen to events 
	 */
	public void actionPerformed (ActionEvent e) {

		//check which button was clicked 

		if (e.getSource()==btnClear) { //if the clear button was clicked
			//play the "erase" sound
			try {
				//Obtain the audio input stream from the provided input stream
				AudioInputStream ais = AudioSystem.getAudioInputStream(erase); 
				//Obtains a clip that can be used for playing the audio file
				Clip c = AudioSystem.getClip();
				c.open(ais); //clip opens AudioInputStream
				c.start(); //starts playing audio
			}
			catch (Exception a){

			}
			//clear all input fields as well as resetting the text inside of the JLabel displaying the final earnings
			hourInput.setText("");
			wageInput.setText("");
			taxInput.setText("");
			lblFinal.setText("<html>Final salary will appear here<br/>once you click 'calculate'<html>");
			//reset the usd and euro conversion options to not chosen
			usd.setSelected(false);
			euro.setSelected(false);
			//set colours of all labels back to black
			lblNumHours.setForeground(Color.BLACK);
			lblHourlyWage.setForeground(Color.BLACK);
			lblIncomeTax.setForeground(Color.BLACK);
		}
		else if (e.getSource()==btnEnd) { //if the end button was clicked
			setVisible(false); //make window not visible
			//display thank-you message
			IO.display("      Thank you for using this program\n\nThis program is the property of Ashwin Inc.");
			//end program
			System.exit(0);
		}
		else if (e.getSource()==btnCalculate) { //if the calculate button was clicked
			//play "click" audio
			try {
				//Obtain the audio input stream from the provided input stream
				AudioInputStream ais = AudioSystem.getAudioInputStream(click);
				//Obtains a clip that can be used for playing the audio file
				Clip c = AudioSystem.getClip();
				c.open(ais); //clip opens AudioInputStream
				c.start(); //starts playing audio
			}
			catch (Exception a){

			}

			//use try and catch to check for incorrect input types into the text input fields (error trapping)
			try {
				/*
				 * declare and initialize variables for the number of hours worked, the hourly wage, tax, 
				 * the tax value in dollars, the final earnings in CAD, final earnings in USD, final earnings in EURO
				 * and a boolean variable to store if inputed hours is valid (true/false)
				 */
				int numOfHours;
				double hourlyWage, tax, totalEarnings, taxValue, finalEarnings, finalEarningsUsd, finalEarningsEuro;
				boolean numberOfHours;

				//reset finalSalary
				finalSalary = "";

				//formatting for two decimal places
				DecimalFormat twoDigits = new DecimalFormat("0.00");

				//read the number of hours inputed by the user and convert it into an integer
				numOfHours = Integer.parseInt(hourInput.getText());

				//call method checkInput to check if inputed number of hours is valid
				numberOfHours = EarningsCalculator.checkInput(numOfHours);

				//loop while the inputed number of hours is not valid
				while (numberOfHours != true) {
					//play "error" sound
					try {
						//Obtain the audio input stream from the provided input stream
						AudioInputStream ais = AudioSystem.getAudioInputStream(error);
						//Obtains a clip that can be used for playing the audio file
						Clip c = AudioSystem.getClip();
						c.open(ais); //clip opens AudioInputStream
						c.start(); //starts playing audio
					}
					catch (Exception a){

					}
					//set colour of the JLabel to red to signify error
					lblNumHours.setForeground(Color.RED);
					//display error message
					JOptionPane.showMessageDialog(null, "Error! Invalid input for number of hours worked");
					//prompt for and get the number of hours worked from the user again
					numOfHours = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the number of hours worked (max 24)"));
					//call method checkInput to check if inputed number of hours is valid
					numberOfHours = EarningsCalculator.checkInput(numOfHours);
				}
				//set colour of the JLabel to green to signify valid input
				lblNumHours.setForeground(new Color(68,214,44));

				//convert integer into a string to display in the text field
				String hours = String.valueOf(numOfHours);
				//display the number of hours worked inputed by the user inside of the text field
				hourInput.setText(hours);

				//read the inputed value by the user into the text field for the hourly wage and convert it into a double
				hourlyWage = Double.parseDouble(wageInput.getText());

				//loop while the inputed number of hours is not valid
				while (hourlyWage < 0) {
					//play "error" sound
					try {
						//Obtain the audio input stream from the provided input stream
						AudioInputStream ais = AudioSystem.getAudioInputStream(error);
						//Obtains a clip that can be used for playing the audio file
						Clip c = AudioSystem.getClip();
						c.open(ais); //clip opens AudioInputStream
						c.start(); //starts playing audio
					}
					catch (Exception a){

					}
					//set colour of the JLabel to red to signify error
					lblHourlyWage.setForeground(Color.RED);
					//display error message
					JOptionPane.showMessageDialog(null, "Error! Invalid input for hourly wage");
					//prompt for and get the hourly wage from the user again
					hourlyWage = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter your hourly wage ($)"));
				}
				//set colour of the JLabel to green to signify valid input
				lblHourlyWage.setForeground(new Color(68,214,44));

				//convert the double value for the wage into a string to display in the text field
				String wage = String.valueOf(twoDigits.format(hourlyWage));
				//display the hourly wage inputed by the user in the text field
				wageInput.setText(wage);

				//call method calculateEarnings to calculate the user's total earnings
				totalEarnings = EarningsCalculator.calculateEarnings(numOfHours, hourlyWage);

				//read the inputed value for tax by the user and convert it into a double 
				tax = Double.parseDouble(taxInput.getText());

				//loop while the inputed income tax value is not valid
				while (tax < 0) {
					//play "error" sound
					try {
						//Obtain the audio input stream from the provided input stream
						AudioInputStream ais = AudioSystem.getAudioInputStream(error);
						//Obtains a clip that can be used for playing the audio file
						Clip c = AudioSystem.getClip();
						c.open(ais); //clip opens AudioInputStream
						c.start(); //starts playing audio
					}
					catch (Exception a){

					}

					//set colour of the JLabel to red to signify error
					lblIncomeTax.setForeground(Color.RED);
					//display error message
					JOptionPane.showMessageDialog(null, "Error! Invalid input for income tax");
					//prompt for and get the income tax from the user again
					tax = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter your income tax (%)"));
				}
				//set colour of the JLabel to green to signify valid input
				lblIncomeTax.setForeground(new Color(68,214,44));

				//convert double value of inputed income tax into a string to display in the text field
				String incomeTax = String.valueOf(twoDigits.format(tax));
				//display the inputed income tax in the text field
				taxInput.setText(incomeTax);

				/*
				 * call method calculatePercent to calculate the dollar amount for the tax using 
				 * user inputed percentage income tax
				 */
				taxValue = SalaryCalculator.calculatePercent(totalEarnings, tax);

				//calculate final earnings in CAD
				finalEarnings = totalEarnings - taxValue;

				//if the USD conversion option is chosen and EURO is not
				if (usd.isSelected() && !euro.isSelected()) {
					//calculate final earnings and divide by $1.28296 cad to convert final earnings to usd (conversion factor)
					finalEarningsUsd = finalEarnings/1.28296;

					/*
					 * add the final earnings, conversion to usd and relevant details inputed by user 
					 * to the string finalSalary and put it in html format to format text for JLabel
					 */
					finalSalary = finalSalary + "<html>Final Earnings<br/><br/> Hours Worked: " 
							+ numOfHours + "<br/>Hourly Wage: $" + twoDigits.format(hourlyWage) 
							+ "<br/>Income Tax: %" + twoDigits.format(tax) + "<br/>Final Earnings: $" 
							+ twoDigits.format(finalEarnings) + " CAD<br>Converted to USD: $" + twoDigits.format(finalEarningsUsd) + " USD</html>";

					/*
					 * set the text of the JLabel to display the final earning to the string finalSalary containing the inputed 
					 * details and the user's final earnings as well as its USD conversion
					 */

					lblFinal.setText(finalSalary);
				}
				//if the EURO conversion option is chosen but the USD conversion is not
				else if (euro.isSelected() && !usd.isSelected()) {
					//calculate final earnings and divide by $1.35491 cad to convert final earnings to euro (conversion factor)
					finalEarningsEuro = finalEarnings/1.35491;

					/*
					 * add the final earnings, euro conversion and relevant user inputed details to the string finalSalary 
					 * and put it in html format to format text for JLabel
					 */

					finalSalary = finalSalary + "<html>Final Earnings<br/><br/> Hours Worked: " 
							+ numOfHours + "<br/>Hourly Wage: $" + twoDigits.format(hourlyWage) 
							+ "<br/>Income Tax: %" + twoDigits.format(tax) + "<br/>Final Earnings: $" 
							+ twoDigits.format(finalEarnings) + " CAD<br>Converted to EURO: €" + twoDigits.format(finalEarningsEuro) + "</html>";

					/*
					 * set the text of the JLabel to display the final earnings to the string finalSalary containing 
					 * user inputed details and the user's final earnings as well as its EURO conversion
					 */
					lblFinal.setText(finalSalary);
				}
				//if both the USD and EURO conversion options are selected
				else if (usd.isSelected() && euro.isSelected()) {
					//calculate final earnings and divide by $1.28296 cad to convert final earnings to usd (conversion factor)
					finalEarningsUsd = finalEarnings/1.28296;
					//calculate final earnings and divide by $1.35491 cad to convert final earnings to euro (conversion factor)
					finalEarningsEuro = finalEarnings/1.35491;

					/* 
					 * add the final earnings, usd and euro conversion and relevant user inputed 
					 * details to the string finalSalary and put it in html format to format text for JLabel
					 */

					finalSalary = finalSalary + "<html>Final Earnings<br/><br/> Hours Worked: " 
							+ numOfHours + "<br/>Hourly Wage: $" + twoDigits.format(hourlyWage) 
							+ "<br/>Income Tax: %" + twoDigits.format(tax) + "<br/>Final Earnings: $" 
							+ twoDigits.format(finalEarnings) + " CAD<br>Converted to USD: $"  
							+ twoDigits.format(finalEarningsUsd) +"USD<br>Converted to EURO: €" 
							+ twoDigits.format(finalEarningsEuro) + "</html>";

					/*
					 * set the text of the JLabel to display the final earnings to the string finalSalary containing
					 * user inputed details and the user's final earnings as well as its USD and EURO conversion
					 */
					lblFinal.setText(finalSalary);
				}
				//if neither the USD or EURO conversion options are chosen
				else {
					/*
					 * add the final earnings and relevant user inputed details to the string finalSalary 
					 * and put it in html format to format text for JLabel
					 */
					finalSalary = finalSalary + "<html>Final Earnings<br/><br/> Hours Worked: " 
							+ numOfHours + "<br/>Hourly Wage: $" + twoDigits.format(hourlyWage) 
							+ "<br/>Income Tax: %" + twoDigits.format(tax) + "<br/>Final Earnings: $" 
							+ twoDigits.format(finalEarnings) + " CAD</html>";

					/*
					 * set the text of the JLabel called lblFinal to display the total to the string finalSalary 
					 * containing the user inputed details and total earnings in CAD
					 */
					lblFinal.setText(finalSalary);
				}
			}
			//if an incorrect input is entered
			catch (Exception ee) {
				//play "error" sound
				try {
					//Obtain the audio input stream from the provided input stream
					AudioInputStream ais = AudioSystem.getAudioInputStream(error);
					//Obtains a clip that can be used for playing the audio file
					Clip c = AudioSystem.getClip();
					c.open(ais); //clip opens AudioInputStream
					c.start(); //starts playing audio
				}
				catch (Exception a){

				}
				//show error message
				JOptionPane.showMessageDialog(null, "Incorrect type of input was entered!");
				//reset text fields to blank
				hourInput.setText("");
				wageInput.setText("");
				taxInput.setText("");
				//reset the conversion options to not chosen
				usd.setSelected(false);
				euro.setSelected(false);
				//set colours of all labels back to black
				lblNumHours.setForeground(Color.BLACK);
				lblHourlyWage.setForeground(Color.BLACK);
				lblIncomeTax.setForeground(Color.BLACK);
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//display the programmer's signature message
		IO.display("*************************************************\n"
				+"      DAILY EARNING CALCULATOR   \n"
				+"                                \n"
				+"               By: Ashwin Santhosh         \n"
				+"*************************************************");

		/*
		 * call constructor to display the window with the components (input fields, labels, buttons, 
		 * picture and radio buttons)
		 */
		new EarningsCalculatorGUI();
	}
}
