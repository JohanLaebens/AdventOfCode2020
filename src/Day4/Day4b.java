package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.temporal.Temporal;
import java.util.List;

public class Day4b {

    List<String> allLines;


    public static void main(String[] args) {
        new Day4b();
    }


    Day4b() {

        readFile("input");

        String currentPassPort = "";
        int birthYear = 0;
        int issueYear = 0;
        int expirationYear = 0;
        int height = 0;
        String heightType = "";
        String hairColour, eyeColour, passportID, countryID = "";
        boolean isBirthYearValid =false;
        boolean isIssueYearValid = false;
        boolean isExpirationYearValid = false;
        boolean isHeightValid = false;
        boolean isHairColourValid =false;
        boolean isEyeColourValid = false;
        boolean isPassportIDValid = false;
        boolean isCountryIDValid = false;
        int numberOfValidPassports = 0;

        for (int i = 0; i < allLines.size(); i++) {
            // read till there you get en empty line

            if (!allLines.get(i).equals("")) {
                currentPassPort = currentPassPort + " " + allLines.get(i);
            } else {
                currentPassPort = currentPassPort + " ";

                // passportLine is complete, validation starts
                if (currentPassPort.indexOf("byr:") != -1)
                {
                    String tempString =  currentPassPort.substring(currentPassPort.indexOf("byr:")+4);
                    String value = tempString.substring(0,tempString.indexOf(" "));

                    try {
                        birthYear = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        birthYear = 0;
                    }

                    if ((birthYear >= 1920 && birthYear <=2002))
                    {
                        isBirthYearValid = true;
                    }
                }

                if (currentPassPort.indexOf("iyr:") != -1)
                {
                    String tempString =  currentPassPort.substring(currentPassPort.indexOf("iyr:")+4);
                    String value = tempString.substring(0,tempString.indexOf(" "));

                    try {
                        issueYear = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        issueYear = 0;
                    }

                    if ((issueYear >= 2010 && issueYear <=2020))
                    {
                        isIssueYearValid = true;
                    }
                }

                if (currentPassPort.indexOf("eyr:") != -1)
                {
                    String tempString =  currentPassPort.substring(currentPassPort.indexOf("eyr:")+4);
                    String value = tempString.substring(0,tempString.indexOf(" "));

                    try {
                        expirationYear = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        expirationYear = 0;
                    }

                    if ((expirationYear >= 2020 && expirationYear <=2030))
                    {
                        isExpirationYearValid = true;
                    }
                }

                if (currentPassPort.indexOf("hgt:") != -1)
                {
                    String tempString =  currentPassPort.substring(currentPassPort.indexOf("hgt:")+4);
                    String value = tempString.substring(0,tempString.indexOf(" "));

                    heightType = value.substring(value.length()-2);
                    try {
                        height = Integer.parseInt(value.substring(0,value.length()-2));
                    } catch (NumberFormatException e) {
                        height = 0;
                    }

                    if (heightType.equals("cm"))
                    {
                        if ((height >= 150 && height <=193))
                        {
                            isHeightValid = true;
                        }
                    }else if(heightType.equals("in")){
                        if ((height >= 59 && height <=76))
                        {
                            isHeightValid = true;
                        }
                    }
                }

                if (currentPassPort.indexOf("hcl:") != -1)
                {
                    String tempString =  currentPassPort.substring(currentPassPort.indexOf("hcl:")+4);
                    String value = tempString.substring(0,tempString.indexOf(" "));

                    hairColour = value;
                    if (hairColour.matches("#[0123456789abcdef]{6}")){
                        isHairColourValid = true;
                        }
                }

                if (currentPassPort.indexOf("ecl:") != -1)
                {
                    String tempString =  currentPassPort.substring(currentPassPort.indexOf("ecl:")+4);
                    String value = tempString.substring(0,tempString.indexOf(" "));

                    eyeColour = value;
                    if (eyeColour.equals("amb") || eyeColour.equals("blu") || eyeColour.equals("brn") || eyeColour.equals("gry") || eyeColour.equals("grn") || eyeColour.equals("hzl") || eyeColour.equals("oth")){
                        isEyeColourValid = true;
                    }
                }

                if (currentPassPort.indexOf("pid:") != -1)
                {
                    String tempString =  currentPassPort.substring(currentPassPort.indexOf("pid:")+4);
                    String value = tempString.substring(0,tempString.indexOf(" "));

                    passportID = value;
                    if (passportID.matches("[0123456789]{9}")){
                       isPassportIDValid = true;
                    }
                }

                // no checks on CountryID yet

                // I know I could break earlier (when there is one mistake), but I assume there will be days which will continue on this task so I left it as open as possible.
                if (isBirthYearValid && isIssueYearValid && isExpirationYearValid &&
                        isHeightValid && isHairColourValid && isEyeColourValid && isPassportIDValid)
                {
                    //end of checks, passport is valid
                    numberOfValidPassports++;
                }

                //clearline && checks
                currentPassPort = "";
                isBirthYearValid = false;
                isIssueYearValid = false;
                isExpirationYearValid = false;
                isHeightValid = false;
                isHairColourValid = false;
                isEyeColourValid = false;
                isPassportIDValid = false;
                isCountryIDValid = false;
            }
        }

        System.out.println(numberOfValidPassports);
    }


    private void readFile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day4\\" + filename + ".txt"));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
