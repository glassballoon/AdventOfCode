package Day13;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Utils.Grid2D;

public class Day13pt1 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day13TestInput.txt");
        Path filePath = Paths.get("src/Input/Day13InputTestExtra.txt");
//        Path filePath = Paths.get("src/Input/Day13Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            var latestGridLines = new ArrayList<String>();
            int sum = 0;

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).isBlank() || i == lines.size() - 1) {
                    sum += runReflectionLogic(latestGridLines);
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

    private static int runReflectionLogic(ArrayList<String> lines) {
        var grid = new Grid2D();
        grid.loadGrid(lines);

        boolean hasVerticalReflection = false;
        boolean hasHorizontalReflection = false;

        int reflectionIndex = 0;

        // check for vertical reflection
        // start with rows closest to the left and try reflection there
        for (int i = 0; i < grid.getNumCols() - 1; i++) {
            var columnLeft = grid.getCharactersInColumn(i);
            var columnRight = grid.getCharactersInColumn(i + 1);
            if (doesCharacterListMatch(columnLeft, columnRight)) {
                // check full refection
                for (int j = 0; j < grid.getNumCols(); j++) {
                    if ((i - (j + 1) > -1) && (i + j + 2 < grid.getNumCols())) {
                        if (!doesCharacterListMatch(grid.getCharactersInColumn(i - (j + 1)), grid.getCharactersInColumn(i + j + 2))) {
                            break;
                        }
                    } else { //we reflected all the way through
                        hasVerticalReflection = true;
                        reflectionIndex = i;
                    }
                }
            }
        }

        // if no vertical reflection now try for horizontal
        // start with rows closest to the top and try reflection there
        if (!hasVerticalReflection) {
            for (int i = 0; i < grid.getNumRows() - 1; i++) {
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


        if (hasVerticalReflection) return reflectionIndex + 1;
        if (hasHorizontalReflection) return (reflectionIndex + 1) * 100;
        return 0;
    }

    private static boolean doesCharacterListMatch(ArrayList<Character> list1, ArrayList<Character> list2) {
        // assume sizes are the same - should be fine for this
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) return false;
        }
        return true;
    }


}
