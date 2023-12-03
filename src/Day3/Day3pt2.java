package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3pt2 {

    public static void main(String[] args) {
//        var filePath = Paths.get("C:/Users/rowen/java/AdventOfCode2023/src/Day3/Day3TestInput.txt");
        var filePath = Paths.get("C:/Users/rowen/java/AdventOfCode2023/src/Day3/Day3Input.txt");
        try {
            var lines = Files.readAllLines(filePath);

            int numRows = lines.size();
            int numColumns = lines.get(0).length();

            //let's pop everything into a huge ArrayList
            var allChars = new ArrayList<Character>();
            for (var line: lines){
                var characterArray = new ArrayList<Character>();
                for (var cha: line.toCharArray()){
                    characterArray.add(cha);
                }
                allChars.addAll(characterArray);
            }

            int index = 0;
            int sum = 0;
            while (index < allChars.size()){
                Character c = allChars.get(index);

                if (c.toString().equals("*")) {
                    var indexes = getAdjacentNumberIndexes(allChars, index, numRows, numColumns);
                    if (indexes.size() > 1) {
                        int gearRatio = 1;
                        for (var i : indexes)
                            gearRatio = gearRatio * extractNum(allChars, i, numRows);

                        sum = sum + gearRatio;
                    }
                }
                index++;
            }

            System.out.println(sum);
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }

    static ArrayList<Integer> getAdjacentNumberIndexes(List<Character> allChars, int index, int numRows, int numColumns){
        var numAdjacentNums = new ArrayList<Integer>();
        int row = (int) Math.floor(index/numRows);
        int col = index%numColumns;
//        System.out.println(allChars.get(index) + " index: " + index + " row: " + row + ", col: " + col + " numRows = " + numRows + " numCols = " + numColumns);
        // check to the top
        if (row != 0 && Character.isDigit(allChars.get(index-numColumns)))
            numAdjacentNums.add(index-numColumns);
        else {
            // check diagonally to top right
            if (row != 0 && col != numColumns - 1 && Character.isDigit(allChars.get(index - numColumns + 1)))
                numAdjacentNums.add(index - numColumns + 1);
            // check diagonally top left
            if (row != 0 && col != 0 && Character.isDigit(allChars.get(index-numColumns-1)))
                numAdjacentNums.add(index-numColumns-1);
        }
        // check to the right
        if (col != numColumns-1 && Character.isDigit(allChars.get(index+1)))
            numAdjacentNums.add(index+1);

        // check below
        if (row != numRows-1 && Character.isDigit(allChars.get(index+numColumns)))
            numAdjacentNums.add(index+numColumns);
        else {
            // check diagonally to below right
            if (row != numRows-1 && col != numColumns-1 && Character.isDigit(allChars.get(index+numColumns+1)))
                numAdjacentNums.add(index+numColumns+1);
            // check diagonally lower left
            if (col != 0 && row != numRows-1 && Character.isDigit(allChars.get(index+numColumns-1)))
                numAdjacentNums.add(index+numColumns-1);

        }

        // check left
        if (col != 0 && Character.isDigit(allChars.get(index-1)))
            numAdjacentNums.add(index-1);

        return numAdjacentNums;
    }

    static Integer extractNum(List<Character> allChars, int index, int numCols){

        String number = allChars.get(index).toString();

        boolean checkToRight = true;
        boolean checkToLeft = true;

        int currentPosition = index;
        int startingCol = getColNum(index, numCols);

        while (checkToRight){
            currentPosition++;
            Character nextChar = allChars.get(currentPosition);
            if (getColNum(currentPosition, numCols) > startingCol && Character.isDigit(nextChar))
                number = number + nextChar.toString();
            else
                checkToRight = false;

        }

        currentPosition = index;

        while (checkToLeft && currentPosition > 0) {
            currentPosition--;
            Character nextChar = allChars.get(currentPosition);
            if (getColNum(currentPosition, numCols) < startingCol && Character.isDigit(nextChar)) {
                number = nextChar.toString() + number;
            } else
                checkToLeft = false;
        }

        return Integer.valueOf(number);
    }

    public static int getRowNum(int index, int numRows){
        return (int) Math.floor(index/numRows);
    }

    public static int getColNum(int index, int numColumns){
        return index%numColumns;
    }
}
