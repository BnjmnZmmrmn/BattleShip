import java.util.Arrays;

/** This class is a random AI that makes moves based on
 * no data at all.
 *  @author Ben Zimmerman
 *  */
public class RandAI implements AI {

    /** Board of AI */
    private Board _BOARD;

    /** Used to ensure move validity in makeMove */
    private int[] _MOVELOCS = new int[100];

    /** Sets the board */
    public RandAI(Board b) {
        _BOARD = b;
        for (int i = 0; i < 100; i ++) {
            _MOVELOCS[i] = 0;
        }
    }

    /** Places all ships randomly. */
    @Override
    public void placeShips() {
        int[] ships = {5, 4, 3, 3, 2};
        char[] dirs = {'w', 'a', 's', 'd'};
        for (int i = 0; i < ships.length; i ++) {
            int len = ships[i];
            int dI = (int)(Math.random() * 4);
            int r = (int)(Math.random() * 10) + 1;
            int c = (int)(Math.random() * 10) + 1;
            while(!_BOARD.canPlace(len, dirs[dI], r + "" + c)) {
                r = (int)(Math.random() * 10) + 1;
                c = (int)(Math.random() * 10) + 1;
            }
            _BOARD.move(c + "" + r);
        }
    }

    /** Makes moves randomly, also ensures the move is valid */
    @Override
    public String makeMove() {
        int r;
        int c;
        do {
            r = (int) (Math.random() * 10) + 1;
            c = (int) (Math.random() * 10) + 1;
        } while (_MOVELOCS[(r - 1) * 10 + c] == 1);
        _MOVELOCS[(r - 1) * 10 + c] = 1;
        return (char) (c + 97) + "" + r;
    }

    /** Returns _BOARD */
    @Override
    public Board getBoard() {
        return _BOARD;
    }
}
