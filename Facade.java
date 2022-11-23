import java.io.File;
import java.util.Scanner;
/**
 * 
 * This is the facade where all the varies different classes interact and operate through
 * by passing it into the facade
 * 
 */
public class Facade {
    //this declares variables
    private Solver solver;
    private CompareData compare;
    
    private CSV csv;

    private StoredData answerData;
    private StoredData questionData;
    private StoredData responseData;
    private StoredData studentData;
    
    //this sets the directory on where to get files
    private String answerDir = "data/answer_data/";
    private String questionDir = "data/question_data/";
    private String studentDir = "data/student_data/";
    private String responseDir = "data/response_data/";
    
 
 
    /**
     * 
     * This declares variable names used in the following methods of the facade. 
     * 
     */
    public Facade(){
        
        compare = new CompareData();
        solver = new Solver();
        csv = new CSV();

    }
    /**
    * validatFile is for validaing files to ensure that they don't crash the program and cause disruptions
    * @param dir
    * @return file for solving and or comparing
    */
    private String validateFile(String dir) {
        Scanner scan = new Scanner(System.in);
        String file = "";
        file = scan.nextLine();

        //while input is not empty and the file itself isn't empty
        while(file.equals("") || !new File(dir+file).exists()) { 
            System.out.print("No File Found. Enter File Name Again.\n");
            file = scan.nextLine();
        }

        String returnFile = dir + file;
        System.out.println(returnFile +": validated");
        return returnFile;
    }
    /**
     * This sends the compared scores the the csv object
     */
    public void outScores() {
        if(compare.getScores() != null) {
            csv.scoreToCSV(compare.getScores());
        }
    }
    /**
     * This sends the answers to the csv object
     */
    public void outAnswers() {
        if(solver.getAnswerData() != null) {
            csv.ansToCSV(solver.getAnswerData(),solver.getVariablesForEquations());
        }
    }

    /**
     * This is called by the main.
     * Then it validates files then sends it to the answerData
     */
    public void setAnswerData() {
        String file = validateFile(answerDir);
        answerData  = new StoredAnswers(file);
    }
    /**
     * This is called by the main.
     * Then it validates files then sends it to questionData 
     */
    public void setQuestionData() {
        String file = validateFile(questionDir);
        questionData = new StoredQuestions(file);
    }
    /**
     * This is called by the main.
     * Then it validates files then sends it to studentData
     */
    public void setStudentData() {
        String file = validateFile(studentDir);
        studentData = new StoredStudentsInfo(file);
    }
    /**
     * This is called by the main.
     * Then it validates files then sends it to responseData
     */
    public void setResponseData() {
        String file = validateFile(responseDir);
        responseData = new StoredResponses(file);
    }
    /**
     * Then it pases the question data in the solver to solve
     */
    public void solveQuestions() {
        solver.solveQuestion(questionData.getData());
    }


    /**
     * stores scores into CompareData class
     * compares the respones to answers
     */
    public void compareToAnswers() {

        //checks if there is response data to compare to
        if(responseData.getData() == null) {
            System.out.println("No response data");
            return;
        }

        //temp answer and student files for respective response file
        String ansFile;
        String studentFile;

        if(responseData.getFile().equals(responseDir+"student_data_q1_response.csv")) {
            ansFile = answerDir + "sample_a_1.txt";
            studentFile = studentDir + "student_data_1.csv";
        }
        else {
            int indexOfNum = responseDir.length()+13; //location of the file number in string
            int answerFileNum =  Integer.parseInt(responseData.getFile().substring(indexOfNum, responseData.getFile().indexOf('_',indexOfNum+1)));; //32 index indicates the file number
            
            ansFile = answerDir + "sample_a_" + answerFileNum + ".txt"; //creates file corresponding to response file
            studentFile = studentDir + "student_data_" + answerFileNum + ".csv";
        }
        //store tempoary answer & student data
        StoredData tempStudentData = new StoredStudentsInfo(studentFile);
        StoredData tempAnswerData = new StoredAnswers(ansFile);
        
        compare.solToReponse(responseData.getData(), tempStudentData.getData(), tempAnswerData.getData());
    }
}
