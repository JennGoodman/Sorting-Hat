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
        private boolean buttonClicked;
        TrueFalseQuestionPanel tfQuestionPanel;
        MultiChoicePanel mcQuestionPanel;
        ScaleQuestionPanel scQuestionPanel;
        MultiChoicePanel spQuestionPanel; //special tie breaker
	
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
		
		Quiz quiz = new Quiz("questions.txt");
		Question question = quiz.getNextQuestion();
                question.setSelectedAnswer(name, 0);
                question = quiz.getNextQuestion();
                question.setSelectedAnswer(country, 0);
                question = quiz.getNextQuestion();
                question.setSelectedAnswer(age, 0);
		
                question = quiz.getNextQuestion();
                
		while(question != null){
		 
                    int questionType = question.getType();
			
			//load question panel depending on type of question
			
		switch (questionType) {
                   
                    case 3: // True/False
				tfQuestionPanel = new TrueFalseQuestionPanel(question);
				setContentPane(tfQuestionPanel);          
	            break;
	        case 4: // Multiple Choice
	        	mcQuestionPanel = new MultiChoicePanel(question, false);
	        	setContentPane(mcQuestionPanel);
                        
	            break;
	        case 5: // Scale
	        	scQuestionPanel = new ScaleQuestionPanel(question);
	        	setContentPane(scQuestionPanel); 
	            break;
	        case 6: // Tiebreak multiple choice
	        	spQuestionPanel = new MultiChoicePanel(question, true);
	        	setContentPane(spQuestionPanel);  
	            break;
			}
			revalidate(); //sets screen to this next question panel
			
			//waits for button to be clicked
			cnt = 0;
			while(buttonClicked == false){
				switch(questionType){
                                    case 3:
                                        buttonClicked = tfQuestionPanel.buttonClicked();
                                        break;
                                    case 4:
                                        buttonClicked = mcQuestionPanel.buttonClicked();
                                        break;
                                    case 5:
                                        buttonClicked = scQuestionPanel.buttonClicked();
                                        break;
                                    case 6:
                                        buttonClicked = spQuestionPanel.buttonClicked();
                                        break;
                                }
			}
			buttonClicked = false; //resets buttonClicked boolean
                        
                        //saves answer depending on type of question
                        switch(questionType){
                                    case 3:
                                        question.setSelectedAnswer(tfQuestionPanel.getHouse(), tfQuestionPanel.getValue());
                                        break;
                                    case 4:
                                        question.setSelectedAnswer(mcQuestionPanel.getHouse(), mcQuestionPanel.getValue());
                                        break;
                                    case 5:
                                        question.setSelectedAnswer(scQuestionPanel.getHouse(), scQuestionPanel.getValue());
                                        break;
                                    case 6:
                                       question.setSelectedAnswer(spQuestionPanel.getHouse(), spQuestionPanel.getValue());
                                        break;
                                }
                        
			//checks for next question
			question = quiz.getNextQuestion();
		}
		 String result = quiz.saveShowResults();
                        
                System.out.println(result);
                          
                ImageIcon icon = new ImageIcon("hat_icon.png",
                                 "a hat");
                          
                setVisible(false);
                JOptionPane optionPane = new JOptionPane();
                optionPane.showMessageDialog(this,
                 "You've been sorted into " + quiz.getWinner(), "You've been sorted!",
                  JOptionPane.PLAIN_MESSAGE, icon);
                System.exit(0);
	}
	
	public static void main(String[] args) throws IOException{
		SortingHat sh = new SortingHat();
	}
}