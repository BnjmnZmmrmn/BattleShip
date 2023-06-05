/** This interface contains AI framework.
 *  @author Ben Zimmerman
 *  */
public interface AI {

    /** Strategy for placing ships */
    void placeShips();

    /** Strategy for making moves */
    String makeMove();

    /** Undoes a move */
    void undo();

    /** Resets the board */
    void reset();

    /** Returns the AI's board */
    Board getBoard();
}
