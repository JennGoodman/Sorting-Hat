import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Quiz Class
 * 
 * Generic class for containing and managing a quiz and it's questions.
 * 
 * @author Jenn Goodman
 */
public class Quiz {

    private ArrayList<Question> questions = new ArrayList();
    private int currentQuestion = 0;
    private int g, h, r, s;
    
    /**
     * Constructor
     * 
     * @param path to file containing questions to be loaded
     * @throws IOException if file is not found
     */
    Quiz (String path) throws IOException {
        g = h = r = s = 0;
        
        // Open File
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            String[] elements;
            int rows = 0;
            Object[][] answers = new Object[0][0];

            // Read File
            while ((line = reader.readLine()) != null) {
                
                elements = line.split(",");
                int mType = 0;
                
                switch (elements[0]) {
                    case "1": // Short Answer
                        mType = 1;
                        break;
                    case "2": // Drop Down
                        mType = 2;
                        break;
                    case "3": // True/False
                        mType = 3; rows = 2;
                        break;
                    case "4": // Multiple Choice
                        mType = 4;
                        rows = 4;
                        break;
                    case "5": // Scale
                        mType = 5; 
                        rows = 5; 
                        break;
                    case "6": // Multiple Choice
                        mType = 6;
                        rows = 4;
                        break;
                }
                // Build answer if needed
                if (rows > 0) {
                    int max = elements.length;
                    int i = 2;
                    answers =  new Object[rows][3];
                    for (int j = 0 ; j < rows ; j++)
                        for (int k = 0; k < 3 ; k++)
                            if (i < max) answers[j][k] = elements[i++];
                }
                // Create Object
                questions.add(new Question(mType, elements[1], answers));
            }
            // Close File
            reader.close();
        }
    }
    
    /**
     * Returns a question object reference to the currently indexed question.
     * @return Question Object in current position
     */
    public Question getNextQuestion() {
        if (questions.size() == currentQuestion) return null;
        return (Question)questions.get(currentQuestion++);
    }
    
    /**
     * Resets the index of the question list to 0.
     */
    public void resetCurrentQuestion () {
        currentQuestion = 0;
    }
    
    /**
     * Saves and returns the string of the house that the participant has been
     * sorted into.
     * 
     * @return String of winning house
     * @throws IOException 
     */
    public String saveShowResults() throws IOException {
        String[] str = new String[3];
        Question q;
        String special = "";
        String mString;
        Object[] answer;
        
        resetCurrentQuestion();
        
        // Get all the question values
        while ((q = getNextQuestion()) != null) {
            answer = q.getSelectedAnswer();
            mString = (String)answer[0];

            switch (q.getType()) {
                case 1: // Short Answer
                    if (q.getQuestion().equalsIgnoreCase("age")) str[1] = mString; 
                    else str[0] = mString;
                    break;
                case 2: // Drop Down
                    str[2] = mString;
                    break;
                case 3: case 4: case 5: // Valued Questions
                    switch (mString) {
                        case "g": g += (int)answer[1];
                            break;
                        case "h": h += (int)answer[1];
                            break;
                        case "r": r += (int)answer[1];
                            break;
                        case "s": s += (int)answer[1];
                            break;
                    }
                    break;
                case 6:
                    special = (String)answer[0];
                    break;
            }
        }
        
        // Calculate Winner!
        String win;
        while ((win = winner()) == null) {
            switch (special) {
                case "g": g++; break;
                case "h": h++; break;
                case "r": r++; break;
                case "s": s++; break;
            }
        }
        
        // Append the line to the file
        try (FileWriter out = new FileWriter("results.txt", true)) {
            String outString = str[0] + "," + str[1] + "," + str[2] + "," + g + "," + s + "," + r + "," + h + "\n";
            out.write(outString);
            out.flush();
            out.close();
        }
        
        // Return winner string
        return win;
    }
    
    /**
     * Calculates the house with the highest total and returns a string of the
     * house name.
     * 
     * @return String of the highest house or null if a tie is found
     */
    private String winner() {
        if (g > r && g > h && g > s){
            return "Gryffindor!";
        }
        else if (r > h && r > s && r > g){
            return "Ravenclaw!";
        }
        else if (h > s && h > g && h > r){
            return "Hufflepuff!";
        }
        else if (s > h && s > g && s > r){
            return "Slytherin!";
        }
        return null;
    }
}