package Day14;

import Utils.Grid2D;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day14pt2 {

    public static final char roundedRock = 'O';
    public static final char cubeShapedRock = '#';
    public static final char emptySpace = '.';

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day14TestInput.txt");
        Path filePath = Paths.get("src/Input/Day14Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            var grid = new Grid2D();
            grid.loadGrid(lines);

            var previousGrids = new ArrayList<String>();

            boolean stopFindingPattern = false;

            int startValue = 0;
            int loopSize = 0;
            int iterations = 1000000000;

            for (int i = 0; i < 1000000000; i++) {

                grid = cycleGrid(grid);

                // check for previous match to get the pattern
                for (int j = 0; j < previousGrids.size(); j++){
                    if (previousGrids.get(j).equals(grid.toString())){
                        startValue = j;
                        loopSize = previousGrids.size() - j;
//                        System.out.println("Prev matching Grid Location: " + j);
//                        System.out.println("Current Location: " + previousGrids.size());
                        stopFindingPattern = true;
                        break;
                    }
                }
                previousGrids.add(grid.toString());
                if (stopFindingPattern){
                    break;
                }
            }

            // find num loops
            int howManyMoreCycles = (iterations - (startValue+1)) % loopSize;
            for (int i = 0; i < howManyMoreCycles; i++) grid = cycleGrid(grid);

            int sum = 0;
            var gridChars = grid.getGrid();

            for (int i = 0; i < gridChars.size(); i++)
                if (grid.getCharacterAt(i) == roundedRock) sum += grid.getNumRows() - grid.getRowNumber(i);

            System.out.println(sum);

        } catch (Exception e){
            System.out.println(e);
        }
    }

    private static Grid2D cycleGrid(Grid2D grid){

        grid = rollDownwards(grid);

        // rotate and roll 3 times, once to west, then south, then east
        for (int i = 0; i < 3; i++) {
            grid = rotateGrid90Degrees(grid);
            grid = rollDownwards(grid);
        }

        // make north again
        grid = rotateGrid90Degrees(grid);
        return grid;
    }

    private static Grid2D rollDownwards(Grid2D grid){
        // get each column - roll roundedRock to northmost possible point
        for (int i = 0; i < grid.getNumCols(); i++){ // i = column

            int highestAvailableSpace = -1;

            boolean calculate = true;
            int row = 0;

            while (calculate){
                var column = grid.getCharactersInColumn(i); // just in case it was updated

                switch (column.get(row)) {
                    case emptySpace :
                        if (highestAvailableSpace == -1) highestAvailableSpace = row;
                        row++;
                        break;
                    case cubeShapedRock:
                        highestAvailableSpace = -1;
                        row++;
                        break;
                    case roundedRock:
                        if (highestAvailableSpace != row && highestAvailableSpace != -1) {
                            grid.setCharacterAt(roundedRock, highestAvailableSpace, i);
                            grid.setCharacterAt(emptySpace, row, i);
                            row = highestAvailableSpace + 1;
                            highestAvailableSpace = -1;
                        } else row++;
                        break;
                }
                if (row == grid.getNumRows()) calculate = false;
            }
        }
        return grid;
    }

    private static Grid2D rotateGrid90Degrees(Grid2D grid){
        var lines = new ArrayList<String>();
        for (int i = 0; i < grid.getNumCols(); i++){ // i = column
            var column = grid.getCharactersInColumn(i);
            String colToString = "";
            for (char c: column)
                colToString = colToString + c;

            lines.add((new StringBuffer(colToString)).reverse().toString());
        }

        Grid2D newGrid = new Grid2D();
        newGrid.loadGrid(lines);
        return newGrid;
    }

    private static boolean gridMatches(List<Character> firstGrid, List<Character> secondGrid){
        // assume they will always be the same size - because they should be
        for (int i = 0; i < firstGrid.size(); i++) {
            if (!firstGrid.get(i).equals(secondGrid.get(i))) return false;
        }
        return true;
    }
}