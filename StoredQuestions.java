import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoredQuestions extends StoredData {

    private int numQuestions = 0;

    public StoredQuestions(String file) {
        this.file = file;
        storeData();
    }
    @Override
    public void storeLine(String line) {
        if(line.contains("Q"+(numQuestions+1))) {

            numQuestions++;
            return;
        }
        else if(line.equals("")) {
            return;
        }
        
    }
    
}
