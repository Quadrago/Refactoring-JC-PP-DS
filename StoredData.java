import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class StoredData {

    protected ArrayList<ArrayList<String>> data;
    protected String file;

    protected void storeData() {
        data = new ArrayList<ArrayList<String>>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String contentLine;
            contentLine = br.readLine();
            while(contentLine != null) {

                //logic for each data set
                storeLine(contentLine);  
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null)
                br.close();
            } catch (IOException ioe) {
            System.out.println("Error in closing the BufferedReader");
            }
        }
        
    }
    
    public abstract void storeLine(String line);
    
    public ArrayList<ArrayList<String>> getData() {
        return data;
    }
    
    public String getFile() {
        return file;
    }
}
