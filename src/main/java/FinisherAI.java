import java.util.ArrayList;

/** This class is essentially an extension of rand AI, except
 * if it scores a hit, it tries to finish off the ship struck.
 *  @author Ben Zimmerman
 *  */
public class FinisherAI implements AI {

    /** AI's board */
    private Board _BOARD;

    /** Used to ensure move validity in makeMove */
    private int[] _MOVELOCS = new int[100];

    /** Keeps track of current ship being focused, empty if no such ship.
     * First element of the list is the row, second is the column, and third is
     * the ship orientation (-1 for unknown, 0 for horizontal, and 1 for vertical).
     * The rest of the list is integers that indicate moves made based off original location.
     */
    private ArrayList<Integer> _FOCUS = new ArrayList<>();


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
        int r = 0;
        int c = 0;
        if (!_FOCUS.isEmpty()) {
            if (_FOCUS.get(2) == -1) {
                r = _FOCUS.get(0);
                c = _FOCUS.get(1);
                int d = (int) (Math.random() * 2);
                _FOCUS.set(2, d);
                if (d == 0) {
                    r--;
                } else {
                    c--;
                }
                _FOCUS.add(-1);
                _FOCUS.add(1);
            } else {
                int d = _FOCUS.get(2);
                int next = _FOCUS.get(_FOCUS.size() - 1);
                if (d == 0) {
                    r += next;
                } else {
                    c += next;
                }
                if (next > 0) {
                    _FOCUS.add(next + 1);
                } else {
                    _FOCUS.add(next - 1);
                }
            }
        } else {
            do {
                r = (int) (Math.random() * 10) + 1;
                c = (int) (Math.random() * 10) + 1;
            } while (_MOVELOCS[(r - 1) * 10 + c] == 1);
            _MOVELOCS[(r - 1) * 10 + c] = 1;
        }
        return (char) (c + 97) + "" + r;
    }

    @Override
    public Board getBoard() {
        return _BOARD;
    }
}
