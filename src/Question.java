/**
 * Question Class
 * 
 * Generic class to manage and store data about a question.
 * 
 * @author Jenn Goodman
 */
public class Question {
    
    // Fields //
    private int qType;
    private String selectedAnswer;
    private Integer value;
    private String question;
    private Object[][] answers;
    
    // Constructors //
    
    Question(int type, String question, Object[][] answers) {
        qType = type;
        this.question = question;
        this.answers = answers;
    }
    
    // Methods //
    
    public int getType () {
        return qType;
    }
    
    public String getQuestion () {
        return question;
    }
    
    public Object[][] getPossibleAnswers () {
        return answers;
    }
    
    public Object[] getSelectedAnswer () {
        Object[] temp = new Object[2];
        temp[0] = selectedAnswer;
        temp[1] = value;
        return temp;
    }
    
    public void setSelectedAnswer (String answer, int value) {
        selectedAnswer = answer;
        this.value = value;
    }
}
