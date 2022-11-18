import java.io.File;
import java.util.Scanner;

public class Facade {
    private Solver solver;
    //private CSVAnswer;
    //private CSVScore;
    private StoredData answerData;
    private StoredData questionData;
    private StoredData responseData;
    private StoredData studentData;
    private String answerDir = "data/answer_data/";
    private String questionDir = "data/question_data/";
    private String studentDir = "data/student_data/";
    private String responseDir = "data/response_data/";
    
    public Facade(){
        
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
    
    //add the CSV stuff later
    public void setAnswerData() {
        String file = validateFile(answerDir);
        answerData = new StoredAnswers(file);
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
}
