import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoredAnswers extends StoredData {

    private int numQuestions = 0;

    public StoredAnswers(String file) {
        this.file = file;
        storeData();   
    }

    @Override
    public void storeLine(String line) {
        if(line.toUpperCase().contains("A"+(numQuestions+1)) || line.equals("")) {
            numQuestions++;
            return;
        }
        List<String> tempList = Arrays.asList(line.split(",",-1));
        data.add(new ArrayList<String>(tempList));
    }
}
