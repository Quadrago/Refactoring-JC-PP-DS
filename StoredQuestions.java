import java.util.ArrayList;


public class StoredQuestions extends StoredData {

    private int numQuestions = 0;

    public StoredQuestions(String file) {
        this.file = file;
        storeData();
    }
    @Override
    public void storeLine(String line) {
        if(line.toUpperCase().contains("Q"+(numQuestions+1))) {
            data.add(new ArrayList<String>());
            numQuestions++;
            return;
        }
        else if(line.equals("")) {
            return;
        }
        data.get(numQuestions-1).add(line.toLowerCase().replace(" ", ""));
    }
    
}
