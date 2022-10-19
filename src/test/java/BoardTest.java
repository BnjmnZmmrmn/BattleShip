import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void placeShip() {
        initBoards();
        _TESTBOARD1.placeShip(5, 'w', "a7");
        assertTrue(_TESTBOARD1.getField().equals(BoardTestHelper._PLACE1));
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

    void initBoards() {
        _TESTBOARD1 = new Board(_NAME1);
        _TESTBOARD2 = new Board(_NAME2);
    }
}