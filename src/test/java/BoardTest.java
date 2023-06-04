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
        assertTrue(bEquals(_TESTBOARD1.getField(), TestingAssets._PLACE1));

        _TESTBOARD2.placeShip(5, 's', "c3");
        _TESTBOARD2.placeShip(4, 'd', "e4");
        _TESTBOARD2.placeShip(3, 'w', "f9");
        _TESTBOARD2.placeShip(3, 'w', "g8");
        _TESTBOARD2.placeShip(2, 'a', "i9");
        assertTrue(bEquals(_TESTBOARD2.getField(), TestingAssets._PLACE2));
    }

    @Test
    void canMove() {
        initBoards();
        initShips();
        for (char r = 'a'; r <= 'j'; r++) {
            for (int c = 1; c <= 10; c++) {
                assertTrue(_TESTBOARD1.canMove(r + "" + c));
                assertTrue(_TESTBOARD2.canMove(r + "" + c));
            }
        }
        initMove();
        assertFalse(_TESTBOARD1.canMove(("d4")));
        assertFalse(_TESTBOARD1.canMove(("h5")));
        assertFalse(_TESTBOARD1.canMove(("j5")));
        assertTrue(_TESTBOARD1.canMove(("j7")));
        assertTrue(_TESTBOARD1.canMove(("a1")));

        assertFalse(_TESTBOARD2.canMove(("c6")));
        assertFalse(_TESTBOARD2.canMove(("h2")));
        assertFalse(_TESTBOARD2.canMove(("f4")));
        assertTrue(_TESTBOARD2.canMove(("e9")));
        assertTrue(_TESTBOARD2.canMove(("a1")));
    }

    @Test
    void move() {
        initBoards();
        initShips();
        _TESTBOARD1.move("d4");
        _TESTBOARD1.move("e5");
        _TESTBOARD1.move("h5");
        _TESTBOARD1.move("i5");
        _TESTBOARD1.move("j5");
        assertTrue(bEquals(_TESTBOARD1.getField(), TestingAssets._MOVE1));

        _TESTBOARD1.move("g5");
        _TESTBOARD1.move("d9");
        _TESTBOARD1.move("h2");
        _TESTBOARD1.move("i9");
        _TESTBOARD1.move("h9");
        assertTrue(bEquals(_TESTBOARD1.getField(), TestingAssets._MOVE2));

        _TESTBOARD2.move("c3");
        _TESTBOARD2.move("d3");
        _TESTBOARD2.move("c2");
        _TESTBOARD2.move("c4");
        _TESTBOARD2.move("c5");
        assertTrue(bEquals(_TESTBOARD2.getField(), TestingAssets._MOVE3));

        _TESTBOARD2.move("c6");
        _TESTBOARD2.move("c7");
        _TESTBOARD2.move("h2");
        _TESTBOARD2.move("e8");
        _TESTBOARD2.move("f4");
        assertTrue(bEquals(_TESTBOARD2.getField(), TestingAssets._MOVE4));
    }

    @Test
    void isAlive() {
        initBoards();
        initShips();
        initMove();
        assertTrue(_TESTBOARD1.isAlive());
        assertTrue(_TESTBOARD2.isAlive());
        _TESTBOARD1.move("d2");
        _TESTBOARD1.move("e2");
        _TESTBOARD1.move("f2");
        _TESTBOARD1.move("a3");
        _TESTBOARD1.move("a4");
        _TESTBOARD1.move("a5");
        _TESTBOARD1.move("a6");
        _TESTBOARD1.move("a7");
        _TESTBOARD1.move("c7");
        _TESTBOARD1.move("d7");
        _TESTBOARD1.move("e7");
        assertTrue(_TESTBOARD1.isAlive());
        _TESTBOARD1.move("i10");
        assertFalse(_TESTBOARD1.isAlive());
        assertTrue(_TESTBOARD2.isAlive());
        _TESTBOARD2.move("e4");
        _TESTBOARD2.move("g4");
        _TESTBOARD2.move("h4");
        _TESTBOARD2.move("f7");
        _TESTBOARD2.move("f8");
        _TESTBOARD2.move("f9");
        _TESTBOARD2.move("g6");
        _TESTBOARD2.move("g7");
        _TESTBOARD2.move("g8");
        _TESTBOARD2.move("h9");
        assertTrue(_TESTBOARD2.isAlive());
        _TESTBOARD2.move("i9");
        assertFalse(_TESTBOARD1.isAlive());
        assertFalse(_TESTBOARD2.isAlive());
    }
    @Test
    void printBoard() {
        initBoards();
        initShips();
        initMove();
        _TESTBOARD1.printBoard();
        System.out.println();
        _TESTBOARD2.printBoard();
    }

    @Test
    void printHits() {
        initBoards();
        initShips();
        initMove();
        _TESTBOARD1.printHits();
        System.out.println();
        _TESTBOARD2.printHits();
    }

    @Test
    void undo() {
        initBoards();
        initShips();
        initMove();
        _TESTBOARD1.undo();
        assertTrue(bEquals(_TESTBOARD1.getField(), TestingAssets._UNDO1));
        _TESTBOARD1.undo();
        assertTrue(bEquals(_TESTBOARD1.getField(), TestingAssets._UNDO2));
        _TESTBOARD2.undo();
        assertTrue(bEquals(_TESTBOARD2.getField(), TestingAssets._UNDO3));
        _TESTBOARD2.undo();
        assertTrue(bEquals(_TESTBOARD2.getField(), TestingAssets._UNDO4));
    }

    @Test
    void reset() {
        initBoards();
        initShips();
        initMove();
        _TESTBOARD1.reset();
        assertTrue(bEquals(_TESTBOARD1.getField(), TestingAssets._PLACE1));
        _TESTBOARD2.reset();
        assertTrue(bEquals(_TESTBOARD2.getField(), TestingAssets._PLACE2));
    }

    /** Initializes test boards with given names. */
    void initBoards() {
        _TESTBOARD1 = new Board(_NAME1);
        _TESTBOARD2 = new Board(_NAME2);
    }

    /** Adds ships to the test board congruent with BoardTestHelper. */
    void initShips() {
        _TESTBOARD1.placeShip(5, 'w', "a7");
        _TESTBOARD1.placeShip(3, 'a', "f2");
        _TESTBOARD1.placeShip(2, 's', "i9");
        _TESTBOARD1.placeShip(4, 'd', "g5");
        _TESTBOARD1.placeShip(3, 'd', "c7");

        _TESTBOARD2.placeShip(5, 's', "c3");
        _TESTBOARD2.placeShip(4, 'd', "e4");
        _TESTBOARD2.placeShip(3, 'w', "f9");
        _TESTBOARD2.placeShip(3, 'w', "g8");
        _TESTBOARD2.placeShip(2, 'a', "i9");
    }

    /** Makes 10 moves on each board congruent with BoardTestHelper. */
    void initMove() {
        _TESTBOARD1.move("d4");
        _TESTBOARD1.move("e5");
        _TESTBOARD1.move("h5");
        _TESTBOARD1.move("i5");
        _TESTBOARD1.move("j5");

        _TESTBOARD1.move("g5");
        _TESTBOARD1.move("d9");
        _TESTBOARD1.move("h2");
        _TESTBOARD1.move("i9");
        _TESTBOARD1.move("h9");

        _TESTBOARD2.move("c3");
        _TESTBOARD2.move("d3");
        _TESTBOARD2.move("c2");
        _TESTBOARD2.move("c4");
        _TESTBOARD2.move("c5");

        _TESTBOARD2.move("c6");
        _TESTBOARD2.move("c7");
        _TESTBOARD2.move("h2");
        _TESTBOARD2.move("e8");
        _TESTBOARD2.move("f4");
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