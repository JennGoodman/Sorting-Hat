import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
 
public class SortingHat extends JFrame{
	private String name;
	private String country;
	private String age;
	
	public SortingHat() throws IOException{
		//Setup JFrame
		setName("Sorting Hat");
		JMenuBar theMenuBar = new JMenuBar();
		setJMenuBar(theMenuBar);
		setPreferredSize(new Dimension(300, 500));
		setSize(new Dimension(300,500));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Set up main content panel (which will change depending on what panel is called)
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		//Set JFrame visible and add main panel
		ParticipantQuestionPanel participantPanel = new ParticipantQuestionPanel();
		add(participantPanel);
		setVisible(true);
		
		//Waiting for submit button to be clicked
		int cnt=0;
                
		while(participantPanel.buttonClicked==false){
			if(cnt<1){
				System.out.println(".");
				cnt++;
                                
			}
                        System.out.print("");
		}
                
                
                age = Integer.toString(participantPanel.getAge());
                name = participantPanel.getName();
                country = participantPanel.getCountry();

		/*--------------Begin Quiz Questions-------------*/
		System.out.println("TEST");
		Quiz quiz = new Quiz("questions.txt");
		Question question = quiz.getNextQuestion();
                question.setSelectedAnswer(name, 0);
                question = quiz.getNextQuestion();
                question.setSelectedAnswer(country, 0);
                question = quiz.getNextQuestion();
                question.setSelectedAnswer(age, 0);
		
                question = quiz.getNextQuestion();
                QuestionPanel questionPanel = null;
		//while there are more questions
                
                
                
		while(question != null){
			
                    System.out.println("WHILE LOOP TEST");
                    int questionType = question.getType();
			
			//load question panel depending on type of question
			
			
		switch (questionType) {
                   
                    case 3: // True/False
				questionPanel = new TrueFalseQuestionPanel(question);
				setContentPane(questionPanel);
	            break;
	        case 4: // Multiple Choice
	        	questionPanel = new MultiChoicePanel(question, false);
	        	setContentPane(questionPanel);
	            break;
	        case 5: // Scale
	        	questionPanel = new ScaleQuestionPanel(question);
	        	setContentPane(questionPanel); 
	            break;
	        case 6: // Tiebreak multiple choice
	        	questionPanel = new MultiChoicePanel(question, true);
	        	setContentPane(questionPanel);
                        System.out.println("OUT?");
	            break;
			}
			revalidate(); //sets screen to this next question panel
			
			//waits for button to be clicked
			cnt = 0;
			while(questionPanel.buttonClicked == false){
				//if(cnt<1){
					System.out.print("");
					//t++;
				//}
			}
			
			//checks for next question
			question = quiz.getNextQuestion();
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		SortingHat sh = new SortingHat();
	}
}