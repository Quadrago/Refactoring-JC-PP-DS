import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Facade work = new Facade();
        work.setAnswerData();

        Scanner scan = new Scanner(System.in);
        System.out.println("To skip to answers type 'yes' and 'no' to solve questions");
        String accessAnswers = scan.nextLine();
        if(accessAnswers.equals("no")) { 
            //to solve questions
        //question file entry
            System.out.println("Enter file name containing question data (including file extension): ");
            work.setQuestionData();
        }
        else if (accessAnswers.equals("yes")) {
            work.compareToAnswers();
        }
        scan.close();
        work.outScores();
        work.outAnswers();
    }
}