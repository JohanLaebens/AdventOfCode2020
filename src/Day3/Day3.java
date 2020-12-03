package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day3();
    }


    Day3() {

        readfile("input");

        String clear = ".";
        String tree = "#";

        int curPosX = 0;
        int curPosY = 0;

        int numberOfTrees = 0;
        int lineWidth = allLines.get(1).length();

        for (int i= 0;i<allLines.size();i++)
        {
            // use de module to see the end of a line
            int posX = curPosX % lineWidth;
            int posX2 = (curPosX % lineWidth)+1;

            // When posX happens to be last char, posX 2 is the start of the line
            if (posX2 == lineWidth+1)
            {
                posX2 = 0;
            }

            if (allLines.get(i).substring(posX,posX2).equals(tree))
            {
                numberOfTrees++;
            }
            curPosX = curPosX+3;
            curPosY = curPosY+1;
        }
        System.out.println("NumberOfTrees: "  + numberOfTrees);
    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day3\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
