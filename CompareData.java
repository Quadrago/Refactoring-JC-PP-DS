import java.util.ArrayList;

public class CompareData{

    private ArrayList<ArrayList<String>> scoreData;

    public CompareData() { 

    }
    public void solToReponse(ArrayList<ArrayList<String>> responseData,  ArrayList<ArrayList<String>> studentData, ArrayList<ArrayList<String>> answerData) {
        scoreData = new ArrayList<ArrayList<String>>(studentData);
        int readOffset = studentData.get(1).size();

        int score;
        for(int i = 0; i < studentData.size(); i++) {
            score = 0;

            for(int x = 0; x < answerData.size(); x++) {
                if(responseData.get(i).get(x+readOffset).equals(String.join(";", answerData.get(x)))) {
                    score++;
                }
            }
            scoreData.get(i).add(Integer.toString(score));
        }
        System.out.println(scoreData);

        
    }

}