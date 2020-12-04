package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day4 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day4();
    }


    Day4() {

        readFile("input");

        String currentPassPort = "";
        int numberOfValidPassports = 0;

        for (int i = 0; i < allLines.size(); i++) {
            // read till there you get en empty line

            if (!allLines.get(i).equals("")) {
                currentPassPort = currentPassPort + " " + allLines.get(i);
            } else {
                System.out.println(currentPassPort);

                if (currentPassPort.indexOf("byr:") != -1  && currentPassPort.indexOf("iyr:") != -1 &&
                        currentPassPort.indexOf("eyr:") != -1 && currentPassPort.indexOf("hgt:") != -1 &&
                        currentPassPort.indexOf("hcl:") != -1 && currentPassPort.indexOf("ecl:") != -1 &&
                        currentPassPort.indexOf("pid:") != -1) {
                    numberOfValidPassports++;
                }
                currentPassPort = "";
            }
        }
        System.out.println(numberOfValidPassports);
    }


    private void readFile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day4\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
