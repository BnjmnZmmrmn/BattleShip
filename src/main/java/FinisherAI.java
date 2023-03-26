/** This class is essentially an extension of rand AI, except
 * if it scores a hit, it tries to finish off the ship struck.
 *  @author Ben Zimmerman
 *  */
public class FinisherAI implements AI {

    /** AI's board */
    private Board _BOARD;

    /** Sets _BOARD */
    public FinisherAI(Board b) {
        _BOARD = b;
    }

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

    /** Makes moves randomly until it hits a ship, which it then
     * finishes off.
     */
    @Override
    public String makeMove() {
        int r = (int)(Math.random() * 10) + 1;
        int c = (int)(Math.random() * 10) + 1;
        return c + "" + r;
    }

    @Override
    public Board getBoard() {
        return _BOARD;
    }
}
