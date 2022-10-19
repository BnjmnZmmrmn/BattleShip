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
    }

    @Test
    void placeShip() {
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