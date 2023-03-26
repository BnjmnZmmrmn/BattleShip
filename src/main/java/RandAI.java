/** This class is a random AI that makes moves based on
 * no data at all.
 *  @author Ben Zimmerman
 *  */
public class RandAI implements AI {

    /** Board of AI */
    private Board _BOARD;

    /** Sets the board */
    public RandAI(Board b) {
        _BOARD = b;
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

    /** Makes moves randomly. */
    @Override
    public String makeMove() {
        int r = (int)(Math.random() * 10) + 1;
        int c = (int)(Math.random() * 10) + 1;
        return c + "" + r;
    }

    /** Returns _BOARD */
    @Override
    public Board getBoard() {
        return _BOARD;
    }
}
