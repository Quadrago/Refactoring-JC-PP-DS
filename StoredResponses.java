import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoredResponses extends StoredData {

    /**
     * call super constructor 
     * @param file containing responses
     */
    public StoredResponses(String file) {
        super(file); 
    }
    @Override
    protected void storeLine(String line) {

        //remove any blank lines
        if(line.equals("")) {
            return;
        }
        //Splits line into array then converts into List
        List<String> tempList = Arrays.asList(line.split(",",-1));
        data.add(new ArrayList<String>(tempList)); //add new ArrayList of tempList
    }
    
}
