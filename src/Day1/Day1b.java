package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1b {

    List<String> allLines;


    public static void main(String[] args) {
        new Day1b();
    }


    Day1b() {

        readfile("input");

        outer: for (int i = 0; i<allLines.size();i++)
        {
            for (int j = i+1; j<allLines.size();j++)
            {
                for (int k = j+1; k<allLines.size();k++){
                    int sum = Integer.parseInt(allLines.get(i)) + Integer.parseInt(allLines.get(j)) + Integer.parseInt(allLines.get(k));
                    System.out.println("SUM: " + i +"+" + j +"+"+ k +"=" + sum);
                    if (sum == 2020)
                    {
                        System.out.println("GEVONDEN!!!!");
                        System.out.println("Multi: " + Integer.parseInt(allLines.get(i))*Integer.parseInt(allLines.get(j))*Integer.parseInt(allLines.get(k)));
                        break outer;
                    }
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
