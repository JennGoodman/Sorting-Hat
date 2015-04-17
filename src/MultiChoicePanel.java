import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MultiChoicePanel extends QuestionPanel{

	public MultiChoicePanel(Question question, boolean specialQuestion){
		
		//set up panel structure and layout
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,500));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		//Create label that loads the next question...uses some HTML for formatting
		JLabel questionLabel = new JLabel("<html><center>" + question.getQuestion() + "</center></html>");
		springLayout.putConstraint(SpringLayout.NORTH, questionLabel, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, questionLabel, 195, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, questionLabel, 265, SpringLayout.WEST, this);
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		questionLabel.setForeground(Color.WHITE);
		add(questionLabel);
		
		/*-----------------------Buttons----------------------*/
		//A button
		JButton aButton = new JButton("A");
		springLayout.putConstraint(SpringLayout.WEST, questionLabel, 0, SpringLayout.WEST, aButton);
		springLayout.putConstraint(SpringLayout.WEST, aButton, 34, SpringLayout.WEST, this);
		aButton.addActionListener(new ButtonListener());
		add(aButton);
		
		//B button
		JButton bButton = new JButton("B");
		springLayout.putConstraint(SpringLayout.WEST, bButton, 34, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, bButton, -159, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, aButton, -15, SpringLayout.NORTH, bButton);
		bButton.addActionListener(new ButtonListener());
		add(bButton);
		
		//C button
		JButton cButton = new JButton("C");
		springLayout.putConstraint(SpringLayout.NORTH, cButton, 15, SpringLayout.SOUTH, bButton);
		springLayout.putConstraint(SpringLayout.WEST, cButton, 34, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, cButton, -115, SpringLayout.SOUTH, this);
		cButton.addActionListener(new ButtonListener());
		add(cButton);
		
		//D button
		JButton dButton = new JButton("D");
		springLayout.putConstraint(SpringLayout.NORTH, dButton, 14, SpringLayout.SOUTH, cButton);
		springLayout.putConstraint(SpringLayout.WEST, dButton, 34, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, dButton, -72, SpringLayout.SOUTH, this);
		dButton.addActionListener(new ButtonListener());
		add(dButton);
		
		//loads the possible answers from Question
		Object[][] answers = question.getPossibleAnswers();
		
		//Answer A label
		JLabel aLabel = new JLabel((String)answers[0][0]);
		springLayout.putConstraint(SpringLayout.WEST, aLabel, 104, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, aButton, -36, SpringLayout.WEST, aLabel);
		springLayout.putConstraint(SpringLayout.NORTH, aLabel, 0, SpringLayout.NORTH, aButton);
		springLayout.putConstraint(SpringLayout.SOUTH, aLabel, 0, SpringLayout.SOUTH, aButton);
		springLayout.putConstraint(SpringLayout.EAST, aLabel, -35, SpringLayout.EAST, this);
		aLabel.setForeground(Color.WHITE);
		add(aLabel);
		
		//Answer B label
		JLabel bLabel = new JLabel((String)answers[1][0]);
		springLayout.putConstraint(SpringLayout.WEST, bLabel, 104, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, bButton, -36, SpringLayout.WEST, bLabel);
		springLayout.putConstraint(SpringLayout.NORTH, bLabel, 0, SpringLayout.NORTH, bButton);
		springLayout.putConstraint(SpringLayout.SOUTH, bLabel, 0, SpringLayout.SOUTH, bButton);
		springLayout.putConstraint(SpringLayout.EAST, bLabel, -35, SpringLayout.EAST, this);
		bLabel.setForeground(Color.WHITE);
		add(bLabel);
		
		//Answer C label
		JLabel cLabel = new JLabel((String)answers[2][0]);
		springLayout.putConstraint(SpringLayout.WEST, cLabel, 104, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, cLabel, -35, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, cButton, -36, SpringLayout.WEST, cLabel);
		springLayout.putConstraint(SpringLayout.NORTH, cLabel, 0, SpringLayout.NORTH, cButton);
		springLayout.putConstraint(SpringLayout.SOUTH, cLabel, 0, SpringLayout.SOUTH, cButton);
		cLabel.setForeground(Color.WHITE);
		add(cLabel);
		
		//Answer D label
		JLabel dLabel = new JLabel((String)answers[3][0]);
		springLayout.putConstraint(SpringLayout.WEST, dLabel, 104, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, dLabel, -35, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, dButton, -36, SpringLayout.WEST, dLabel);
		springLayout.putConstraint(SpringLayout.NORTH, dLabel, 0, SpringLayout.NORTH, dButton);
		springLayout.putConstraint(SpringLayout.SOUTH, dLabel, 0, SpringLayout.SOUTH, dButton);
		dLabel.setForeground(Color.WHITE);
		add(dLabel);
		
		//If this is the final special question...determined by constructors boolean value
		if(specialQuestion == true){
			//adds special tiebreak label to screen
			JLabel specialQuestionLabel = new JLabel("The Sorting Hat will take this into consideration.");
			springLayout.putConstraint(SpringLayout.NORTH, aLabel, 17, SpringLayout.SOUTH, specialQuestionLabel);
			springLayout.putConstraint(SpringLayout.SOUTH, questionLabel, -22, SpringLayout.NORTH, specialQuestionLabel);
			springLayout.putConstraint(SpringLayout.WEST, specialQuestionLabel, 28, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, specialQuestionLabel, -17, SpringLayout.NORTH, aButton);
			springLayout.putConstraint(SpringLayout.EAST, specialQuestionLabel, -28, SpringLayout.EAST, this);
			specialQuestionLabel.setForeground(Color.WHITE);
			specialQuestionLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
			specialQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
			add(specialQuestionLabel);
		}
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//this is inherited member variable from inherited parent class
			buttonClicked = true;
		}
	}
}
