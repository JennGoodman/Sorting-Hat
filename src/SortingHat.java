import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 * SortingHat Class
 * 
 * Main class for starting Quiz and Administrator Report.
 * Manages the various JPanel screens used in Swing GUI.
 * 
 * @author Charlie Swing
 */
public class SortingHat extends JFrame{
	//Fields
        private String name;
	private String country;
	private String age;
        private boolean buttonClicked;
        private boolean quizStart; //determines if "Start Quiz" or "Admin Options" button clicked
        private boolean adminStart;
        
        JPanel mainPanel;
        JPanel adminPanel;
        TrueFalseQuestionPanel tfQuestionPanel;
        MultiChoicePanel mcQuestionPanel;
        ScaleQuestionPanel scQuestionPanel;
        MultiChoicePanel spQuestionPanel; //special tie breaker panel
        
        /**
         * SortingHat constructor
         * @throws IOException 
         */
        public SortingHat() throws IOException{
            //Setup JFrame
            setName("Sorting Hat");
            setTitle("Sorting Hat");
            JMenuBar theMenuBar = new JMenuBar();
            setJMenuBar(theMenuBar);
            setPreferredSize(new Dimension(300, 500));
            setSize(new Dimension(300,500));
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            //uses mainPanel() method to build the main intro screen
            JPanel mainPanel = mainPanel();
            setContentPane(mainPanel);
            setVisible(true);

            //wait for either "Start Quiz" or "Admin" button to be clicked
            int cnt = 0;
            while(quizStart==false && adminStart==false){
                    if(cnt<1){
                            cnt++;      
                    }
                    System.out.print("");
            }

            //Admin button clicked -- switches to Admin dialog box then program exits
            if(adminStart==true){
                adminDialog();
                setContentPane(adminPanel);
                setVisible(true);
            }

            //Otherwise, set JFrame visible and add main quiz panel to Participant panel
            ParticipantQuestionPanel participantPanel = new ParticipantQuestionPanel();
            setContentPane(participantPanel);
            setVisible(true);

            //Waiting for submit button to be clicked
            cnt=0;
            while(participantPanel.buttonClicked==false){
                    if(cnt<1){
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
            
            //loads hat image for results dialog box
            ImageIcon icon = new ImageIcon("hat_icon.png",
                             "a hat");
            
            //Set up and display dialog box
            setVisible(false);
            JOptionPane optionPane = new JOptionPane();
            optionPane.showMessageDialog(this,
             "You've been sorted into " + quiz.saveShowResults(), "You've been sorted!",
              JOptionPane.PLAIN_MESSAGE, icon);
            System.exit(0);
	}
        
        /**
         * Creates and returns the main ("intro") JPanel screen
         * @return JPanel 
         */
        public JPanel mainPanel(){
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.BLACK);
            mainPanel.setPreferredSize(new Dimension(300,480));
            SpringLayout springLayout = new SpringLayout();
            mainPanel. setLayout(springLayout);

            JLabel lblSortingHat = new JLabel("Sorting Hat");
            lblSortingHat.setFont(new Font("Arial Black", Font.PLAIN, 16));
            lblSortingHat.setForeground(Color.WHITE);
            mainPanel.add(lblSortingHat);

            JButton quizBtn = new JButton("Start Quiz");
            quizBtn.addActionListener(new ButtonListener());
            springLayout.putConstraint(SpringLayout.NORTH, quizBtn, 351, SpringLayout.NORTH, this);
            springLayout.putConstraint(SpringLayout.WEST, quizBtn, 95, SpringLayout.WEST, this);
            springLayout.putConstraint(SpringLayout.WEST, lblSortingHat, 0, SpringLayout.WEST, quizBtn);
            springLayout.putConstraint(SpringLayout.SOUTH, lblSortingHat, -105, SpringLayout.NORTH, quizBtn);
            mainPanel.add(quizBtn);

            JButton adminBtn = new JButton("Admin Report");
            adminBtn.addActionListener(new ButtonListener());
            springLayout.putConstraint(SpringLayout.NORTH, adminBtn, 28, SpringLayout.SOUTH, quizBtn);
            springLayout.putConstraint(SpringLayout.WEST, adminBtn, 83, SpringLayout.WEST, this);
            mainPanel.add(adminBtn);

            BufferedImage myPicture;
            try {
                    myPicture = ImageIO.read(new File("hat_icon.png"));
            } catch (IOException e) {
                    myPicture = null;
                    e.printStackTrace();
            }

            JLabel label = new JLabel(new ImageIcon(myPicture));
            springLayout.putConstraint(SpringLayout.NORTH, label, 80, SpringLayout.NORTH, this);
            springLayout.putConstraint(SpringLayout.WEST, label, 82, SpringLayout.WEST, this);
            mainPanel.add(label);
            
            return mainPanel;
        }
        
        /**
         * Creates and displays Admin Report dialog box
         * @throws IOException 
         */
        public void adminDialog() throws IOException{
            Administrator admin = new Administrator("results.txt");
            setVisible(false);
            JOptionPane optionPane = new JOptionPane();
            javax.swing.UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Courier New", Font.PLAIN, 11))); 
            optionPane.showMessageDialog(this,
             admin.getResults(), "TEST RESULTS",
              JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }     
        
        /**
         * Button Listener class that responds to button clicks
         */
        private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
                    switch(e.getActionCommand()){
                        case "Start Quiz":
                            quizStart = true;
                            break;
                        case "Admin Report":
                            adminStart = true;
                            break; 
                        case "Exit":
                            System.exit(0);
                    }
		}
	}
	
	public static void main(String[] args) throws IOException{
		SortingHat sh = new SortingHat();
	}
}
