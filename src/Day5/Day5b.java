package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day5b {

    List<String> allLines;

    public static void main(String[] args) {
        new Day5b();
    }




    Day5b() {

        readFile("input");
        int [] allSeats = new int[allLines.size()];

        for (int x = 0; x < allLines.size(); x++) {
            int rowNr = 0;
            int seatNr = 0;
            int lowerLimit = 0;
            int upperLimit = 127;
            int seatID = 0;

            // first the rowNr
            // six times, last time is special
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

            // add to array of seats
            allSeats[x] = seatID;
        }
        //sort the array
        Arrays.sort(allSeats);

        // Print 'm all, just a visual check
//        for (int i = 0;i<allSeats.length;i++)
//        {
//            System.out.println(allSeats[i]);
//        }

        //set previous and next for the first instance
        int previousSeatID = allSeats[0] -1;
        int currentSeatID = allSeats[0];
        int nextSeatID = allSeats[0] +1;

        for (int i = 0;i<allSeats.length;i++)
        {
            currentSeatID = allSeats[i];
            if (!((previousSeatID == currentSeatID -1) || (nextSeatID == currentSeatID+1)))
            {
                int missingSeatID = currentSeatID -1;
                System.out.println("Missing seatID : " + missingSeatID);
            }
            previousSeatID = currentSeatID;
            nextSeatID = currentSeatID +2;
        }
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
