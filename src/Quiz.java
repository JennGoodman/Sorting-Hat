import java.io.BufferedReader;
import java.io.FileReader;
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
    
    public void saveShowResults() {
        resetCurrentQuestion();
        Question q = getNextQuestion();
        
        switch (q.getType()) {
            case 1: // Short Answer
                Object[] a = q.getSelectedAnswer();
                try {
                    int age = Integer.parseInt(a[0].toString());
                }
                catch (NumberFormatException e) {
                    
                }
                break;
            case 2: // Drop Down
                break;
            case 3: // True/False
                break;
            case 4: // Multiple Choice
                break;
            case 5: // Scale
        }
        
    }
}