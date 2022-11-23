import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Facade work = new Facade();
        System.out.println("Enter file name containing student data (including file extension): ");
        work.setStudentData();

        Scanner scan = new Scanner(System.in);
        System.out.println("To skip to answers type 'yes' and 'no' to solve questions");
        String accessAnswers = scan.nextLine();
        if(accessAnswers.equals("no")) { 
            
            System.out.println("Enter file name containing question data (including file extension): ");
            work.setQuestionData();
            work.solveQuestions();
            work.outAnswers();
        }
        else if (accessAnswers.equals("yes")) {
            System.out.println("Enter file name containing answer data (including file extension): ");
            work.setAnswerData();
        }
        System.out.println("Enter file to solve containing response data (including file extension): ");
        work.setResponseData();
        work.compareToAnswers();
        work.outScores();
        
       
    }
}