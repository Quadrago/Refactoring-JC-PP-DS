import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class CSV {
    public void ansToCSV(ArrayList<ArrayList<Double>> answerData, ArrayList<String> varsForQuestions){
      try {
        FileWriter answers = new FileWriter("answers.csv");
        int q = 0;
        for (ArrayList<Double> row: answerData) {
          answers.write("A"+ (q+1) + "\n"); // heading for answer
          String line ="";
  
          if(Double.isInfinite(row.get(0))){
  
            answers.write("n/a\n\n"); // if there are no answers
            q++;
            continue;
          }
          String vars = varsForQuestions.get(q);
  
          line += vars.charAt(0) + "=" + row.get(0).intValue(); //to avoid comma at beginning
          for(int i = 1; i < row.size();i++) {
            line+= "," + vars.charAt(i) + "=" + row.get(i).intValue();
          }
          q++;
          answers.write(line+"\n\n");
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
