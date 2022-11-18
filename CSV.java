public abstract class CSV {
    try {
        FileWriter marking = new FileWriter("grading.csv");
        for (String[] row: scoreArr) {
            String line = "";
            line = String.join(",", row);
            marking.write(line + "\n");
          }
          marking.close();
        }
          
        catch (IOException e) {
          System.out.println("Error has occured.");
          e.printStackTrace();
        }
        dfreader.close();
}
