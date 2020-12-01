package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day1();
    }


    Day1() {

        readfile("input");

        outer: for (int i = 0; i<allLines.size();i++)
        {
            for (int j = i+1; j<allLines.size();j++)
            {
                
                int sum = Integer.parseInt(allLines.get(i)) + Integer.parseInt(allLines.get(j));
                System.out.println("SUM: " + i +"+" + j + "=" + sum);
                if (sum == 2020)
                {
                    System.out.println("GEVONDEN!!!");
                    System.out.println("Multi: " + Integer.parseInt(allLines.get(i))*Integer.parseInt(allLines.get(j)));
                    break outer;
                }
            }
        }
    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day1\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
