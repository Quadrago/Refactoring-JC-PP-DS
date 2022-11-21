import java.util.ArrayList;

public class CompareData{

    private ArrayList<ArrayList<String>> scoreData;
    
    /**
     * uses responses and answers storing an array with student info
     * paired with their scores
     * @param responseData students answers
     * @param studentData student data
     * @param answerData answers to each question
     */
    public void solToReponse(ArrayList<ArrayList<String>> responseData,
      ArrayList<ArrayList<String>> studentData, 
      ArrayList<ArrayList<String>> answerData) {

        scoreData = new ArrayList<ArrayList<String>>(studentData);
        int readOffset = studentData.get(0).size(); //index of responses 
        int score;

        //loop through each student
        for(int i = 0; i < studentData.size(); i++) {
            score = 0;

            //loop through each answer
            for(int x = 0; x < answerData.size(); x++) {
                //puts answerdata into correct format, then check if response is equal to data
                if(responseData.get(i).get(x+readOffset).equals(String.join(";", answerData.get(x)))) {
                    score++;
                }
            }
            scoreData.get(i).add(Integer.toString(score));
        }
  
    }
    /**
     * @return scoreData as 2d Arraylist
     */
    public ArrayList<ArrayList<String>> getScores() {
        return scoreData;
    }

}