package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day8b {

    List<String> allLines;
    List<String> allLinesBackup;

    int lastChangedOperatorLine = -1;

    List<Integer> alreadyRanInstructionLines=  new ArrayList<>();;
    int currentInstructionLine = 0;
    int previousAccumulatorValue = 0;
    int nextInstructionLine = 0;
    int globalAccumulatorValue = 0;

    boolean isSolutionFound = false;

    public static void main(String[] args) {
        new Day8b();
    }


    Day8b() {

        readFile("input");

        long start_time = System.currentTimeMillis();

        //setup backup
        allLinesBackup = new ArrayList<>(allLines);

        while (!(isSolutionFound)) {
                // change the first Nop to a jmp or first jmp to a nop
                // restore the alllines with the backup
                allLines = new ArrayList<>(allLinesBackup);
            loop:
            for (int i = lastChangedOperatorLine+1; i < allLines.size(); i++) {
                if (allLines.get(i).substring(0, 3).equals("jmp")) {
                    allLines.set(i,allLines.get(i).replace("jmp", "nop"));
                    lastChangedOperatorLine = i;
   //                 System.out.println("ChangedOperator for line " + i);
                    break loop;
                } else if (allLines.get(i).substring(0, 3).equals("nop")) {
                    allLines.set(i,allLines.get(i).replace("nop", "jmp"));
                    lastChangedOperatorLine = i;
  //                  System.out.println("ChangedOperator for line " + i);
                    break loop;
                }
            }


            //reset everything for a new run
            currentInstructionLine = 0;
            previousAccumulatorValue = 0;
            nextInstructionLine = 0;
            globalAccumulatorValue = 0;
            alreadyRanInstructionLines.clear();

            executeProgram:
            while (!(hasInstructionAlreadyBeenExecuted(currentInstructionLine))) {
                String instruction = allLines.get(currentInstructionLine).substring(0, allLines.get(currentInstructionLine).indexOf(" "));
                String operator = allLines.get(currentInstructionLine).substring(allLines.get(currentInstructionLine).indexOf(" ") + 1, allLines.get(currentInstructionLine).indexOf(" ") + 2);
                int value = Integer.parseInt(allLines.get(currentInstructionLine).substring(allLines.get(currentInstructionLine).indexOf(operator) + 1));

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
                if (currentInstructionLine >= allLines.size())
                {
                    isSolutionFound = true;
                    break executeProgram;
                }
            }
        }
        System.out.println("Last Accumulator value = " + previousAccumulatorValue);
        long end_time = System.currentTimeMillis();
        long difference = end_time-start_time;
        System.out.println("Time to calculate (ms): " + difference);

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
