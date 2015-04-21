import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

public class ParticipantQuestionPanel extends QuestionPanel {
	private JTextField nameField;
	private JComboBox<String> countriesBox;
	private boolean submitClicked;
	private String name;
	private String country;
	private int age;
	private JSpinner monthSpinner;
	private JSpinner daySpinner;
	private JSpinner yearSpinner;
	private String participantData; //Uses this added to the results line for admin report

	public ParticipantQuestionPanel() throws FileNotFoundException {
		
		//Set up panel structure and layout
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,480));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
			
		/*----------------Name Question--------------------*/
		//Name question text field
		nameField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, nameField, 47, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, nameField, -197, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, nameField, -45, SpringLayout.EAST, this);
		add(nameField);
		nameField.setColumns(10); 
		
		//Name question label
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, nameField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, -6, SpringLayout.NORTH, nameField);
		add(lblName);
				
		/*----------------Country Question--------------------*/
				
		//Country question combo box
		countriesBox = new JComboBox<String>(countriesList());
		springLayout.putConstraint(SpringLayout.NORTH, countriesBox, 34, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.WEST, countriesBox, 0, SpringLayout.WEST, nameField);
		springLayout.putConstraint(SpringLayout.EAST, countriesBox, 0, SpringLayout.EAST, nameField);
		add(countriesBox);
		
		//Country question label
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, lblCountry, 0, SpringLayout.WEST, nameField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCountry, -6, SpringLayout.NORTH, countriesBox);
		add(lblCountry);
		
		//Date of Birth label 
		//(the spinners for date of birth are below code for Submit button
		JLabel lblDateOfBirth = new JLabel("Date of Birth (mm/dd/yyyy)");
		lblDateOfBirth.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, lblDateOfBirth, 0, SpringLayout.WEST, nameField);
		add(lblDateOfBirth);
				
		/*--------------Submit button-----------*/
		submitClicked = false;
		JButton submitButton = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.SOUTH, submitButton, -34, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblDateOfBirth, -56, SpringLayout.NORTH, submitButton);
		submitButton.addActionListener(new ButtonListener());
		springLayout.putConstraint(SpringLayout.WEST, submitButton, 62, SpringLayout.WEST, nameField);
		add(submitButton);
		
		/*------Date of birth Spinners------*/
		//month
		monthSpinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.EAST, monthSpinner, 9, SpringLayout.EAST, lblCountry);
		monthSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		springLayout.putConstraint(SpringLayout.NORTH, monthSpinner, 6, SpringLayout.SOUTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, monthSpinner, 47, SpringLayout.WEST, this);
		add(monthSpinner);
		
		//day
		daySpinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.NORTH, daySpinner, 6, SpringLayout.SOUTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, daySpinner, 0, SpringLayout.WEST, submitButton);
		springLayout.putConstraint(SpringLayout.EAST, daySpinner, -143, SpringLayout.EAST, this);
		daySpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		add(daySpinner);
		
		//year
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		SpinnerModel yearModel = new SpinnerNumberModel(currentYear, currentYear-120, currentYear, 1);
		
		yearSpinner = new JSpinner(yearModel);
		
		//Next two lines use an 'editor' to remove comma from year
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearSpinner, "#"); 
		yearSpinner.setEditor(editor);
		
		springLayout.putConstraint(SpringLayout.NORTH, yearSpinner, 6, SpringLayout.SOUTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, yearSpinner, 22, SpringLayout.EAST, daySpinner);
		springLayout.putConstraint(SpringLayout.EAST, yearSpinner, -47, SpringLayout.EAST, this);
		add(yearSpinner);
		
		
	}

	//Tell GUI program to change to next screen in SortingHat class
	public boolean submitClicked(){
            System.out.println("BUTTON CLICKED");
		return submitClicked;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
                    
                        
			name = nameField.getText(); //sets name variable as entered name
			country = countriesBox.getSelectedItem().toString(); //sets country to selected item
			
			//calculate age using calculateAge method
			age = calculateAge((Integer)yearSpinner.getValue(),
					(Integer)monthSpinner.getValue(),
					(Integer)daySpinner.getValue());
			
			StringBuilder sb = new StringBuilder();
			sb.append(name + ",");
			sb.append(age + ",");
			sb.append(country);
			System.out.println(sb.toString());
			buttonClicked= true; //this is inherited member variable from parent class
		}
	}
	
	public int calculateAge(int year, int month, int day){
		
		Calendar dob = Calendar.getInstance();  
		dob.set(year, month, day);;  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}
	
	public String[] countriesList() throws FileNotFoundException{
		Scanner scr= new Scanner(new File("Countries.txt"));
		
		//count countries for array size build
		int count = 0;
		while(scr.hasNextLine()){
			scr.nextLine();
			count++;
		}
		scr.close();
		String[] countries = new String[count];
		
		scr= new Scanner(new File("Countries.txt"));
		count = 0;

		while(scr.hasNext()){
			countries[count] = scr.nextLine();
			count++;
		}
		scr.close();
		return countries;
	}
        public int getAge(){
                return age;
        }
        public String getCountry(){
                return country;
        }
        public String getName(){
                return name;
        }
}	
		

