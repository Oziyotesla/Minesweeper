public class GameBoard {
    final boolean DEBUG = true;
    private final int MAX_X = 30;
    private final int MAX_Y = 16;
    private final int MIN_X = 8;
    private final int MIN_Y = 8;
    private int xSize = 0;
    private int ySize = 0;
    private int BombCount = 0;
    //reference table
    Block board[][];
    GameBoard() {
        System.out.printf("Gameboard is created.\n");
    }
    private int xSet(int x){
        int err = 0;
        xSize = x;
        if(xSize > MAX_X){
            //xSize too big setting it to maximum size
            if(DEBUG) {
                System.out.println("Too big x size.\n");
            }
            xSize = MAX_X;
            err = 1;
        }
        if(xSize < MIN_X){
            //xSize too small setting it to minimum size
            if(DEBUG) {
                System.out.println("Too small x size.\n");
            }
            xSize = MIN_X;
            err = 1;
        }
        return err;
    }
    private int ySet(int y){
        int err = 0;
        ySize = y;
        if(ySize > MAX_Y){
            //ySize too big setting it to maximum size
            if(DEBUG) {
                System.out.println("Too big y size.\n");
            }
            ySize = MAX_Y;
            err = 1;
        }
        if(ySize < MIN_Y){
            //xSize too small setting it to minimum size
            if(DEBUG) {
                System.out.println("Too small y size.\n");
            }
            ySize = MIN_Y;
            err = 1;
        }
        return err;
    }

    private int bombSet(int b){
        int err = 0;
        BombCount = b;
        if(BombCount > (MAX_X-1)*(MAX_Y-1)){
            //BombCount too big setting it to maximum
            if(DEBUG) {
                System.out.printf("Too big BombCount.\n");
            }
            BombCount = ((MAX_X-1)*(MAX_Y-1));
            err = 1;
        }
        if(BombCount < 1){
            //BombCount too small setting it to minimum
            if(DEBUG) {
                System.out.println("Too small BombCount.\n");
            }
            BombCount = 1;
            err = 1;
        }
        return err;
    }
    public int setBoardSize(int x, int y,int BC){
        int err = 0;
        err = err + xSet(x);
        err = err + ySet(y);
        err = err + bombSet(BC);
        return err;
    }
    public void generateBoard(){
        boolean[][] bombmap = new boolean[xSize][ySize];
        //generate bomb map
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++)
            {
                bombmap[i][j] = false;
            }
        }

        Block[][] board = new Block[xSize][ySize];
        //Set Board Size
        for(int i = 0; i < xSize; i++ ){
            for(int j = 0; j < ySize; j++) {
                board[i][j] = new Block(i, j, bombmap[i][j]);
            }
            if(DEBUG){
                System.out.printf("\n");
            }
        }
        if(DEBUG) {
            System.out.printf("GameBoard is generated.\n");
        }
    }
    public void revealXY(int x, int y){
        board[x][y].reveal();
        return;
    }
}
