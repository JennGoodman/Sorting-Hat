import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.getInteger;
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
    
    private ArrayList<Object> questions;
    private int currentQuestion;
    private final String path;

    // Constructors //
    
    Quiz (String path) throws IOException {
        this.path = path;
        
        // Open File
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String foo;
            String[] bar;
            int rows = 0;
            Object[][] answers = new Object[0][0];

            while ((foo = reader.readLine()) != null) {
                bar = foo.split(",");

                switch (Integer.getInteger(bar[0])) {
                    case 1: break;              // Short Answer
                    case 2: break;              // Drop Down
                    case 3: rows = 2; break;    // True/False
                    case 4: rows = 4; break;    // Multiple Choice
                    case 5: rows = 5; break; }   // Scale
                
                if (rows > 0) {
                    int max = bar.length;
                    int i = 2;
                    answers =  new Object[rows][3];
                    for (int j = 0 ; j < rows ; j++)
                        for (int k = 0; k < 3 ; k++)
                            if (i < max) answers[j][k] = bar[i++];
                }
                questions.add(new Question(getInteger(bar[0]), bar[1], answers));
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
    
    public void saveShowResults() {
        // To do
    }
}