import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

/**
 * ParticipantQuestionPanel class
 * @author Charlie Swing
 */
public class ParticipantQuestionPanel extends QuestionPanel {
	
        //fields
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

        /**
         * ParicipantQuestionPanel constructor 
         * @throws FileNotFoundException
         * @throws IOException 
         */
	public ParticipantQuestionPanel() throws FileNotFoundException, IOException {
		
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
		
                BufferedImage bi = ImageIO.read(new File("hat_icon.png"));
                
		try {
			bi = ImageIO.read(new File("hat_icon.png"));
		} catch (IOException e) {
			bi = null;
			e.printStackTrace();
		}
		
		JLabel picLabel = new JLabel(new ImageIcon(bi));
		springLayout.putConstraint(SpringLayout.SOUTH, picLabel, -104, SpringLayout.NORTH, nameField);
		springLayout.putConstraint(SpringLayout.EAST, picLabel, -90, SpringLayout.EAST, this);
		add(picLabel);
		
		JLabel lblSortingHat = new JLabel("Sorting Hat");
		springLayout.putConstraint(SpringLayout.SOUTH, lblSortingHat, -63, SpringLayout.NORTH, nameField);
		springLayout.putConstraint(SpringLayout.EAST, lblSortingHat, 0, SpringLayout.EAST, submitButton);
		lblSortingHat.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSortingHat.setForeground(Color.WHITE);
		add(lblSortingHat);
		
	}

	/**
         * Tell GUI program to change to next screen in SortingHat class
         * @return boolean
         */
	public boolean submitClicked(){
		return submitClicked;
	}
	
        /**
         * Button Listener class that responds to button clicks
         */
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
                    
                        
			name = nameField.getText(); //sets name variable as entered name
			country = countriesBox.getSelectedItem().toString(); //sets country to selected item
			
			//calculate age using calculateAge method
			age = calculateAge((Integer)yearSpinner.getValue(),
					(Integer)monthSpinner.getValue(),
					(Integer)daySpinner.getValue());

			buttonClicked= true; //this is inherited member variable from parent class
		}
	}
	/**
         * Calculates age based on given date of birth
         * @param year
         * @param month
         * @param day
         * @return int age
         */
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
	
        /**
         * Reads in countries.txt files to populate JSpinner menu box
         * @return
         * @throws FileNotFoundException 
         */
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
        
        /**
         * Returns age
         * @return int
         */
        public int getAge(){
                return age;
        }
        
        /**
         * Returns country
         * @return String
         */
        public String getCountry(){
                return country;
        }
        
        /**
         * Returns name
         * @return String
         */
        public String getName(){
                return name;
        }
}	
		

