/** This interface contains AI framework.
 *  @author Ben Zimmerman
 *  */
public interface AI {

    /** Strategy for placing ships */
    void placeShips();

    /** Strategy for making moves */
    String makeMove();

    /** Returns the AI's board */
    Board getBoard();
}
