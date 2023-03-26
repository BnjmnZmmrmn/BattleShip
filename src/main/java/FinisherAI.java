/** This class is essentially an extension of rand AI, except
 * if it scores a hit, it tries to finish off the ship struck.
 *  @author Ben Zimmerman
 *  */
public class FinisherAI extends RandAI{

    /** AI's board */
    private Board _BOARD;

    /** Sets _BOARD */
    public FinisherAI(Board b) {
        super(b);
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
}
