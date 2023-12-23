package Day11;

import Utils.Grid2D;

import java.util.ArrayList;
import java.util.List;

public class Day11Grid extends Grid2D {

    char emptySpace = '.';
    char galaxy = '#';

    ArrayList<Integer> galaxies = new ArrayList<Integer>();

    public ArrayList<Integer> getGalaxies() {
        return galaxies;
    }

    public Day11Grid() {
        super();
    }

    @Override
    public void loadGrid(List<String> lines){
        setNumRows(lines.size());
        setNumColumns(lines.get(0).length());

        //let's pop everything into a huge ArrayList
        for (var line: lines){
            for (var c: line.toCharArray()){
                getGrid().add(c);
                if (c == galaxy) galaxies.add(getGrid().size()-1);
            }
        }
    }

    public ArrayList<Integer> getEmptyRows(){
        var emptyRows = new ArrayList<Integer>();
        for (int i = 0; i<getNumRows(); i++){
            var row = getCharactersInRow(i);
            if (row.stream().filter(c -> c.equals(emptySpace)).toList().size() == row.size())
                emptyRows.add(i);
        }
        return emptyRows;
    }

    public ArrayList<Integer> getEmptyColumns(){
        var emptyColumns = new ArrayList<Integer>();
        for (int i = 0; i < getNumCols(); i++) {
            var column = getCharactersInColumn(i);
            if (column.stream().filter(c -> c.equals(emptySpace)).toList().size() == column.size())
                emptyColumns.add(i);
        }
        return emptyColumns;
    }

}
