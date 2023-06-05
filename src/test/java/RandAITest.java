import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** This class contains tests for RandAI.java as well
 *  as accessory methods to aid in testing.
 * @author Ben Zimmerman
 */
class RandAITest {

    private Board _BOARD1;
    private RandAI _RANDY1;
    private Board _BOARD2 = new Board("Randy2");
    private RandAI _RANDY2 = new RandAI(_BOARD2);

    @Test
    void testPlace() {
        _BOARD1 = new Board("Randy1");
        _RANDY1 = new RandAI(_BOARD1);

        _RANDY1.getBoard().printBoard();
        _RANDY1.placeShips();
        _RANDY1.getBoard().printBoard();

        _BOARD1 = new Board("Randy1");
        _RANDY1 = new RandAI(_BOARD1);

        _RANDY1.getBoard().printBoard();
        _RANDY1.placeShips();
        _RANDY1.getBoard().printBoard();
    }

    @Test
    void testMove() {
        _BOARD1 = new Board("Randy1");
        _RANDY1 = new RandAI(_BOARD1);
        _BOARD2 = new Board("Randy2");
        _RANDY2 = new RandAI(_BOARD2);

        _RANDY1.placeShips();
        _RANDY2.placeShips();

        System.out.println("------ Player 1 ------");
        _RANDY1.getBoard().printBoard();
        System.out.println("------ Player 2 ------");
        _RANDY2.getBoard().printBoard();

        _RANDY2.getBoard().move(_RANDY1.makeMove());
        System.out.println("------ Player 2 ------");
        _RANDY2.getBoard().printBoard();
        _RANDY1.getBoard().move(_RANDY2.makeMove());
        System.out.println("------ Player 1 ------");
        _RANDY1.getBoard().printBoard();

        _RANDY2.getBoard().move(_RANDY1.makeMove());
        System.out.println("------ Player 2 ------");
        _RANDY2.getBoard().printBoard();
        _RANDY1.getBoard().move(_RANDY2.makeMove());
        System.out.println("------ Player 1 ------");
        _RANDY1.getBoard().printBoard();

        _RANDY2.getBoard().move(_RANDY1.makeMove());
        System.out.println("------ Player 2 ------");
        _RANDY2.getBoard().printBoard();
        _RANDY1.getBoard().move(_RANDY2.makeMove());
        System.out.println("------ Player 1 ------");
        _RANDY1.getBoard().printBoard();

        _RANDY2.getBoard().move(_RANDY1.makeMove());
        System.out.println("------ Player 2 ------");
        _RANDY2.getBoard().printBoard();
        _RANDY1.getBoard().move(_RANDY2.makeMove());
        System.out.println("------ Player 1 ------");
        _RANDY1.getBoard().printBoard();
    }
}
