import java.util.Stack;

/** This class contains the players ships and tracks hits,
 *  interfacing with Game.java.
 *  @author Ben Zimmerman
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
    private String _PLYRNAME;

    /** Amount of hits on the board */
    private int _HITS = 0;

    /** A list of moves made to this board. */
    private Stack<String> _MVLIST = new Stack<>();

    /** Board constructor.  Stores player name, handles an
     *  ArrayList of ship placements and puts the ships on
     *  the board.
     *  @param playerName
     *  */
    public Board(String playerName) {
        assert (!playerName.equals(""));
        _PLYRNAME = playerName;
        _FIELD = new char[_HEIGHT][_WIDTH];
        for (int r = 0; r < _HEIGHT; r++) {
            for (int c = 0; c < _WIDTH; c++) {
                _FIELD[r][c] = '-';
            }
        }
    }

    /** Returns the player name associated with the board. */
    public String getName() {
        return _PLYRNAME;
    }

    /** Checks if a ship can be placed.
     * @param len
     * @param dir
     * @param loc
     */
    public boolean canPlace(int len, char dir, String loc) {
        assert (len <= 5 && len >= 1);
        assert (dir == 'w' || dir == 'a' || dir == 's' || dir == 'd');
        int r = Integer.parseInt(loc.substring(1)) - 1;
        int c = loc.charAt(0) - 97;
        assert (r >= 0 && r <= 9 && c >= 0 && c <= 9);
        try {
            switch (dir) {
                case 'w':
                    for (int i = 0; i < len; i++) {
                        if (_FIELD[r - i][c] != '-') {
                            return false;
                        }
                    }
                    return true;
                case 'a':
                    for (int i = 0; i < len; i++) {
                        if (_FIELD[r][c - i] != '-') {
                            return false;
                        }
                    }
                    return true;
                case 's':
                    for (int i = 0; i < len; i++) {
                        if (_FIELD[r + i][c] != '-') {
                            return false;
                        }
                    }
                    return true;
                case 'd':
                    for (int i = 0; i < len; i++) {
                        if (_FIELD[r][c + i] != '-') {
                            return false;
                        }
                    }
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /** Places a ship on the board of a specific length len
     *  in a direction dir at square loc.
     * @param len
     * @param dir
     * @param loc
     */
    public void placeShip(int len, char dir, String loc) {
        assert (len <= 5 && len >= 1);
        assert (dir == 'w' || dir == 'a' || dir == 's' || dir == 'd');
        int r = Integer.parseInt(loc.substring(1)) - 1;
        int c = loc.charAt(0) - 97;
        assert (r >= 0 && r <= 9 && c >= 0 && c <= 9);
        switch (dir) {
            case 'w':
                for (int i = 0; i < len; i++) {
                    _FIELD[r - i][c] = 'X';
                }
                return;
            case 'a':
                for (int i = 0; i < len; i++) {
                    _FIELD[r][c - i] = 'X';
                }
                return;
            case 's':
                for (int i = 0; i < len; i++) {
                    _FIELD[r + i][c] = 'X';
                }
                return;
            case 'd':
                for (int i = 0; i < len; i++) {
                    _FIELD[r][c + i] = 'X';
                }
                return;
        }
    }

    /** Checks if a spot is not hit yet.
     * @param move
     */
    public boolean canMove(String move) {
        int r = Integer.parseInt(move.substring(1)) - 1;
        int c = move.charAt(0) - 97;
        assert (r >= 0 && r <= 9 && c >= 0 && c <= 9);
        if (_FIELD[r][c] == 'O' || _FIELD[r][c] == '*') {
            return false;
        }
        return true;
    }

    /** Places a shot on the board.
     * @param move
     */
    public void move(String move) {
        int r = Integer.parseInt(move.substring(1)) - 1;
        int c = move.charAt(0) - 97;
        assert (r >= 0 && r <= 9 && c >= 0 && c <= 9);
        if (_FIELD[r][c] == 'X') {
            _FIELD[r][c] = '*';
            _HITS++;
        } else {
            _FIELD[r][c] = 'O';
        }
        _MVLIST.push(move);
    }

    /** Returns true if any boats are still alive, false otherwise. */
    public boolean isAlive() {
        return _HITS < 17;
    }

    /** Used for debugging purposes only. */
    public char[][] getField() {
        return _FIELD;
    }

    /** Prints out a textual rendition of the board
     * according to '-' denotes empty space, 'O' denotes
     * a miss, '*' denotes a hit, and 'X' denotes a ship.
     * Notably, this is the users perspective, showing all boats.
     */
    public void printBoard() {
        for (int r = 0; r < _HEIGHT; r++) {
            for (int c = 0; c < _WIDTH; c++) {
                System.out.print(_FIELD[r][c] + " ");
                if (c == _WIDTH - 1) {
                    System.out.println();
                }
            }
        }
    }

    /** Prints out a textual rendition of the board
     * according to '-' denotes empty space, 'O' denotes
     * a miss, and '*' denotes a hit.  Notably, this is a
     * ship-less because this view is for the other player.
     */
    public void printHits() {
        for (int r = 0; r < _HEIGHT; r++) {
            for (int c = 0; c < _WIDTH; c++) {
                if (_FIELD[r][c] != 'X') {
                    System.out.print(_FIELD[r][c] + " ");
                } else {
                    System.out.print("- ");
                }
                if (c != _WIDTH - 1) {
                    System.out.println(" ");
                }
            }
        }
    }

    /** This reverts the last shot placed on the board. */
    public void undo() {
        if (!_MVLIST.peek().equals(null)) {
            String move = _MVLIST.pop();
            int r = Integer.parseInt(move.substring(1));
            int c = move.charAt(0) - 97;
            if (_FIELD[r][c] == '*') {
                _FIELD[r][c] = 'X';
            } else {
                _FIELD[r][c] = '-';
            }
        }
    }

    /** This resets the board to before ships were placed. */
    public void reset()  {
        while (!_MVLIST.peek().equals(null)) {
            undo();
        }
    }
}
