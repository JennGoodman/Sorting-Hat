/**
 * Question Class
 * 
 * Generic class to manage and store data about a question.
 * 
 * @author Jenn Goodman
 */
public class Question {
    
    // Fields //
    private final int qType;
    private final String question;
    private final Object[][] answers;
    private String selectedAnswer;
    private Integer value;
    
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
        Object[] temp = {selectedAnswer, value};
        return temp;
    }
    
    public void setSelectedAnswer (String answer, int value) {
        this.selectedAnswer = answer;
        this.value = value;
    }
}
