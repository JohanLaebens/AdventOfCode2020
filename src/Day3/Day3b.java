package Day3;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3b {

    List<String> allLines;


    public static void main(String[] args) {
        new Day3b();
    }


    Day3b() {

        readfile("input");

        BigInteger solution = BigInteger.valueOf(runSimilation(1,1) *
                        runSimilation(3,1) *
                        runSimilation(5,1) *
                        runSimilation(7,1) *
                        runSimilation(1,2));
        System.out.println("Solution: " + solution);
    }

    private int runSimilation(int addToX, int addToY) {
        String clear = ".";
        String tree = "#";

        int curPosX = 0;
        int curPosY = 0;

        int numberOfTrees = 0;
        int lineWidth = allLines.get(1).length();

        for (int i= 0;i<allLines.size();i=i+addToY)
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
            curPosX = curPosX+addToX;
            curPosY = curPosY+addToY;
        }
        System.out.println("NumberOfTrees: "  + numberOfTrees);
        return numberOfTrees;
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
