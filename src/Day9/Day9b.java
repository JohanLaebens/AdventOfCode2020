package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day9b {

    List<String> allLines;
    List<Long> currentListOfValues = new ArrayList<>();
    int listSize = 0;
    int currentLineInList = 0;

    long sum = 0;
    int step = 0;

    public static void main(String[] args) {
        new Day9b();
    }


    Day9b() {
        String filename = "input";


        readFile(filename);

        if (filename.equals ("input"))
        {
            listSize = 25;
            sum = 542529149;
            step = 2;
        }
        else if (filename.equals("example"))
        {
            listSize = 5;
            sum = 127;
            step = 2;
       }

        boolean solutionFound = false;

        while (!(solutionFound))
        {
            forloop: for (int i = 0; i < allLines.size() - step; i++) {
                Long tempSum = Long.valueOf(0);
                for (int j = 0; j < step; j++) {
                    tempSum = tempSum + Long.parseLong(allLines.get(i+j));
                }
                System.out.println("Step: " + step + " sum: " + tempSum);
                if (tempSum == sum) {
                    System.out.println("FOUND IT: " + step + " " + i + " " + allLines.get(i));
                    // find the smallest in range
                    long smallest = Long.MAX_VALUE;
                    long biggest = Long.MIN_VALUE;
                    for (int x = i;x<i+step;x++)
                    {
                        if (Long.parseLong(allLines.get(x)) < smallest)
                        {
                            smallest = Long.parseLong(allLines.get(x));
                        }
                        if (Long.parseLong(allLines.get(x)) > biggest)
                        {
                            biggest = Long.parseLong(allLines.get(x));
                        }
                    }
                    long sum = smallest + biggest;
                    System.out.println("Solution: " + smallest +" + " + biggest + " = " + sum );
                    solutionFound = true;
                    break forloop;
                }
            }
            step++;
        }
        System.out.println("THE END");
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
