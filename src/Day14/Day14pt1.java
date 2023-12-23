package Day14;

import Utils.Grid2D;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day14pt1 {

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

            int sum = 0;
            var gridChars = grid.getGrid();

            for (int i = 0; i < gridChars.size(); i++){
                if (grid.getCharacterAt(i) == roundedRock) sum += grid.getNumRows() - grid.getRowNumber(i);
            }
            System.out.println("");
            System.out.println(sum);
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
