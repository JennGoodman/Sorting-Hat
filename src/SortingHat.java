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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Set up main content panel (which will change depending on what panel is called)
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		//Set JFrame visible and add main panel
		ParticipantQuestionPanel participantPanel = new ParticipantQuestionPanel();
		add(participantPanel);
		setVisible(true);
		
		//Waiting for submit button to be clicked
		while(participantPanel.submitClicked()==false){
			//System.out.println("waiting...");
		}
		System.out.println("CLICKED!");

		/*--------------Begin Quiz Questions-------------*/
		
		Quiz quiz = new Quiz("questions.txt");
		Question question = quiz.getNextQuestion();
		//load question panel and call multiple choice panel
		setContentPane(new QuestionViewPanel(question));
		revalidate();
	}
	
	public static void main(String[] args) throws IOException{
		SortingHat sh = new SortingHat();
	}
}