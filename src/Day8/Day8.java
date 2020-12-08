package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day8 {

    List<String> allLines;

    List<Integer> alreadyRanInstructionLines=  new ArrayList<>();;
    int currentInstructionLine = 0;
    int previousAccumulatorValue = 0;
    int nextInstructionLine = 0;
    int globalAccumulatorValue = 0;

    public static void main(String[] args) {
        new Day8();
    }


    Day8() {

        readFile("input");

        currentInstructionLine = 0;

        while (!(hasInstructionAlreadyBeenExecuted(currentInstructionLine)))
        {
            String instruction = allLines.get(currentInstructionLine).substring(0,allLines.get(currentInstructionLine).indexOf(" "));
            String operator = allLines.get(currentInstructionLine).substring(allLines.get(currentInstructionLine).indexOf(" ")+1,allLines.get(currentInstructionLine).indexOf(" ")+2);
            int value = Integer.parseInt(allLines.get(currentInstructionLine).substring(allLines.get(currentInstructionLine).indexOf(operator)+1));

            switch (instruction) {
                case "nop":                 // just go to the next line
                    nextInstructionLine = currentInstructionLine + 1;
                    break;
                case "jmp":                // jump to line X
                    if (operator.equals("+")) {
                        nextInstructionLine = currentInstructionLine + value;
                    } else {
                        nextInstructionLine = currentInstructionLine - value;
                    }
                    break;
                case "acc":                // add to global value and move to next instruction
                    if (operator.equals("+")) {
                        globalAccumulatorValue = globalAccumulatorValue + value;
                    } else {
                        globalAccumulatorValue = globalAccumulatorValue - value;
                    }
                    nextInstructionLine = currentInstructionLine + 1;
                    break;
            }
            alreadyRanInstructionLines.add(currentInstructionLine);
            previousAccumulatorValue = globalAccumulatorValue;
            currentInstructionLine = nextInstructionLine;
        }
        System.out.println("Last Accumulator value = " + previousAccumulatorValue);
     }

     private boolean hasInstructionAlreadyBeenExecuted(int line)
     {
         for (int i = 0;i<alreadyRanInstructionLines.size();i++)
         {
             if (alreadyRanInstructionLines.get(i) == line)
             {
                 return true;
             }
         }
         return false;
     }


    private void readFile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day8\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
