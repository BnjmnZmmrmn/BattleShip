import java.util.ArrayList;
import java.util.Stack;

/** This class contains the players ships and tracks hits,
 *  interfacing with Game.java.
 *  */
public class Board {

    /** Width of the board. */
    private static final int _WIDTH = 10;

    /** Height of the board. */
    private static final int _HEIGHT = 10;

    /** This is how the board is stored.  '-' denotes empty
     *  space, 'O' denotes a miss, '*' denotes a hit, and
     *  'X' denotes a ship.
     *  */
    private char[][] _FIELD;

    /** Stores player name. */
    private static String _PLYRNAME;

    /** True if player has ships remaining, false otherwise. */
    private boolean _ALIVE = true;

    /** A list of moves made to this board. */
    private Stack<String> _MVLIST = new Stack<>();

    /** Board constructor.  Stores player name, handles an
     *  ArrayList of ship placements and puts the ships on
     *  the board.
     *  @param playerName
     *  @param shipPlacements
     *  */
    public Board(String playerName, ArrayList<String[]> shipPlacements) {
        _PLYRNAME = playerName;
        _FIELD = new char[_WIDTH][_HEIGHT];
        for (int r = 0; r < _WIDTH; r++) {
            for (int c = 0; c < _HEIGHT; c++) {
                _FIELD[r][c] = '-';
            }
        }
        for (int i = 0; i < 5; i++) {
            String[] placement = shipPlacements.get(i);
            placeShip(Integer.getInteger(placement[0]), placement[1].charAt(0), placement[2]);
        }
    }

    /** Checks if a ship can be placed.
     * @param len
     * @param dir
     * @param loc
     */
    public boolean canPlace(int len, char dir, String loc) {
        int r = Integer.parseInt(loc.substring(1));
        int c = loc.charAt(0) - 97;
        if (dir == 'w') {
            if (r + 1 < len) {
                return false;
            }
            for (int i = len; i > 0; i--); {
                if (_FIELD[r - len][c] != '-') {
                    return false;
                }
            }
        } else if (dir == 's') {
            if (r + 1 > _HEIGHT - len) {
                return false;
            }
            for (int i = len; i > 0; i--); {
                if (_FIELD[r + len][c] != '-') {
                    return false;
                }
            }
        } else if (dir == 'a') {
            if (c + 1 < len) {
                return false;
            }
            for (int i = len; i > 0; i--); {
                if (_FIELD[r][c - len] != '-') {
                    return false;
                }
            }
        } else if (dir == 'd') {
            if (c + 1 > _WIDTH - len) {
                return false;
            }
            for (int i = len; i > 0; i--); {
                if (_FIELD[r][c + len] != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /** Places a ship on the board of a specific length len
     *  in a direction dir at square loc.
     * @param len
     * @param dir
     * @param loc
     */
    public void placeShip(int len, char dir, String loc) {

    }

    public boolean canMove(String move) {
        return false;
    }

    public void move(String move) {

    }

    public void printBoard() {

    }

    public void printHits() {

    }

    public void undo() {

    }

    public void reset() {

    }
}
