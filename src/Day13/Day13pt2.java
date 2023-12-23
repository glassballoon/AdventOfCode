package Day13;

import Utils.Grid2D;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Day13pt2 {
    // 36010 too low
    //40011 too high
    // not 36719

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day13TestInput.txt");
//        Path filePath = Paths.get("src/Input/Day13Input.txt");
        Path filePath = Paths.get("src/Input/Day13InputTestExtra2.txt");

        try {
            var lines = Files.readAllLines(filePath);

            var latestGridLines = new ArrayList<String>();
            int sum = 0;

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).isBlank() || i == lines.size() - 1) {

                    if (i == lines.size()-1) latestGridLines.add(lines.get(i));

                    int originalValue = runReflectionLogic(latestGridLines, -1);
                    int originalValueConverted = -1;

                    if (originalValue==0) originalValueConverted = -1;
                    else if (originalValue%100==0) originalValueConverted = originalValue-100;
                    else originalValueConverted = originalValue-1;

                    System.out.println(" ");
                    System.out.println("original value = " + originalValue);
                    System.out.println("originalValueConverted = " + originalValueConverted);

                    // run now for different changes - ignore first correct match

                    boolean haveAnswer = false;

                    for (int j = 0; j < latestGridLines.size(); j++) {
                        for (int k = 0; k < latestGridLines.get(j).length(); k++){

                            ArrayList<String> modifiedGridLines = new ArrayList<String>();
                            modifiedGridLines.addAll(latestGridLines);
                            modifiedGridLines.set(j, getModifiedGridLine(latestGridLines.get(j), k));

                            System.out.println("");
                            for (String line: modifiedGridLines)
                                System.out.println(line);

                            System.out.println("");

                            int newValue = runReflectionLogic(modifiedGridLines, originalValueConverted);

                            if (newValue != 0) {
                                System.out.println("newValue = " + newValue);
                                sum += newValue;
                                System.out.println("sum = " + sum);
                                haveAnswer = true;
                                break;
                            }
                        }
                        if (haveAnswer == true) break;
                    }

                    // clear down the grid to start a new one
                    latestGridLines.clear();
                } else
                    latestGridLines.add(lines.get(i));
            }

            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String getModifiedGridLine(String oldLine, int index) {

        char oldCharacter = oldLine.charAt(index);
        String newString = "";
        if (oldCharacter == '.')
            newString = oldLine.substring(0, index) + "#" + oldLine.substring(index + 1);
        else
            newString = oldLine.substring(0, index) + "." + oldLine.substring(index + 1);

        return newString;
    }


    private static int runReflectionLogic(ArrayList<String> lines, int ignore) {

        var grid = new Grid2D();
        grid.loadGrid(lines);

        boolean hasVerticalReflection = false;
        boolean hasHorizontalReflection = false;

        int reflectionIndex = 0;

        // check for vertical reflection
        // start with rows closest to the left and try reflection there
        for (int i = 0; i < grid.getNumCols() - 1; i++) {
            if (hasVerticalReflection) break;
            if (i == ignore) continue;
            var columnLeft = grid.getCharactersInColumn(i);
            var columnRight = grid.getCharactersInColumn(i + 1);
            if (doesCharacterListMatch(columnLeft, columnRight)) {
                // check full refection
                for (int j = 0; j < grid.getNumCols(); j++) {
                    if (i - (j + 1) > -1 && i + j + 2 < grid.getNumCols()) {
                        if (!doesCharacterListMatch(grid.getCharactersInColumn(i - (j + 1)), grid.getCharactersInColumn(i + j + 2))) {
                            break;
                        }
                    } else { //we reflected all the way through
                        hasVerticalReflection = true;
                        reflectionIndex = i;
                        break;
                    }
                    if (hasVerticalReflection) break;
                }
            }
        }

        // if no vertical reflection now try for horizontal
        // start with rows closest to the top and try reflection there
        if (!hasVerticalReflection) {

            for (int i = 0; i < grid.getNumRows() - 1; i++) {
                if (hasHorizontalReflection) break;
                if (ignore%100 == 0 && i == ignore/100) continue;
                var rowLeft = grid.getCharactersInRow(i);
                var rowRight = grid.getCharactersInRow(i + 1);
                if (doesCharacterListMatch(rowLeft, rowRight)) {
                    // check full refection
                    for (int j = 0; j < grid.getNumRows(); j++) {
                        if (i - (j + 1) > -1 && i + j + 2 < grid.getNumRows()) {
                            if (!doesCharacterListMatch(grid.getCharactersInRow(i - (j + 1)), grid.getCharactersInRow(i + j + 2))) {
                                break;
                            }
                        } else { //we reflected all the way through
                            hasHorizontalReflection = true;
                            reflectionIndex = i;
                        }
                    }
                }
            }
        }
//        System.out.println(hasVerticalReflection + ", " + hasHorizontalReflection + ", " + reflectionIndex);
//        if (hasVerticalReflection || hasHorizontalReflection){
//            System.out.println("grid with reflection");
//            for (String line: lines)
//                System.out.println(line);
//
//            System.out.println("");
//        }
        if (hasVerticalReflection) return reflectionIndex + 1;
        else if (hasHorizontalReflection) return (reflectionIndex + 1) * 100;
        else return 0;
    }

    private static boolean doesCharacterListMatch(ArrayList<Character> list1, ArrayList<Character> list2) {
        // assume sizes are the same - should be fine for this
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) return false;
        }
        return true;
    }
}
