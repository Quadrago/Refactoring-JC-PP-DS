import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoredAnswers extends StoredData {

    private int numQuestions;

    /**
     * call super constructor and set current question count
     * @param file containing answers
     */
    public StoredAnswers(String file) {
        super(file); 
        numQuestions = 0; 
    }
    @Override
    protected void storeLine(String line) {
        
        //remove any lines containg Question headers
        if(line.toUpperCase().contains("A"+(numQuestions+1))) {
            numQuestions++;
            return;
        }

        //remove any blank lines
        else if(line.equals("")) {
            return;
        }
        
        //Splits line into array then converts into List
        List<String> tempList = Arrays.asList(line.split(";",-1));
        data.add(new ArrayList<String>(tempList)); //add new ArrayList of tempList
    }
}
