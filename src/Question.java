/**
 * Question Class
 * 
 * Generic class to manage and store data about a question.
 * 
 * @author Jenn Goodman
 */
public class Question {
    private final int qType;
    private final String question;
    private final Object[][] answers;
    private String selectedAnswer;
    private Integer value;
    
    /**
     * Constructor
     * 
     * @param type of object to be created
     * @param question to be asked
     * @param answers that are valid
     */
    Question(int type, String question, Object[][] answers) {
        qType = type;
        this.question = question;
        this.answers = answers;
    }
    
    /**
     * Gets the int type of the question object
     * 
     * @return type of question
     */
    public int getType () {
        return qType;
    }
    
    /**
     * Gets the question string
     * 
     * @return question string
     */
    public String getQuestion () {
        return question;
    }
    
    /**
     * Gets the array of possible answers
     * 
     * @return array of answers to the question with values if needed
     */
    public Object[][] getPossibleAnswers () {
        return answers;
    }
    
    /**
     * Gets the selected answer
     * 
     * @return the (house,value) pair in an Object array as (String,Integer)
     */
    public Object[] getSelectedAnswer () {
        Object[] temp = {selectedAnswer, value};
        return temp;
    }
    
    /**
     * Saves the selected answer in the object
     * 
     * @param answer is the house character associated with the value
     * @param value of the answer being saved
     */
    public void setSelectedAnswer (String answer, int value) {
        this.selectedAnswer = answer;
        this.value = value;
    }
}