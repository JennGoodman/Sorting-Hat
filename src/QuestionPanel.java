import javax.swing.JPanel;

public abstract class QuestionPanel extends JPanel{
	protected String house;
        protected int value;
        protected boolean buttonClicked;
              
        public String getHouse(){
            return house;
        }
        public int getValue(){
            return value;
        }
}
