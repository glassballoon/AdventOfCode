package Day9;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Day9pt2 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day9TestInput.txt");
        Path filePath = Paths.get("src/Input/Day9Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            var nextPredictedValue = new ArrayList<Integer>();

            for (var line: lines){
                nextPredictedValue.add(calcNextLineNumber(line));
            }

            int sum = 0;
            for (var val: nextPredictedValue) sum +=val;

            System.out.println(sum);

        } catch (Exception e){
            System.out.println(e);
        }

    }

    static int calcNextLineNumber(String line){

        var values = Arrays.stream(line.split("\\s+")).map(n -> Integer.parseInt(n)).toList();

        var nextValuesLine = new ArrayList<Integer>();
        var diffValuesLine = new ArrayList<Integer>();
        var allValuesLines = new ArrayList<ArrayList<Integer>>();

        for (Integer value: values)
            nextValuesLine.add(value);

        allValuesLines.add(nextValuesLine);

        int firstNum = 0;

        boolean runAgain = true;

        while (runAgain) {

            nextValuesLine = allValuesLines.get(allValuesLines.size() - 1);
            diffValuesLine = new ArrayList<>();

            for (int i = 1; i < nextValuesLine.size(); i++) {
                diffValuesLine.add(nextValuesLine.get(i) - nextValuesLine.get(i - 1));
            }

            firstNum = diffValuesLine.get(0);

            int finalFirstNum = firstNum;

            if ((diffValuesLine.stream().filter(n -> n == finalFirstNum).toList().size() == diffValuesLine.size())) {
                runAgain = false;

                int nextValue = finalFirstNum;

                for (int i = allValuesLines.size() - 1; i >= 0; i--) {
                    nextValue = allValuesLines.get(i).get(0) - nextValue;
                    if (i == 0) return nextValue;
                }
            } else {
                allValuesLines.add(diffValuesLine);
            }
        }
        return 0;
    }
}
