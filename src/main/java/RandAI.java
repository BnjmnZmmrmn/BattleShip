public class RandAI implements AI {
    @Override
    public void makeMove(Board b) {
        int r = (int)((Math.random() + 1) * 10);
        int c = (int)((Math.random() + 1) * 10);
        while(!b.canMove(c + "" + r)) {
            r = (int)((Math.random() + 1) * 10);
            c = (int)((Math.random() + 1) * 10);
        }
        b.move(c + "" + r);
    }
}
