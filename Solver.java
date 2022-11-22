import java.util.ArrayList;
import java.util.Arrays;

public class Solver {

    // Attributes (Variable Members)
    private ArrayList<ArrayList<Double>> answerData;
    private ArrayList<ArrayList<String>> questionData;
    private int numOfEquations; // number of questions
    private ArrayList<String> varsForQuestions; // Variables of question

    /**
     * public Accessor method responsible for returning the answer data (solved
     * Question)
     * 
     * @return 2d double ArrayList containing answer Data
     */
    public ArrayList<ArrayList<Double>> getAnswerData() {
        return answerData;
    }

    /**
     * public Mutator Method responsible for setting question data, numOfEquations,
     * and also solving questionData
     * 
     * @param questionData 2d String Arraylist containing questions
     */
    public void solveQuestion(ArrayList<ArrayList<String>> questionData) {
        this.questionData = questionData;
        this.numOfEquations = questionData.size();

        answerData = formatEquations(numOfEquations, questionData);
    }

    /**
     * public accessor method responsible for returning VariablesForEquations
     * 
     * @return 1d String ArrayList containing varibales for every equation
     */
    public ArrayList<String> getVariablesForEquations() {
        return varsForQuestions;
    }

    /**
     * used to call equationToArray with a for loop and storing thosee answers in a
     * matrix
     * 
     * @param matrix    2d Double ArrayList to store coefficeints and constants in
     * @param equations equations for one question stored in a 1d ArrayList
     * @param vars      variables that the equations uses
     */
    private void convertEquationToMatrix(ArrayList<ArrayList<Double>> matrix, ArrayList<String> equations,
            String vars) {

        for (int i = 0; i < equations.size(); i++) {
            matrix.add(equationToArray(equations.get(i), vars)); // adds a 1d doble ArrayList to the matrix
        }
    }

    /**
     * private helpor method to solve a given quesion using the gaussian elimination
     * (Guassian Elimination: algorithm for solving linear equations)
     * 
     * @param matrix 2d double ArrayList containing variables, coeffeicents and
     *               constant
     * @return 1d double arrayList containing varibale answers
     */
    private ArrayList<Double> solveMatrix(ArrayList<ArrayList<Double>> matrix) {

        int width = matrix.size();
        ArrayList<Double> answer = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            answer.add(0.0);
        }

        // Guassian Elimination: algorithm for solving linear equations

        // forward elimination
        for (int i = 0; i < width - 1; i++) {
            for (int j = i + 1; j < width; j++) {
                double ratio = matrix.get(j).get(i) / matrix.get(i).get(i);
                for (int x = i; x < width + 1; x++) {
                    matrix.get(j).set(x, matrix.get(j).get(x) - ratio * matrix.get(i).get(x));
                }
            }
        }
        // backward substitution
        answer.set(width - 1, matrix.get(width - 1).get(width) / matrix.get(width - 1).get(width - 1));
        for (int i = width - 2; i > -1; i--) {

            double Sum = matrix.get(i).get(width);
            for (int j = i + 1; j < width; j++) {
                Sum = Sum - matrix.get(i).get(j) * answer.get(j);
            }
            answer.set(i, Sum / matrix.get(i).get(i));
        }

        // answer are the values of each variable
        return answer;
    }

    /**
     * splits equation into coefficients and constants by splitting it at '-','=','+'
     * 
     * @param equation the string equation
     * @param vars     variables that the equation uses
     * @return return 1d double arrayList of coefficients and constants
     */
    private ArrayList<Double> equationToArray(String equation, String vars) {

        ArrayList<Double> equationArr = new ArrayList<>();
        for (int i = 0; i < vars.length() + 1; i++) {
            equationArr.add(0.0);     // set defualt values to add on to
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
                    equationArr.set(equationArr.size() - 1,
                            equationArr.get(equationArr.size() - 1) + Integer.parseInt(term) * -1);
                } else if (term.charAt(0) == '-') { // indicates its a negative
                    if (term.length() == 2)
                        equationArr.set(varIndex, equationArr.get(varIndex) + -1);
                    else
                        equationArr.set(varIndex,
                                equationArr.get(varIndex) + Integer.parseInt(term.substring(0, term.length() - 1)));

                } else if (term.length() == 1) { // indicates var has a coefficient of one
                    equationArr.set(varIndex, equationArr.get(varIndex) + 1);

                } else {
                    equationArr.set(varIndex,
                            equationArr.get(varIndex) + Integer.parseInt(term.substring(0, term.length() - 1)));
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
                    equationArr.set(equationArr.size() - 1,
                            equationArr.get(equationArr.size() - 1) + Integer.parseInt(term));
                } else if (term.charAt(0) == '-') {
                    if (term.length() == 2)
                        equationArr.set(varIndex, equationArr.get(varIndex) + 1); // no coefficent, therefore 1 (it's
                                                                                  // already negative)
                    else
                        equationArr.set(varIndex, equationArr.get(varIndex)
                                + Integer.parseInt(term.substring(0, term.length() - 1)) * -1); // no coefficent,
                                                                                                // therefore 1 (it's
                                                                                                // already negative)

                } else if (term.length() == 1) {
                    equationArr.set(varIndex, equationArr.get(varIndex) + -1);
                } else {
                    equationArr.set(varIndex,
                            equationArr.get(varIndex) + Integer.parseInt(term.substring(0, term.length() - 1)) * -1);

                }
            }
        }
        return equationArr;
    }

    /**
     * gets the variables from every equations in a question and converts the equation into a matrix which later gets solved and added to solvedQuestions
     * @param numOfEquations   number of equations in a questions
     * @param answerData     solved question data as a 2d String ArrayList
     * @return answers (solvedQuestions) as a 2d double ArrayList
     */
    private ArrayList<ArrayList<Double>> formatEquations(int numOfEquations, ArrayList<ArrayList<String>> answerData) {
        ArrayList<ArrayList<Double>> solvedQuestions = new ArrayList<>();
        ArrayList<String> varsForQuestions = new ArrayList<>();

        for (int n = 0; n < numOfEquations; n++) {

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
        this.varsForQuestions = varsForQuestions;  // Assigns the variable member, varsForQuestions with a value
        return solvedQuestions;
    }

}
