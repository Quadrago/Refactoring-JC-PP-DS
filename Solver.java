import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
 
    private ArrayList<ArrayList<Double>> answerData;
    private ArrayList<ArrayList<String>> questionData;
    private int questionDataRows;
    private ArrayList<String> varsForQuestions;

    public ArrayList<ArrayList<Double>> getAnswerData() {
        return answerData;
    }
    
    public void solveQuestion(ArrayList<ArrayList<String>> questionData){
        this.questionData = questionData;
        this.questionDataRows = questionData.size();

        ArrayList<ArrayList<Double>> solvedQuestionData = formatEquations(questionDataRows, questionData);
        answerData = solvedQuestionData;
    }

    public ArrayList<String> getVariablesForEquations(){
        return varsForQuestions;
    }

    private void convertEquationToMatrix(ArrayList<ArrayList<Double>> matrix, ArrayList<String> equations, String vars) {

        for (int i = 0; i < equations.size(); i++) {
            matrix.add(equationToArray(equations.get(i), vars));
            //matrix[i] = equationToArray(equations[i], vars);
        }
    }

    private ArrayList<Double> solveMatrix(ArrayList<ArrayList<Double>> matrix) {

        int width = matrix.size();
        ArrayList<Double> answer = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            answer.add(0.0);
        }
        //double[] answer = new double[width]; // contains the variable answers

        // Guassian Elimination: algorithm for solving linear equations

        // forward elimination
        for (int i = 0; i < width - 1; i++) {
            for (int j = i + 1; j < width; j++) {
                //double ratio = matrix[j][i] / matrix[i][i];
                double ratio = matrix.get(j).get(i) / matrix.get(i).get(i);
                for (int x = i; x < width + 1; x++) {
                    matrix.get(j).set(x,matrix.get(j).get(x) - ratio * matrix.get(i).get(x));
                    //matrix[j][x] = matrix[j][x] - ratio * matrix[i][x];
                }
            }
        }
        // backward substitution
        answer.set(width - 1, matrix.get(width - 1).get(width) / matrix.get(width - 1).get(width - 1)); 
        for (int i = width - 2; i > -1; i--) {

            double Sum = matrix.get(i).get(width);
            for (int j = i + 1; j < width; j++) {
                Sum = Sum -matrix.get(i).get(j) * answer.get(j);
            }
            answer.set(i, Sum / matrix.get(i).get(i));
        }

        // answer are the values of each variable
        return answer;
    }

    private ArrayList<Double> equationToArray(String equation, String vars) {

        ArrayList<Double> equationArr = new ArrayList<>();
        for(int i = 0; i < vars.length()+1; i++) {
            equationArr.add(0.0);
        }

        ArrayList<String> equalSeperated = new ArrayList<>(Arrays.asList(equation.split("=")));
        String leftSide = equalSeperated.get(0);
        String rightSide = equalSeperated.get(1);

        // for the left side of the equation
        for (String plusSeperated : leftSide.split("\\+")) {
            int varIndex;
            ArrayList<String> terms = new ArrayList<>(Arrays.asList(plusSeperated.split("(?=\\-)")));
            for (String term : terms) {
                if (term.equals("")) { // dont do anything if the term doesn't exist
                    continue;
                }
                varIndex = vars.indexOf(term.charAt(term.length() - 1)); // find index of variable in string vars
                if (varIndex == -1) { // indicates it's a constant
                    equationArr.set(equationArr.size() - 1, equationArr.get(equationArr.size() - 1) + Integer.parseInt(term) * -1);
                } else if (term.charAt(0) == '-') { // indicates its a negative
                    if (term.length() == 2)
                        equationArr.set(varIndex, equationArr.get(varIndex) + -1);
                    else
                        equationArr.set(varIndex, equationArr.get(varIndex) + Integer.parseInt(term.substring(0, term.length() - 1)));
                          
                } else if (term.length() == 1) { // indicates var has a coefficient of one
                    equationArr.set(varIndex, equationArr.get(varIndex) + 1);
                    
                } else {
                    equationArr.set(varIndex, equationArr.get(varIndex) + Integer.parseInt(term.substring(0, term.length() - 1)));
                }
            }
        }

        /*
         * everything is the same as the left side, but are multiple by negative again.
         * This is because we are moving terms from right to left (except for constants)
         */
        for (String plusSeperated : rightSide.split("\\+")) {
            int varIndex;
            ArrayList<String> terms = new ArrayList<>(Arrays.asList(plusSeperated.split("(?=\\-)")));
            for (String term : terms) {
                if (term.equals("")) { // skip if empty
                    continue;
                }
                varIndex = vars.indexOf(term.charAt(term.length() - 1)); // find index of variable in string of
                                                                         // variables
                if (varIndex == -1) {
                    equationArr.set(equationArr.size() - 1, equationArr.get(equationArr.size() - 1) + Integer.parseInt(term));
                } else if (term.charAt(0) == '-') {
                    if (term.length() == 2)
                        equationArr.set(varIndex, equationArr.get(varIndex) + 1); // no coefficent, therefore 1 (it's already negative)
                    else
                        equationArr.set(varIndex, equationArr.get(varIndex) + Integer.parseInt(term.substring(0, term.length() - 1)) * -1); // no coefficent, therefore 1 (it's already negative)
                        
                } else if (term.length() == 1) {
                    equationArr.set(varIndex, equationArr.get(varIndex) + -1);
                } else {
                    equationArr.set(varIndex, equationArr.get(varIndex) + Integer.parseInt(term.substring(0, term.length() - 1)) * -1);

                }
            }
        }
        return equationArr;
    }

    private ArrayList<ArrayList<Double>> formatEquations(int questionDataRows, ArrayList<ArrayList<String>> answerData/*, ArrayList<ArrayList<Double>> solvedQuestions, ArrayList<ArrayList<String>> cleanedQuestions, ArrayList<String> varsForQuestons*/){
        ArrayList<ArrayList<Double>> solvedQuestions = new ArrayList<>();
        ArrayList<String> varsForQuestions = new ArrayList<>();
        // cleaned equation ========== answer data (right???????)

        for (int n = 0; n < questionDataRows; n++) {

            // 2d array for question: each row contains an equation

            ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
            String vars = "";

            for (String equation : answerData.get(n)) {
                for (char val : equation.toCharArray()) {

                    // adds letter if it doesn't exist in current varaibles string
                    if (Character.isLetter(val) && vars.indexOf(val) == -1) {
                        vars += val;
                    }
                }
            }

            varsForQuestions.add(vars); // variables used question

            convertEquationToMatrix(matrix, answerData.get(n), vars);

            solvedQuestions.add(solveMatrix(matrix)); // stores answers for each variable in row

        }
        this.varsForQuestions = varsForQuestions;
        return solvedQuestions;
    }

    
  
}

