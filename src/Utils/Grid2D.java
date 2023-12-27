package Utils;

import java.util.ArrayList;
import java.util.List;

public class Grid2D {

    protected ArrayList<Character> grid = new ArrayList<Character>();
    protected int numRows;
    protected int numColumns;

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

    public int getSize() {
        return grid.size();
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public void loadGrid(List<String> lines){
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

    public Character getCharacterAt(int index){
        return grid.get(index);
    }

    /* return the character at a certain column and row location */
    //@TODO - set up some out of bounds handling
    public Character getCharacterAt(int row, int col){
        if (row > numRows) return null;
        if (col > numColumns) return null;
        return grid.get((row * numRows) + col);
    }

    public void setCharacterAt(int index, Character c){
        grid.set(index, c);
    }

    public void setCharacterAt(Character c, int row, int col){
        setCharacterAt((row * numColumns) + col, c);
    }

    public int getRowNumber(int index){
        return (int) Math.floor(index/numRows);
    }

    public int getColumnNumber(int index){
        return index%numColumns;
    }

    public int getLocationAbove(int index){
        if (getRowNumber(index) != 0)
            return index-numColumns;
        else return -1;
    }

    public Character getCharacterAbove(int index){
        // get row number
        if (getLocationAbove(index) == -1) return null;
        else return grid.get(getLocationAbove(index));
    }

    public int getLocationToRight(int index){
        if (getColumnNumber(index) != numColumns-1)
            return index+1;
        else return -1;
    }

    public Character getCharacterToRight(int index){
        if (getLocationToRight(index) == -1) return null;
        else return grid.get(getLocationToRight(index));
    }

    public int getLocationBelow(int index){
        if (getRowNumber(index) != numRows-1)
            return index+numColumns;
        else return -1;
    }

    public Character getCharacterBelow(int index){
        if (getLocationBelow(index) == -1) return null;
        // get row number
        else return grid.get(getLocationBelow(index));
    }

    public int getLocationToLeft(int index){
        if (getColumnNumber(index) != 0)
            return index-1;
        else return -1;
    }

    public Character getCharacterToLeft(int index){
        if (getLocationToLeft(index) == -1) return null;
        else return grid.get(getLocationToLeft(index));
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (var c: grid)
            sb.append(c);

        return sb.toString();
    }
}
