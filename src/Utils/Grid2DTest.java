package Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Grid2DTest {

    ArrayList<String> testFile = new ArrayList<String>();
    Grid2D grid;
    int E = 4;
    int L = 11;
    int Y = 24;

    public Grid2DTest() {
    }

    @BeforeEach
    public void setUpGrid() {
        testFile.add("ABCDE");
        testFile.add("FGHIJ");
        testFile.add("KLMNO");
        testFile.add("PQRST");
        testFile.add("UVWXY");
        grid = new Grid2D();
        grid.loadFile(testFile);
    }

    @Test
    public void testGrid() {
        System.out.println(grid.getCharactersInRow(3));
        System.out.println(grid.getCharactersInColumn(3));
    }

    @Test
    public void testGetNumCols() {
        assertEquals(grid.getNumCols(), 5);
    }

    @Test
    public void testGetNumRows() {
        assertEquals(grid.getNumRows(), 5);
    }

    @Test
    public void testGetCharacterAt() {
        assertEquals(grid.getCharacterAt(E), 'E');
        assertEquals(grid.getCharacterAt(L), 'L');
        assertEquals(grid.getCharacterAt(Y), 'Y');
        assertEquals(grid.getCharacterAt(0, 0), 'A');
        assertEquals(grid.getCharacterAt(2, 3), 'N');
        assertNull(grid.getCharacterAt(6, 3));
    }

    @Test
    public void testGetCharacterAbove() {
        assertNull(grid.getCharacterAbove(E));
        assertEquals(grid.getCharacterAbove(L), 'G');
        assertEquals(grid.getCharacterAbove(Y), 'T');
    }

    @Test
    public void testGetCharacterToRight() {
        assertNull(grid.getCharacterToRight(E));
        assertEquals(grid.getCharacterToRight(L), 'M');
        assertNull(grid.getCharacterToRight(Y));
    }

    @Test
    public void testGetCharacterBelow() {
        assertEquals(grid.getCharacterBelow(E), 'J');
        assertEquals(grid.getCharacterBelow(L), 'Q');
        assertNull(grid.getCharacterBelow(Y));
    }
    @Test
    public void testGetCharacterToLeft() {
        assertEquals(grid.getCharacterToLeft(E), 'D');
        assertEquals(grid.getCharacterToLeft(L), 'K');
        assertEquals(grid.getCharacterToLeft(Y), 'X');
    }
}