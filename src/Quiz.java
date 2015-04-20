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
    
    // Fields //
    
    private ArrayList<Question> questions = new ArrayList();
    private int currentQuestion = 0;
    private int g, h, r, s;

    // Constructors //
    
    Quiz (String path) throws IOException {
        g = h = r = s = 0;
        
        // Open File
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String foo;
            String[] bar;
            int rows = 0;
            Object[][] answers = new Object[0][0];

            while ((foo = reader.readLine()) != null) {
                
                bar = foo.split(",");
                int mType = 0;
                
                switch (bar[0]) {
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
                
                if (rows > 0) {
                    int max = bar.length;
                    int i = 2;
                    answers =  new Object[rows][3];
                    for (int j = 0 ; j < rows ; j++)
                        for (int k = 0; k < 3 ; k++)
                            if (i < max) answers[j][k] = bar[i++];
                }
                questions.add(new Question(mType, bar[1], answers));
            }
            reader.close();
        }
    }
    
    // Methods //
    
    public Question getNextQuestion() {
        if (questions.size() == currentQuestion) return null;
        return (Question)questions.get(currentQuestion++);
    }
    
    public void resetCurrentQuestion () {
        currentQuestion = 0;
    }
    
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
                    str[3] = mString;
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
        
        // Append the line to the file
        try (FileWriter out = new FileWriter("results.txt", true)) {
            String outString = str[0] + "," + str[1] + "," + str[2] + "," + g + "," + s + "," + r + "," + h;
            out.write(outString);
            out.flush();
            out.close();
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
        
        // Return winner string
        return (str[0] + " has been sorted into " + win);
    }
    
    private String winner() {
        if (g > r && g > h && g > s) return "Gryffindor!";
        else if (r > h && r > s && r > g) return "Ravenclaw!";
        else if (h > s && h > g && h > r) return "Hufflepuff!";
        else if (s > h && s > g && s > r) return "Slytherin!";
        return null;
    }
}