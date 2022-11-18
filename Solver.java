public class solve {
    private String[][] answerData;
    //question data

    public void setAnswerData(String[][] answerData) {
        this.answerData = answerData;
    }

    public String[][] getAnswerData() {
        return answerData;
    }

    public void convertEquationToMatrix(double[][] matrix, String[] equations, String vars) {

        for (int i = 0; i < equations.length; i++) {
            matrix[i] = equationToArray(equations[i], vars); // stores formated equation into row
        }
    }

    private double[] equationToArray(String equation, String vars) {
        double[] equationArr = new double[vars.length() + 1];

        String[] equalSeperated = equation.split("=");
        String leftSide = equalSeperated[0];
        String rightSide = equalSeperated[1];

        // for the left side of the equation
        for (String plusSeperated : leftSide.split("\\+")) {
            int varIndex;
            String[] terms = plusSeperated.split("(?=\\-)"); // keeps the minus sign
            for (String term : terms) {
                if (term.equals("")) { // dont do anything if the term doesn't exist
                    continue;
                }
                varIndex = vars.indexOf(term.charAt(term.length() - 1)); // find index of variable in string vars
                if (varIndex == -1) { // indicates it's a constant
                    equationArr[equationArr.length - 1] += Integer.parseInt(term) * -1;
                } else if (term.charAt(0) == '-') { // indicates its a negative
                    if (term.length() == 2)
                        equationArr[varIndex] += -1; // indicates it has no coefficeint (meaning its 1)
                    else
                        equationArr[varIndex] += Integer.parseInt(term.substring(0, term.length() - 1)); // add the
                                                                                                         // coefficient
                                                                                                         // to arr at
                                                                                                         // varIndex
                } else if (term.length() == 1) { // indicates var has a coefficient of one
                    equationArr[varIndex] += 1;
                } else {
                    equationArr[varIndex] += Integer.parseInt(term.substring(0, term.length() - 1)); // add the
                                                                                                     // coefficient to
                                                                                                     // arr (at
                                                                                                     // varIndex)
                }
            }
        }

        /*
         * everything is the same as the left side, but are multiple by negative again.
         * This is because we are moving terms from right to left (except for constants)
         */
        for (String plusSeperated : rightSide.split("\\+")) {
            int varIndex;
            String[] terms = plusSeperated.split("(?=\\-)");
            for (String term : terms) {
                if (term.equals("")) { // skip if empty
                    continue;
                }
                varIndex = vars.indexOf(term.charAt(term.length() - 1)); // find index of variable in string of
                                                                         // variables
                if (varIndex == -1) {
                    equationArr[equationArr.length - 1] += Integer.parseInt(term); // stores constant at end
                } else if (term.charAt(0) == '-') {
                    if (term.length() == 2)
                        equationArr[varIndex] += 1; // no coefficent, therefore 1 (it's already negative)
                    else
                        equationArr[varIndex] += Integer.parseInt(term.substring(0, term.length() - 1)) * -1; // gets
                                                                                                              // int
                                                                                                              // value
                                                                                                              // of term
                } else if (term.length() == 1) {
                    equationArr[varIndex] += -1; // no coefficient, therefore -1
                } else {
                    equationArr[varIndex] += Integer.parseInt(term.substring(0, term.length() - 1)) * -1; // if it's
                                                                                                          // positive
                }
            }
        }
        return equationArr;
    }
    // private solveMatrix()

    public double[] solveMatrix(double[][] matrix) {

        int width = matrix.length;
        double[] answer = new double[width]; // contains the variable answers

        // Guassian Elimination: algorithm for solving linear equations

        // forward elimination
        for (int i = 0; i < width - 1; i++) {
            for (int j = i + 1; j < width; j++) {
                double ratio = matrix[j][i] / matrix[i][i];
                for (int x = i; x < width + 1; x++) {
                    matrix[j][x] = matrix[j][x] - ratio * matrix[i][x];
                }
            }
        }
        // backward substitution
        answer[width - 1] = matrix[width - 1][width] / matrix[width - 1][width - 1];
        for (int i = width - 2; i > -1; i--) {
            double Sum = matrix[i][width];
            for (int j = i + 1; j < width; j++) {
                Sum = Sum - matrix[i][j] * answer[j];
            }
            answer[i] = Sum / matrix[i][i];
        }

        // answer are the values of each variable
        return answer;
    }

     

}

// private getAnswers()
