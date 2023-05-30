public class Block {
    private final boolean DEBUG = true;
    private boolean isBomb;
    private boolean isFlagged;
    private boolean isRevealed;
    private int x;
    private int y;

    Block(int x, int y, boolean Bomb) {
        this.isBomb = Bomb;
        this.isRevealed = false;
        this.isFlagged = false;
        this.x = x;
        this.y = y;
        if(DEBUG) {
            System.out.printf("(%d,", x);
            System.out.printf("%d):", y);
            System.out.printf("%b  ", isBomb);
        }
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }

    public void reveal() {
        isRevealed = true;
        //redraw block
        //return isBomb;
    }
}
