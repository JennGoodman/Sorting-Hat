import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class QuestionViewPanel extends JPanel{
	
	public QuestionViewPanel(Question question){
		
		//Set up panel structure and layout
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,500));
		
		//Determine question type by calling getType method on 'question' object
		//then build rest of panel in that particular question type's method
		if(question.getType()==4){
			multChoicePanel();
		}
		else if(question.getType()==3){
			trueFalsePanel();
		}
		else if(question.getType()==5){
			scalePanel();
		}
	}
	
	public void multChoicePanel(){
		JLabel questionType = new JLabel("MultiChoice");
		questionType.setForeground(Color.WHITE);
		add(questionType);
		System.out.println("Multi");
	}
	
	public void trueFalsePanel(){
		JLabel questionType = new JLabel("T or F");
		questionType.setForeground(Color.WHITE);
		add(questionType);
		System.out.println("TF");
	}
	
	public void scalePanel(){
		JLabel questionType = new JLabel("Scale");
		questionType.setForeground(Color.WHITE);
		add(questionType);
		System.out.println("Scale");
	}
}
