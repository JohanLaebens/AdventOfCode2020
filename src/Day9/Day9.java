package Day9;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

    List<String> allLines;
    List<Long> currentListOfValues = new ArrayList<>();
    int listSize = 25;
    int currentLineInList = 0;

    public static void main(String[] args) {
        new Day9();
    }


    Day9() {

        readFile("input");

        //initially fill the list
        for (int i=0;i<listSize;i++)
        {
            currentListOfValues.add(Long.parseLong(allLines.get(i)));
        }
        currentLineInList = listSize;

        // the calculation
        for (int a = 0;a<allLines.size()-listSize;a++) {
            outer:
            for (int i = 0; i < currentListOfValues.size(); i++) {
                for (int j = 0; j < currentListOfValues.size(); j++) {
                    if (i != j) // you cannot use the same number
                    {
                        if ((currentListOfValues.get(i) + currentListOfValues.get(j)) == Long.parseLong(allLines.get(currentLineInList))) {
                            // the value is valid
                            System.out.println(">>>> " + currentListOfValues.get(i) +" ("+ i +") +"+ currentListOfValues.get(j)  +" ("+ j +") "+ "=" + allLines.get(currentLineInList));
                            // remove the first from the list
                            currentListOfValues.remove(0);

                            // add the last one from the global list
                            currentListOfValues.add(Long.parseLong(allLines.get(currentLineInList)));

                            //move on to the next in line
                            currentLineInList++;
                            break outer;
                        }
                        else
                        {
                 //           System.out.println(currentListOfValues.get(i) +"+"+ currentListOfValues.get(j) +"!=" + allLines.get(currentLineInList + 1));
                        }
                    }
                }
            }
        }
        System.out.println("Could not find match for: " + allLines.get(currentLineInList));
     }


    private void readFile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day9\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
