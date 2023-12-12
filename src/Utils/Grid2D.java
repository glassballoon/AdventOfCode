package Utils;

import java.util.ArrayList;
import java.util.List;

public class Grid2D {

    private ArrayList<Character> grid = new ArrayList<Character>();
    private int numRows;
    private int numColumns;

    public Grid2D() {

    }

    public ArrayList<Character> getGrid() {
        return grid;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public void loadFile(List<String> lines){
        numRows = lines.size();
        numColumns = lines.get(0).length();

        //let's pop everything into a huge ArrayList
        var allChars = new ArrayList<Character>();
        for (var line: lines){
            for (var c: line.toCharArray()){
                grid.add(c);
            }
            allChars.addAll(grid);
        }
    }

    /* return the character at a certain column and row location */
    //@TODO - set up some out of bounds handling
    public Character getCharacterAt(int row, int col){
        if (row > numRows) return null;
        if (col > numColumns) return null;
        return grid.get((row * numRows) + col);
    }

    public Character getCharacterAt(int index){
        return grid.get(index);
    }

    public int getRowNumber(int index){
        return (int) Math.floor(index/numRows);
    }

    public int getColumnNumber(int index){
        return index%numColumns;
    }

    public Character getCharacterAbove(int index){
        // get row number
        if (getRowNumber(index) != 0)
            return grid.get(index-numColumns);
        else
            return null;
    }

    public Character getCharacterToRight(int index){
        if (getColumnNumber(index) != numColumns-1)
            return grid.get(index+1);
        else
            return null;
    }

    public Character getCharacterBelow(int index){
        // get row number
        if (getRowNumber(index) != numRows-1)
            return grid.get(index+numColumns);
        else
            return null;
    }

    public Character getCharacterToLeft(int index){
        if (getColumnNumber(index) != 0)
            return grid.get(index-1);
        else
            return null;
    }

    public ArrayList<Character> getCharactersInRow(int row){
        int startingIndex = row*numColumns;
        var rowArray = new ArrayList<Character>();
        for (int i = startingIndex; i < startingIndex + numColumns; i++){
            rowArray.add(grid.get(i));
        }
        return rowArray;
    }

    public ArrayList<Character> getCharactersInColumn(int column){
        var columnArray = new ArrayList<Character>();
        for (int i = column; i < grid.size(); i+=numColumns){
            columnArray.add(grid.get(i));
        }
        return columnArray;
    }
}
