package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day7b {

    List<String> allLines;

    public static void main(String[] args) {
        new Day7b();
    }

    Day7b() {

        readFile("input");

        int amount = 1;
        String typeOfBag = "shiny gold";
        int result = findBagsForType(amount, typeOfBag);

        result = result -1; //eigen shiny gold bag niet tellen
        System.out.println("result: " + result);
    }

    private int findBagsForType(int amount, String typeOfBag) {
        System.out.println("working on: " + amount + " " + typeOfBag);

        String bagLine = findContainsLineForTypeOfBag(typeOfBag);

        if (bagLine.contains("no other bags")) {
            return amount;
        } else {
            int subtotal = 0;
            while (bagLine.length() > 4) {
                int start = 0;
                if (bagLine.contains("contain")) {
                    start = bagLine.indexOf("contain") + 8;
                } else {
                    start = bagLine.indexOf(",") + 2;
                }
                bagLine = bagLine.substring(start);

                int numberOfBagsInLine = 0;
                String typeOfBagInLine = "";

                numberOfBagsInLine = Integer.parseInt(bagLine.substring(0, bagLine.indexOf(" ")));
                typeOfBagInLine = bagLine.substring(bagLine.indexOf(" ") + 1, bagLine.indexOf(" bag"));

                int result = findBagsForType(numberOfBagsInLine, typeOfBagInLine);
                System.out.println("result for " + typeOfBagInLine + ": " + result);
                subtotal = subtotal + (amount * result);

                bagLine = bagLine.substring(bagLine.indexOf(" bag") + 4);
            }
            amount = amount + subtotal;
        }
        return amount;
    }

    private String findContainsLineForTypeOfBag(String typeOfBag) {
        String Line = "";

        searchForBagLine:
        for (int i = 0; i < allLines.size(); i++) {

            if (allLines.get(i).substring(0, allLines.get(i).indexOf(" bags")).equals(typeOfBag)) {
                System.out.println(allLines.get(i));
                return allLines.get(i);
            }
        }
        return "";
    }


    private void readFile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day7\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
