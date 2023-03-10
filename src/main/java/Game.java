import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    /** The maximum amount of moves for a battleship game. */
    private static final int _MAXMV = 200;

    /** Keeps track of move number, also used to determine whose turn. */
    private static int _MVCNT = 0;

    /** Player one's battleship board. */
    private static Board _BRDONE;

    /** Player two's battleship board. */
    private static Board _BRDTWO;

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

    /** Simple contains method that returns true if an element is contained within
     * an array.  Only used on arrays of size < 5.
     * @param i
     * @param arr
     * @return
     */
    private static boolean simpleContains(int i, int[] arr) {
        for (int a : arr) {
            if (a == i) {
                return true;
            }
        }
        return false;
    }

    /** Handles the inputs of the game, redirects to accessory methods. */
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to BattleShip!");
        System.out.println("What is player one's name? Leave blank for an AI player.");
        String name1 = sc.nextLine();
        if (name1.equals(null)) {
            //Add once AI is made
        } else {
            _BRDONE = new Board(name1);
        }
        System.out.println("What is player two's name? Leave blank for an AI player.");
        String name2 = sc.nextLine();
        if (name2.equals(null)) {
            //Add once AI is made
        } else {
            _BRDTWO = new Board(name2);
        }
        System.out.println("Do you need to hear the rules? (Y/N)");
        String ans = sc.nextLine();
        if (ans.equals("Y")) {
            System.out.println("This game uses grid coordinates where a1 is the" +
                    " top left corner and j10 is the bottom right corner.  The letter" +
                    " corresponds to the column and the number corresponds to the row.");
            Thread.sleep(1000);
            System.out.println("You will now be prompted to place ships.  The format for" +
                    " ship placement should be: <ship length> <direction> <coordinate>." +
                    "  Direction indicates which way a ship will point once you place it," +
                    " indicated by WASD.");
            Thread.sleep(1000);
            System.out.println("From there, players will take turns firing at coordinates." +
                    "  May the best man win!");
            Thread.sleep(1000);
        }
        int[] p1Ships = {5, 4, 3, 3, 2};
        int[] p2Ships = {5, 4, 3, 3, 2};
        System.out.println(_BRDONE.getName() + ", its your turn to place ships.");
        while (p1Ships.length != 0) {
            System.out.println("You have: " + p1Ships.toString() + ".  Please place one:");
            String[] cmmnd = sc.nextLine().split(" ");
            if (cmmnd.length != 3 || !simpleContains(Integer.parseInt(cmmnd[0]), p1Ships)) {
                System.out.println("Incorrect format for placement.  Please try again.");
            } else {
                try {
                    _BRDONE.placeShip(Integer.parseInt(cmmnd[0]), cmmnd[1].charAt(0), cmmnd[2]);
                }
            }
        }
    }
}
