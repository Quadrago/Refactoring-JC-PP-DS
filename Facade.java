import java.io.File;
import java.util.Scanner;

public class Facade {
    private Solver solver;
    private CompareData compare;
    
    private CSV csv;

    private StoredData answerData;
    private StoredData questionData;
    private StoredData responseData;
    private StoredData studentData;

    private String answerDir = "data/answer_data/";
    private String questionDir = "data/question_data/";
    private String studentDir = "data/student_data/";
    private String responseDir = "data/response_data/";
    
    public Facade(){
        
        compare = new CompareData();
        solver = new Solver();
        csv = new CSV();

    }
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
    
    public void outScores() {
        if(compare.getScores() != null) {
            csv.scoreToCSV(compare.getScores());
        }
    }
    public void outAnswers() {
        if(solver.getAnswerData() != null) {
            csv.ansToCSV(solver.getAnswerData(),solver.getVariablesForEquations());
        }
    }


    public void setAnswerData() {
        String file = validateFile(answerDir);
        answerData  = new StoredAnswers(file);
    }
    public void setQuestionData() {
        String file = validateFile(questionDir);
        questionData = new StoredQuestions(file);
    }
    public void setStudentData() {
        String file = validateFile(studentDir);
        studentData = new StoredStudentsInfo(file);
    }
    public void setResponseData() {
        String file = validateFile(responseDir);
        responseData = new StoredResponses(file);
    }
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
