package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day7 {

    List<String> allLines;
    List<String> typesOfBag = new ArrayList<>();
    List<String> typesOfBagToContainShinyGoldBag = new ArrayList<>();

    int previousListSize =0;
    int currentListSize = 0;

    public static void main(String[] args) {
        new Day7();
    }


    Day7() {

        readFile("input");

        findTypesOfBag();
//        System.out.println("typesOfBag:");
//        for (int i = 0;i<typesOfBag.size();i++)
//        {
//            System.out.println(typesOfBag.get(i));
//        }

        System.out.println("=================");
        System.out.println("typesOfBagToContainShinyGoldBag:");
        findWhichBagCanHoldShinyGoldBag();
        for (int i = 0;i<typesOfBagToContainShinyGoldBag.size();i++)
        {
            System.out.println(typesOfBagToContainShinyGoldBag.get(i));
        }

        do {
            previousListSize = typesOfBagToContainShinyGoldBag.size();
            findBagsWhichCanHoldOtherBags();
            currentListSize = typesOfBagToContainShinyGoldBag.size();
        } while(previousListSize != currentListSize);

        System.out.println("=================");
        System.out.println("typesOfBagToContainShinyGoldBag after rec run:");

        for (int i = 0;i<typesOfBagToContainShinyGoldBag.size();i++)
        {
            System.out.println(typesOfBagToContainShinyGoldBag.get(i));
        }

        System.out.println("Count: " + typesOfBagToContainShinyGoldBag.size());

    }

    private void findBagsWhichCanHoldOtherBags() {
        boolean foundDuplicate = false;

        for (int a = 0;a<typesOfBagToContainShinyGoldBag.size();a++) {
            for (int i = 0; i < allLines.size(); i++) {
                if (allLines.get(i).substring(allLines.get(i).indexOf("contain")).contains(typesOfBagToContainShinyGoldBag.get(a))) {
                    String typeOfBag = allLines.get(i).substring(0, allLines.get(i).indexOf(" bags"));
                    //check if that type of bag has not yet been included
                    checkIfAlreadyInList:
                    for (int j = 0; j < typesOfBagToContainShinyGoldBag.size(); j++) {
                        if (typesOfBagToContainShinyGoldBag.get(j).equals(typeOfBag)) {
                            foundDuplicate = true;
                            break checkIfAlreadyInList;
                        }
                    }
                    if (foundDuplicate == false) {
                        typesOfBagToContainShinyGoldBag.add(typeOfBag);
                    }
                    foundDuplicate = false;
                }
            }
        }
    }

    private void findWhichBagCanHoldShinyGoldBag() {
        for (int i = 0;i<allLines.size();i++)
        {
            // we start at char "contains" so to ignore the "shiny gold bag at the beginning)
            if (allLines.get(i).substring(allLines.get(i).indexOf("contain")).contains("shiny gold bag"))
            {
                String typeOfBag = allLines.get(i).substring(0,allLines.get(i).indexOf(" bags"));
                typesOfBagToContainShinyGoldBag.add(typeOfBag);
            }
        }
    }

    private void findTypesOfBag() {
        for (int i = 0;i<allLines.size();i++)
        {
            String typeOfBag = allLines.get(i).substring(0,allLines.get(i).indexOf(" bags"));
            typesOfBag.add(typeOfBag);
        }

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
