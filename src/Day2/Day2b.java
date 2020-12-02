package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2b {

    List<String> allLines;


    public static void main(String[] args) {
        new Day2b();
    }


    Day2b() {

        readfile("input");
        int numberOfvalidPasswords = 0;


        for (int i = 0; i<allLines.size();i++) {
            int position1 = 0;
            int position2 = 0;
            String requiredLetter = "";
            String password = "";

            String line = allLines.get(i);

            position1 = Integer.parseInt(line.substring(0,line.indexOf("-")));
            position2 = Integer.parseInt(line.substring(line.indexOf("-")+1,line.indexOf(" ")));

            requiredLetter = line.substring(line.indexOf(" ")+1,line.indexOf(":"));
            password = line.substring(line.indexOf(":")+2);

            System.out.println(position1 + " - " + position2+ " - " +requiredLetter + " - " + password);

            boolean isPos1Valid = false;
            boolean isPos2Valid = false;

            isPos1Valid = password.substring(position1-1,position1).equals(requiredLetter);
            isPos2Valid = password.substring(position2-1,position2).equals(requiredLetter);

            if (isPos1Valid != isPos2Valid)
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
