package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day6b {

    List<String> allLines;
    public static void main(String[] args) {
        new Day6b();
    }




    Day6b() {

        readFile("input");
        int count = 0;
        List<String> currentGroup = new ArrayList<>();

        for (int i = 0; i < allLines.size(); i++) {
            // read till there you get en empty line

            if (!allLines.get(i).equals("")) {
                // each line in a group gets an array entry
                currentGroup.add(allLines.get(i));
            } else {
                // check all letters
                letterfor: for (int j = 97;j<123;j++)
                {
                    char c = (char) j;
                    int countForCurrentAnswer = 0;

                    // check if that letter is available in a line
                    for (int k=0;k<currentGroup.size();k++)
                    {
                        if (currentGroup.get(k).contains(Character.toString(c))) {
                            countForCurrentAnswer++;
                        }
                    }
                    // everyone has answered that question
                    if (countForCurrentAnswer == currentGroup.size()) {
                        count++;
                    }
                }

                //clear currentGroup for next for loop
                currentGroup.clear();
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
