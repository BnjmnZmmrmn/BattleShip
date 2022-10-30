public class Game {
    /** The maximum amount of moves for a battleship game. */
    private static final int _MAXMV = 200;

    /** Keeps track of move number, also used to determine whose turn. */
    private static int _MVCNT = 0;

    /** Player one's battleship board. */
    private static Board _BRDONE;

    /** Player two's battleship board. */
    private static Board _BRDTWO;

    /** Used to initialize player boards. */
    private static void initialize(String name1, String name2) {
        _BRDONE = new Board(name1);
        _BRDTWO = new Board(name2);
    }

    /** Places a ship on a board. */
    private static boolean placeShip(Board board, int len, char w, String loc) {
        if (board.canPlace(len, w, loc)) {
            board.placeShip(len, w, loc);
            return true;
        }
        return false;
    }

    /** Resets both boards to after ship placements. */
    private static void resetBoards() {
        _BRDONE.reset();
        _BRDTWO.reset();
    }

    /** Undoes the last move made in the game. */
    private static void undoMove() {
        assert (_MVCNT > 0);
        if (_MVCNT % 2 == 1) {
            _BRDONE.undo();
        } else {
            _BRDTWO.undo();
        }
        _MVCNT--;
    }

    /** Makes a move on a board. */
    private static boolean makeMove(Board board, String move) {
        if (board.canMove(move)) {
            board.move(move);
            return true;
        }
        return false;
    }

    /** Returns true if there is a winner, false otherwise, also. */
    private static boolean isWinner() {
        return !_BRDONE.isAlive() || !_BRDTWO.isAlive();
    }

    /** Handles the inputs of the game, redirects to accessory methods. */
    public static void main(String[] args) {
        
    }
}
