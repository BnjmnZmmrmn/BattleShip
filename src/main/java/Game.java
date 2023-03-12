import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

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
        System.out.println("Do you need to hear the placement rules? (Y/N)");
        String ans = sc.nextLine();
        if (ans.equals("Y")) {
            System.out.println("This game uses grid coordinates where a1 is the" +
                    " top left corner and j10 is the bottom right corner.  The letter" +
                    " corresponds to the column and the number corresponds to the row.");
            Thread.sleep(3000);
            System.out.println("You will now be prompted to place ships.  The format for" +
                    " ship placement should be: <ship length> <direction> <coordinate>." +
                    "  Direction indicates which way a ship will point once you place it," +
                    " indicated by WASD.");
            Thread.sleep(3000);
        }
        int[] p1Ships = {5, 4, 3, 3, 2};
        int[] p2Ships = {5, 4, 3, 3, 2};
        System.out.println(_BRDONE.getName() + ", its your turn to place ships.");
        while (p1Ships.length != 0) {
            _BRDONE.printBoard();
            System.out.print("You have: ");
            for (int i = 0; i < p1Ships.length; i ++) {
                System.out.print(p1Ships[i]);
                if (i != p1Ships.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(".  Please place one:");
            String[] cmmnd = sc.nextLine().split(" ");
            boolean noError = false;
            while (!noError) {
                try {
                    Integer.parseInt(cmmnd[0]);
                    noError = true;
                } catch (Error error) {
                    System.out.println("Incorrect format for placement.  Please try again.");
                }
            }
            if (cmmnd.length != 3 || !simpleContains(Integer.parseInt(cmmnd[0]), p1Ships)) {
                System.out.println("Incorrect format for placement.  Please try again.");
            } else {
                if (!_BRDONE.canPlace(Integer.parseInt(cmmnd[0]), cmmnd[1].charAt(0), cmmnd[2])) {
                    System.out.println("Could not place ship.  Please try again.");
                } else {
                    _BRDONE.placeShip(Integer.parseInt(cmmnd[0]), cmmnd[1].charAt(0), cmmnd[2]);
                    int[] new_ships = new int[p1Ships.length - 1];
                    int c = 0;
                    for (int i = 0; i < p1Ships.length - 1; i++) {
                        if (c != 1 && p1Ships[i] == Integer.parseInt(cmmnd[0])) {
                            c = 1;
                        }
                        new_ships[i] = p1Ships[i + c];
                    }
                    p1Ships = new_ships;
                }
            }
        }
        Thread.sleep(1000);
        System.out.println(_BRDTWO.getName() + ", its your turn to place ships.");
        while (p2Ships.length != 0) {
            _BRDTWO.printBoard();
            System.out.print("You have: ");
            for (int i = 0; i < p2Ships.length; i ++) {
                System.out.print(p2Ships[i]);
                if (i != p2Ships.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(".  Please place one:");
            String[] cmmnd = sc.nextLine().split(" ");
            boolean noError = false;
            while (!noError) {
                try {
                    Integer.parseInt(cmmnd[0]);
                    noError = true;
                } catch (Error error) {
                    System.out.println("Incorrect format for placement.  Please try again.");
                }
            }
            if (cmmnd.length != 3 || !simpleContains(Integer.parseInt(cmmnd[0]), p2Ships)) {
                System.out.println("Incorrect format for placement.  Please try again.");
            } else {
                if (!_BRDTWO.canPlace(Integer.parseInt(cmmnd[0]), cmmnd[1].charAt(0), cmmnd[2])) {
                    System.out.println("Could not place ship.  Please try again.");
                } else {
                    _BRDTWO.placeShip(Integer.parseInt(cmmnd[0]), cmmnd[1].charAt(0), cmmnd[2]);
                    int[] new_ships = new int[p2Ships.length - 1];
                    int c = 0;
                    for (int i = 0; i < p2Ships.length - 1; i++) {
                        if (c != 1 && p2Ships[i] == Integer.parseInt(cmmnd[0])) {
                            c = 1;
                        }
                        new_ships[i] = p2Ships[i + c];
                    }
                    p2Ships = new_ships;
                }
            }
        }
        Thread.sleep(1000);
        System.out.println("All ships are placed.  Do you need to hear the move rules? (Y/N)");
        ans = sc.nextLine();
        if (ans.equals("Y")) {
            System.out.println("To attack a square, simply enter that square in grid coordinates.");
            System.out.println("Additionally, typing \"h\" will show you the attacks you've made on the enemy board" +
                    " and typing \"b\" will show you your own board.");
            System.out.println("Typing \"r\" will reset all moves and \"u\" will undo a move.");
            Thread.sleep(1000);
        }
        int turn = 0;
        String command;
        while (_BRDONE.isAlive() && _BRDTWO.isAlive()) {
            if (turn % 2 == 0) {
                System.out.println("Its " + _BRDONE.getName() + "'s turn.  Please enter your move or any other commands:");
                command = sc.nextLine();
                while (command.length() == 1) {
                    if (command.equals("h")) {
                        _BRDTWO.printHits();
                    } else if (command.equals("b")) {
                        _BRDONE.printBoard();
                    } else if (command.equals("r")) {
                        _BRDONE.reset();
                        _BRDTWO.reset();
                        turn = 0;
                    } else if (command.equals("u")) {
                        _BRDONE.undo();
                        turn --;
                    } else {
                        System.out.println("Invalid command.");
                    }
                    System.out.println("Please enter next command:");
                    command = sc.nextLine();
                }
                if(!_BRDONE.canMove(command)) {
                    System.out.println("Invalid command.");
                } else {
                    _BRDONE.move(command);
                    turn ++;
                }
            } else {
                System.out.println("Its " + _BRDTWO.getName() + "'s turn.  Please enter your move or any other commands:");
                command = sc.nextLine();
                while (command.length() == 1) {
                    if (command.equals("h")) {
                        _BRDONE.printHits();
                    } else if (command.equals("b")) {
                        _BRDTWO.printBoard();
                    } else if (command.equals("r")) {
                        _BRDTWO.reset();
                        _BRDONE.reset();
                        turn = 0;
                    } else if (command.equals("u")) {
                        _BRDTWO.undo();
                        turn --;
                    } else {
                        System.out.println("Invalid command.");
                    }
                    System.out.println("Please enter next command:");
                    command = sc.nextLine();
                }
                if(!_BRDTWO.canMove(command)) {
                    System.out.println("Invalid command.");
                } else {
                    _BRDTWO.move(command);
                    turn ++;
                }
            }
        }
        if (_BRDONE.isAlive()) {
            System.out.println("Congrats " + _BRDONE.getName() + ", you win!");
        } else {
            System.out.println("Congrats " + _BRDTWO.getName() + ", you win!");
        }
    }
}
