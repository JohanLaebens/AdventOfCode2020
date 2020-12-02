package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day2();
    }


    Day2() {

        readfile("input");
        int numberOfvalidPasswords = 0;

        for (int i = 0; i<allLines.size();i++) {
            int min = 0;
            int max = 0;
            String requiredLetter = "";
            String password = "";

            String line = allLines.get(i);

            min = Integer.parseInt(line.substring(0,line.indexOf("-")));
            max = Integer.parseInt(line.substring(line.indexOf("-")+1,line.indexOf(" ")));

            requiredLetter = line.substring(line.indexOf(" ")+1,line.indexOf(":"));
            password = line.substring(line.indexOf(":")+2);

            System.out.println(min + " - " + max+ " - " +requiredLetter + " - " + password);

            int count = 0;
            // count the number of letters which equal the requiredLetter
            for (int j=0; j<password.length();j++)
            {
                if (password.substring(j,j+1).equals(requiredLetter)) {
                    count++;
                }
            }

            // is it between the requirements?
            if (count >= min && count <= max)
            {
                numberOfvalidPasswords++;
            }
            System.out.println("numberOfvalidPasswords= " + numberOfvalidPasswords);
        }
    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day2\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
