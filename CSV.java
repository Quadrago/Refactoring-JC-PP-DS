import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class CSV {
    public void ansToCSV(ArrayList<ArrayList<String>> scoreArr){
      try {
        FileWriter answers = new FileWriter("answers.csv");
        for (ArrayList<String> row: scoreArr) {
            String line = "";
            line = String.join(",", row);
            answers.write(line + "\n");
          }
          answers.close();
        }
          
        catch (IOException e) {
          System.out.println("Error has occured.");
          e.printStackTrace();
        }
    }
  public void scoreToCSV(ArrayList<ArrayList<String>> scoreArr){
    try {
      FileWriter marking = new FileWriter("grading.csv");
      for (ArrayList<String> row: scoreArr) {
          String line = "";
          line = String.join(",", row);
          marking.write(line + "\n");
        }
        marking.close();
      }
        
      catch (IOException e) {
        System.out.println("Error has occured.");
        e.printStackTrace();
      }
  }
}
