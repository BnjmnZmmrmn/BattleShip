import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** This class contains tests for Board.java as well
 *  as accessory methods to aid in testing.
 * @author Ben Zimmerman
 */
class BoardTest {

    private Board _TESTBOARD1;
    private Board _TESTBOARD2;
    final String _NAME1 = "Ben";
    final String _NAME2 = "Kip";


    @Test
    void getName() {
        initBoards();
        assertTrue(_TESTBOARD1.getName().equals(_NAME1));
        assertTrue(_TESTBOARD2.getName().equals(_NAME2));
    }

    @Test
    void canPlace() {
        initBoards();
        assertFalse(_TESTBOARD1.canPlace(5, 'w', "a3"));
        assertFalse(_TESTBOARD1.canPlace(5, 'a', "c1"));
        assertFalse(_TESTBOARD1.canPlace(5, 's', "f7"));
        assertFalse(_TESTBOARD1.canPlace(5, 'd', "i7"));
        assertTrue(_TESTBOARD1.canPlace(5, 'w', "a7"));
        assertTrue(_TESTBOARD1.canPlace(5, 'a', "i2"));
        assertTrue(_TESTBOARD1.canPlace(5, 's', "f3"));
        assertTrue(_TESTBOARD1.canPlace(5, 'd', "c10"));

        _TESTBOARD1.placeShip(5, 'w', "a7");
        _TESTBOARD1.placeShip(3, 'a', "f2");
        _TESTBOARD1.placeShip(2, 's', "i9");
        _TESTBOARD1.placeShip(4, 'd', "g5");
        _TESTBOARD1.placeShip(3, 'd', "c7");

        assertFalse(_TESTBOARD1.canPlace(5, 'w', "d7"));
        assertFalse(_TESTBOARD1.canPlace(4, 'd', "g9"));
        assertFalse(_TESTBOARD1.canPlace(3, 'a', "c3"));
        assertFalse(_TESTBOARD1.canPlace(3, 's', "d1"));
        assertTrue(_TESTBOARD1.canPlace(5, 'd', "b3"));
        assertTrue(_TESTBOARD1.canPlace(4, 's', "f3"));
        assertTrue(_TESTBOARD1.canPlace(3, 'a', "d6"));
        assertTrue(_TESTBOARD1.canPlace(2, 'w', "b7"));

        _TESTBOARD2.placeShip(5, 's', "c3");
        _TESTBOARD2.placeShip(4, 'd', "e4");
        _TESTBOARD2.placeShip(3, 'w', "f9");
        _TESTBOARD2.placeShip(3, 'w', "g8");
        _TESTBOARD2.placeShip(2, 'a', "i9");

        assertFalse(_TESTBOARD2.canPlace(5, 'a', "e3"));
        assertFalse(_TESTBOARD2.canPlace(4, 'd', "g6"));
        assertFalse(_TESTBOARD2.canPlace(3, 'a', "h6"));
        assertFalse(_TESTBOARD2.canPlace(3, 's', "c1"));
        assertTrue(_TESTBOARD2.canPlace(5, 'w', "d8"));
        assertTrue(_TESTBOARD2.canPlace(4, 'd', "d5"));
        assertTrue(_TESTBOARD2.canPlace(3, 'a', "f6"));
        assertTrue(_TESTBOARD2.canPlace(2, 's', "f5"));
    }

    @Test
    void placeShip() {
        initBoards();
        _TESTBOARD1.placeShip(5, 'w', "a7");
        _TESTBOARD1.placeShip(3, 'a', "f2");
        _TESTBOARD1.placeShip(2, 's', "i9");
        _TESTBOARD1.placeShip(4, 'd', "g5");
        _TESTBOARD1.placeShip(3, 'd', "c7");
        assertTrue(bEquals(_TESTBOARD1.getField(), BoardTestHelper._PLACE1));

        _TESTBOARD2.placeShip(5, 's', "c3");
        _TESTBOARD2.placeShip(4, 'd', "e4");
        _TESTBOARD2.placeShip(3, 'w', "f9");
        _TESTBOARD2.placeShip(3, 'w', "g8");
        _TESTBOARD2.placeShip(2, 'a', "i9");
        assertTrue(bEquals(_TESTBOARD2.getField(), BoardTestHelper._PLACE2));
    }

    @Test
    void canMove() {
    }

    @Test
    void move() {
    }

    @Test
    void printBoard() {
    }

    @Test
    void printHits() {
    }

    @Test
    void undo() {
    }

    @Test
    void reset() {
    }

    /** Initializes test boards with given names. */
    void initBoards() {
        _TESTBOARD1 = new Board(_NAME1);
        _TESTBOARD2 = new Board(_NAME2);
    }

    /** Compares two char[][] datatypes. */
    boolean bEquals(char[][] compare, char[][] to) {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (compare[r][c] != to[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
}