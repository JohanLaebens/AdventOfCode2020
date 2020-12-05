package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day5 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day5();
    }


    Day5() {

        readFile("input");
        int highestSeatID = 0;

        for (int x = 0; x < allLines.size(); x++) {
            int rowNr = 0;
            int seatNr = 0;
            int lowerLimit = 0;
            int upperLimit = 127;
            int seatID = 0;

            // first, calculate rowID
            // six times, last time is special and will be treated differently
            for (int i = 0; i < 6; i++) {
                String value = allLines.get(x).substring(i, i + 1);
                int half = (upperLimit - lowerLimit) / 2;
                if (value.equals("F")) //take the lowerLimit half
                {
                    upperLimit = upperLimit - half - 1;
                } else if (value.equals("B")) // take the upperLimit half
                {
                    lowerLimit = lowerLimit + half + 1;
                }
       //         System.out.println("rowcheck " + i + " = " + lowerLimit + "/" + upperLimit);
            }


            String value = allLines.get(x).substring(6, 7);
            if (value.equals("F")) {
                rowNr = lowerLimit;
            } else if (value.equals("B")) {
                rowNr = upperLimit;
            }
            System.out.println("rowNr : " + rowNr);

            //then the column
            //reset lowerLimit and upperLimit to be used for the columns
            lowerLimit = 0;
            upperLimit = 7;
            for (int i = 7; i < 9; i++) {
                value = allLines.get(x).substring(i, i + 1);
                int half = (upperLimit - lowerLimit) / 2;
                if (value.equals("L")) //take the lowerLimit half
                {
                    upperLimit = upperLimit - half - 1;
                } else if (value.equals("R")) // take the upperLimit half
                {
                    lowerLimit = lowerLimit + half + 1;
                }
           //     System.out.println("seatheck " + i + " = " + lowerLimit + "/" + upperLimit);
            }


            value = allLines.get(x).substring(9, 10);
            if (value.equals("L")) {
                seatNr = lowerLimit;
            } else if (value.equals("R")) {
                seatNr = upperLimit;
            }
            System.out.println("seatNr : " + seatNr);

            seatID = (rowNr*8)+seatNr;
            System.out.println("seatID : " + seatID);
            System.out.println();

            if (seatID > highestSeatID)
            {
                highestSeatID = seatID;
            }
        }
        System.out.println("Highest = " + highestSeatID);
    }

    private void readFile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day5\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
