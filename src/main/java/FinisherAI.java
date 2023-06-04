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

    /** Accessory data for _FOCUS.  Index 0 is current miss count, index
     * 1 is for lowest relative move, index 2 is for highest relative move.
     */
    private int[] _FOCUSINFO = {0, 0, 0};


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
            int d = _FOCUS.get(2);
            r = _FOCUS.get(0);
            c = _FOCUS.get(1);
            if (d == 0) {
                r+= _FOCUS.get(_FOCUS.size() - 1);
            } else {
                c+= _FOCUS.get(_FOCUS.size() - 1);
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

    /** Takes in whether previous move was a hit (true) or miss (false)
     * and performs necessary logic to plan next move.
     * @param move
     * @param hit
     */
    public void processResult(String move, boolean hit) {
        if (hit) {
            if (_FOCUS.isEmpty()) {
                int r = Integer.parseInt(move.substring(1)) - 1;
                int c = move.charAt(0) - 97;
                _FOCUS.add(r);
                _FOCUS.add(c);
                int d = (int) (Math.random() * 2);
                _FOCUS.add(d);
                _FOCUS.add(-1);
                _FOCUSINFO[1]--;
            } else {
                // Add logic to handle if 5 hits were already made stop
                int lastMoveRelative = _FOCUS.get(_FOCUS.size() - 1);
                if (lastMoveRelative > 0) {
                    _FOCUS.add(lastMoveRelative + 1);
                    _FOCUSINFO[2]++;
                } else {
                    _FOCUS.add(lastMoveRelative - 1);
                    _FOCUSINFO[1]--;
                }
            }
        } else if (!_FOCUS.isEmpty()){
            _FOCUSINFO[0]++;
            int currMoveCnt = _FOCUS.size() - 3;
            if (currMoveCnt == 1 || _FOCUSINFO[0] == 3) {
                _FOCUS.add(1);
                _FOCUSINFO[2]++;
            } else if (currMoveCnt == 2 && _FOCUSINFO[0] == 2) {
                int d = _FOCUS.get(2);
                if (d == 1) {
                    _FOCUS.set(2, 0);
                } else {
                    _FOCUS.set(2, 1);
                }
                _FOCUS.add(-1);
                _FOCUSINFO[1] = -1;
                _FOCUSINFO[2] = 0;
            } else if (_FOCUSINFO[0] == 4) {
                _FOCUS = new ArrayList<>();
                _FOCUSINFO[0] = 0;
                _FOCUSINFO[1] = 0;
                _FOCUSINFO[2] = 0;
            } else {
                if (_FOCUS.get(_FOCUS.size() - 1) > 0) {
                    _FOCUS.add(_FOCUSINFO[1] - 1);
                    _FOCUSINFO[1]--;
                } else {
                    _FOCUS.add(_FOCUSINFO[2] + 1);
                    _FOCUSINFO[2]++;
                }
            }
        }
    }

    @Override
    public Board getBoard() {
        return _BOARD;
    }
}
