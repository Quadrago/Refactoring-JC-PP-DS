import java.util.ArrayList;

public class CompareData{

    private ArrayList<ArrayList<String>> scoreData;

    public CompareData() { 
        System.out.println("test");
    }
    public  void solToReponse(ArrayList<ArrayList<String>> responseData,  ArrayList<ArrayList<String>> studentData, ArrayList<ArrayList<String>> answerData) {
        scoreData = new ArrayList<ArrayList<String>>(studentData);
        System.out.println(scoreData);

        
    }

}