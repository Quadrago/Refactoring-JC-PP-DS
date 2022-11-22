import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class StoredData {

    protected ArrayList<ArrayList<String>> data;
    protected String file;

    /**
     * store file and store data once called
     * @param file supered file
     */
    public StoredData(String file) {
         this.file = file;
         storeData();
    }

    /**
     * Reads through file line by line by line, cleaning and storing
     * data into data arrayList.
     * Uses overriden readLine method to clean/store lines
     */
    protected void storeData() {

        data = new ArrayList<ArrayList<String>>(); 
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(file));
            String contentLine;
            contentLine = br.readLine();
            
            //skip header file for student_data
            if(file.contains("student_data")) contentLine = br.readLine();

            while(contentLine != null) {
                //logic for each data set
                storeLine(contentLine);  
                contentLine = br.readLine(); //next line
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
    
    /**
     * Each subclass has their own implementation to store the line 
     * in their own way
     * @param line current content line of file
     */
    protected abstract void storeLine(String line);
    
    //get 2d Array List of respective subclass's data
    public ArrayList<ArrayList<String>> getData() {
        return data;
    }
    
    //get String file 
    public String getFile() {
        return file;
    }
}
