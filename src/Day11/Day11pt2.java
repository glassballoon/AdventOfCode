package Day11;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day11pt2 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day11TestInput.txt");
        Path filePath = Paths.get("src/Input/Day11Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            var grid = new Day11Grid();
            grid.loadGrid(lines);

            var emptyRows = grid.getEmptyRows();
            var emptyColumns = grid.getEmptyColumns();
            var galaxies = grid.getGalaxies();
            var distances = new ArrayList<Long>();

            for (int i = 0; i < galaxies.size(); i++){ // i = firstGalaxy location/index
                for (int j = i; j < galaxies.size(); j++){ // j = secondGalaxy location/index
                    if (i==j) continue;
                    int higherRow = Math.max(grid.getRowNumber(galaxies.get(i)), grid.getRowNumber(galaxies.get(j)));
                    int lowerRow = Math.min(grid.getRowNumber(galaxies.get(i)), grid.getRowNumber(galaxies.get(j)));
                    int higherColumn = Math.max(grid.getColumnNumber(galaxies.get(i)), grid.getColumnNumber(galaxies.get(j)));
                    int lowerColumn = Math.min(grid.getColumnNumber(galaxies.get(i)), grid.getColumnNumber(galaxies.get(j)));
                    long rowsAway = higherRow - lowerRow;
                    // check for any crossing empty rows
                    for (var row: emptyRows){
                        if (row >= lowerRow && row <= higherRow) {
                            rowsAway+=999999;
                        }
                    }
                    long columnsAway = higherColumn - lowerColumn;
                    // check for any crossing empty cols
                    for (var column: emptyColumns){
                        if (column >= lowerColumn && column <= higherColumn) {
                            columnsAway+=999999;
                        }
                    }
                    distances.add(rowsAway + columnsAway);
                }
            }
            long sum = 0;
            for (var d: distances)
                sum +=d;

            System.out.println(sum);
        } catch (Exception e){
            System.out.println(e);
        }
    }


}
