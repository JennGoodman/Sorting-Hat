import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TrueFalseQuestionPanel extends QuestionPanel{

	public TrueFalseQuestionPanel(Question question){
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,500));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel questionLabel = new JLabel("<html><center>" + question.getQuestion() + "</center></html>");
		springLayout.putConstraint(SpringLayout.NORTH, questionLabel, 38, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, questionLabel, 34, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, questionLabel, -312, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, questionLabel, 265, SpringLayout.WEST, this);
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		questionLabel.setForeground(Color.WHITE);
		add(questionLabel);
		
		JButton trueButton = new JButton("TRUE");
		springLayout.putConstraint(SpringLayout.NORTH, trueButton, 33, SpringLayout.SOUTH, questionLabel);
		springLayout.putConstraint(SpringLayout.WEST, trueButton, 75, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, trueButton, -188, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, trueButton, -76, SpringLayout.EAST, this);
		trueButton.addActionListener(new ButtonListener());
		trueButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(trueButton);
		
		JButton falseButton = new JButton("FALSE");
		springLayout.putConstraint(SpringLayout.NORTH, falseButton, 25, SpringLayout.SOUTH, trueButton);
		springLayout.putConstraint(SpringLayout.WEST, falseButton, 76, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, falseButton, -72, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, falseButton, -77, SpringLayout.EAST, this);
		falseButton.addActionListener(new ButtonListener());
		falseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(falseButton);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//this is inherited member variable from inherited parent class
			System.out.println("Clicked!");
			buttonClicked = true;
		}
	}
}
