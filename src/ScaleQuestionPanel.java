import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ScaleQuestionPanel extends QuestionPanel{
        private String house;
        private int value;

	public ScaleQuestionPanel(Question question){
		//set up panel structure and layout
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,500));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		//Create label that loads the next question...uses some HTML for formatting
		JLabel questionLabel = new JLabel("<html><center>" + question.getQuestion() + "</center></html>");
		springLayout.putConstraint(SpringLayout.NORTH, questionLabel, 38, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, questionLabel, 34, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, questionLabel, 188, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, questionLabel, 265, SpringLayout.WEST, this);
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		questionLabel.setForeground(Color.WHITE);
		add(questionLabel);
		
		//Create JSlider using snap and painted texts and a tool tip text
		JSlider slider = new JSlider();
		springLayout.putConstraint(SpringLayout.WEST, slider, 34, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, slider, -184, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, slider, -35, SpringLayout.EAST, this);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(5);
		slider.setMinimum(1);
		slider.setForeground(Color.WHITE);
		slider.setToolTipText("1= Not True    5=Very True");
		slider.setSnapToTicks(true);
		add(slider);
		
		//Submit button
		JButton submitButton = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.NORTH, submitButton, 48, SpringLayout.SOUTH, slider);
		springLayout.putConstraint(SpringLayout.WEST, submitButton, 94, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, submitButton, -95, SpringLayout.EAST, this);
		submitButton.addActionListener(new ButtonListener());
		add(submitButton);
		
		//Scale label
		JLabel scaleInstLabel = new JLabel("1= Not At All   5= Tremendously");
		scaleInstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scaleInstLabel.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, scaleInstLabel, 16, SpringLayout.SOUTH, questionLabel);
		springLayout.putConstraint(SpringLayout.WEST, scaleInstLabel, 34, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scaleInstLabel, 54, SpringLayout.SOUTH, questionLabel);
		springLayout.putConstraint(SpringLayout.EAST, scaleInstLabel, 0, SpringLayout.EAST, questionLabel);
		add(scaleInstLabel);
	}
        
        public Object[] getAnswer(){
            Object[] answer = new Object[2];
            answer[0] = house;
            answer[1] = value;
            return answer;
        }  
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//this is inherited member variable from inherited parent class
			buttonClicked = true;
		}
	}
}
