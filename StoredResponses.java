import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoredResponses extends StoredData {

    public StoredResponses(String file) {
        this.file = file;
        storeData();
    }

    @Override
    public void storeLine(String line) {
        if(line.equals("")) {
            return;
        }
        List<String> tempList = Arrays.asList(line.split(",",-1));
        data.add(new ArrayList<String>(tempList));
    }
    
}
