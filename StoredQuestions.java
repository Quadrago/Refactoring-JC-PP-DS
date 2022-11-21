import java.util.ArrayList;


public class StoredQuestions extends StoredData {

    private int numQuestions;

    /**
     * call super constructor and set current question count
     * @param file containing questions
     */
    public StoredQuestions(String file) {
        super(file); 
        numQuestions = 0;
    }
    
    @Override
    protected void storeLine(String line) {

        //remove any lines containg Question headers
        if(line.toUpperCase().contains("Q"+(numQuestions+1))) {
            data.add(new ArrayList<String>()); //new empty arraylist added (new line in array)
            numQuestions++;
            return;
        }

        //remove blank lines
        else if(line.equals("")) {
            return;
        }

        //removes spaces and sets line to lowercase
        data.get(numQuestions-1).add(line.toLowerCase().replace(" ", ""));
    }
    
}
