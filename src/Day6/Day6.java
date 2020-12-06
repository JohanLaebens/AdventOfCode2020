package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day6 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day6();
    }


    Day6() {

        readFile("input");
        int count = 0;

        String currentGroup = "";

        for (int i = 0; i < allLines.size(); i++) {
            // read till there you get en empty line

            if (!allLines.get(i).equals("")) {
                currentGroup = currentGroup + allLines.get(i);
            } else {

                String uniqueValuesInCurrentGroup = "";
                for (int j = 0; j < currentGroup.length(); j++) {
                    if (!(uniqueValuesInCurrentGroup.contains(currentGroup.substring(j, j + 1)))) {
                        uniqueValuesInCurrentGroup = uniqueValuesInCurrentGroup + currentGroup.substring(j, j + 1);
                        count++;
                    }
                }
                //clear currentGroup for next for loop
                currentGroup = "";
            }
        }
        System.out.println("Solution = " + count);
    }

    private void readFile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day6\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
