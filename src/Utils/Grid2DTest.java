package Utils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Grid2DTest {


    public static void main(String[] args) {

        ArrayList<String> testFile = new ArrayList<String>();

        testFile.add("ABCDE");
        testFile.add("FGHIJ");
        testFile.add("KLMNO");
        testFile.add("PQRST");
        testFile.add("UVWXY");

        Grid2D grid = new Grid2D(testFile);

        assertEquals(grid.getNumCols(), 5);
        assertEquals(grid.getNumRows(), 5);

        int E = 4;
        int L = 11;
        int Y = 24;

        System.out.println(grid.getCharacterAt(E));
        System.out.println(grid.getCharacterAbove(E));
        System.out.println(grid.getCharacterToRight(E));
        System.out.println(grid.getCharacterBelow(E));
        System.out.println(grid.getCharacterToLeft(E));
        System.out.println("");

        System.out.println(grid.getCharacterAt(L));
        System.out.println(grid.getCharacterAbove(L));
        System.out.println(grid.getCharacterToRight(L));
        System.out.println(grid.getCharacterBelow(L));
        System.out.println(grid.getCharacterToLeft(L));
        System.out.println("");

        System.out.println(grid.getCharacterAt(Y));
        System.out.println(grid.getCharacterAbove(Y));
        System.out.println(grid.getCharacterToRight(Y));
        System.out.println(grid.getCharacterBelow(Y));
        System.out.println(grid.getCharacterToLeft(Y));
        System.out.println("");

        System.out.println(grid.getCharacterAt(0, 0));
        System.out.println(grid.getCharacterAt(2, 3));

        System.out.println(grid.getCharacterAt(6, 3));
    }




}