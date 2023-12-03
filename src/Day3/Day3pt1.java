package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3pt1 {

    public static void main(String[] args) {
//        var filePath = Paths.get("src/Day3/Day3TestInput.txt");
        var filePath = Paths.get("src/Day3/Day3Input.txt");
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
//            for (int i = 0; i < allChars.size(); i++) {
//                System.out.println(hasAdjacentSymbol(allChars, i, numRows, numColumns));
//            }
            int index = 0;
            var validNums = new ArrayList<Integer>();
            boolean startedNum = false;
            boolean numHasAdjacentSymbol = false;
            String currentNum = "";
            while (index < allChars.size()){
                Character c = allChars.get(index);
                if (Character.isDigit(c)) {
                    startedNum = true;
                    currentNum = currentNum + c.toString();
                    if (!numHasAdjacentSymbol)
                            numHasAdjacentSymbol = hasAdjacentSymbol(allChars, index, numRows, numColumns);
                }
                else if (startedNum) {
                    if (numHasAdjacentSymbol){
                        validNums.add(Integer.valueOf(currentNum));
                        numHasAdjacentSymbol = false;
                    }
                    startedNum = false;
                    currentNum = "";
                }
                index++;
            }

            int sum = 0;
            for (var num: validNums)
                sum += num;

            System.out.println(sum);
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }

    static boolean hasAdjacentSymbol(List<Character> allChars, int index, int numRows, int numColumns){

        boolean hasAdjSymbol = false;
        int row = (int) Math.floor(index/numRows);
        int col = index%numColumns;
        System.out.println(allChars.get(index) + " index: " + index + " row: " + row + ", col: " + col + " numRows = " + numRows + " numCols = " + numColumns);
        // check to the top
        hasAdjSymbol = row != 0 && isSymbol(allChars.get(index-numColumns));
        // check diagonally to top right
        if (!hasAdjSymbol)
            hasAdjSymbol = row != 0 && col != numColumns-1 && isSymbol(allChars.get(index-numColumns+1));
        // check to the right
        if (!hasAdjSymbol)
            hasAdjSymbol = col != numColumns-1 && isSymbol(allChars.get(index+1));
        // check diagonally to below right
        if (!hasAdjSymbol)
            hasAdjSymbol = row != numRows-1 && col != numColumns-1 && isSymbol(allChars.get(index+numColumns+1));
        // check below
        if (!hasAdjSymbol)
            hasAdjSymbol = row != numRows-1 && isSymbol(allChars.get(index+numColumns));
        // check diagonally lower left
        if (!hasAdjSymbol)
            hasAdjSymbol = col != 0 && row != numRows-1 && isSymbol(allChars.get(index+numColumns-1));
        // check left
        if (!hasAdjSymbol)
            hasAdjSymbol = col != 0 && isSymbol(allChars.get(index-1));
        // check diagonally top left
        if (!hasAdjSymbol)
            hasAdjSymbol = row != 0 && col != 0 && isSymbol(allChars.get(index-numColumns-1));
        return hasAdjSymbol;
    }

    static boolean isSymbol(Character cha){
        return !(Character.isDigit(cha) || cha.toString().equals("."));
    }
}
