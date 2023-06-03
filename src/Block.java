public class Block {
    private final boolean DEBUG = true;
    private boolean isBomb;
    private boolean isFlagged;
    private boolean isRevealed;
    private int x;
    private int y;
    private int bombNeibour;
    private Block[] Neibours = new Block[9];
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

    public void setFlagged(boolean flag){
        isFlagged = flag;
        //redraw block
    }

    public boolean reveal(){
        if(isRevealed){
            return isBomb;
        }
        isRevealed = true;
        //redraw block
        if(isBomb == false) {
            if (bombNeibour == 0) {
                for (int i = 0; Neibours[i] != null; i++)
                    Neibours[i].reveal();
            }
        }
        return isBomb;
    }

    public void setNeibours(Block[] N){
        for(int i = 0; i < N.length;i++){
            Neibours[i] = N[i];
        }
        neigbourBombCount();
    }
    private void neigbourBombCount(){
        bombNeibour = 0;
        for(int i = 0; ((Neibours[i] != null)&(i<8)); i++){
            if(Neibours[i].isBomb()){
                bombNeibour++;
            }
        }
    }

    public void printstate(){
        if(isFlagged){
            System.out.print('f');
        } else if (isRevealed) {
            if(isBomb){
                System.out.print('B');
            } else {
                System.out.print(bombNeibour);
            }
        } else {
            System.out.print('h');
        }
        System.out.print(' ');
    }
}
