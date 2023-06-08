import java.util.Random;

public class GameBoard {
    final boolean DEBUG = true;
    private final int MAX_X = 30;
    private final int MAX_Y = 16;
    private final int MIN_X = 8;
    private final int MIN_Y = 8;
    private final int MAXiteration = 1000;
    private int xSize = 8;
    private int ySize = 8;
    private int BombCount = 0;
    private Random rand = new Random();
    //reference table
    private static MinesweeperGUI minesweeperGUI;
    Block[][] board;

    GameBoard() {
        System.out.printf("Gameboard is created.\n");
    }

    private int xSet(int x) {
        int err = 0;
        xSize = x;
        if (xSize > MAX_X) {
            //xSize too big setting it to maximum size
            if (DEBUG) {
                System.out.println("Too big x size.\n");
            }
            xSize = MAX_X;
            err = 1;
        }
        if (xSize < MIN_X) {
            //xSize too small setting it to minimum size
            if (DEBUG) {
                System.out.println("Too small x size.\n");
            }
            xSize = MIN_X;
            err = 1;
        }
        return err;
    }

    private int ySet(int y) {
        int err = 0;
        ySize = y;
        if (ySize > MAX_Y) {
            //ySize too big setting it to maximum size
            if (DEBUG) {
                System.out.println("Too big y size.\n");
            }
            ySize = MAX_Y;
            err = 1;
        }
        if (ySize < MIN_Y) {
            //xSize too small setting it to minimum size
            if (DEBUG) {
                System.out.println("Too small y size.\n");
            }
            ySize = MIN_Y;
            err = 1;
        }
        return err;
    }

    private int bombSet(int b) {
        int err = 0;
        BombCount = b;
        if (BombCount > (MAX_X - 1) * (MAX_Y - 1)) {
            //BombCount too big setting it to maximum
            if (DEBUG) {
                System.out.printf("Too big BombCount.\n");
            }
            BombCount = ((MAX_X - 1) * (MAX_Y - 1));
            err = 1;
        }
        if (BombCount < 1) {
            //BombCount too small setting it to minimum
            if (DEBUG) {
                System.out.println("Too small BombCount.\n");
            }
            BombCount = 1;
            err = 1;
        }
        return err;
    }

    public int setBoardSize(int x, int y, int BC) {
        int err = 0;
        err = err + xSet(x);
        err = err + ySet(y);
        err = err + bombSet(BC);
        return err;
    }

    public int generateBoard() {
        int err = 0;
        boolean[][] bombmap = new boolean[xSize][ySize];
        //generate bomb map
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                bombmap[i][j] = false;
            }

        }

        for (int i = 0; i < BombCount; i++) {
            boolean bombset = false;
            int x = 0;
            int y = 0;
            int iteration = 0;
            while (bombset == false) {
                x = rand.nextInt(xSize);
                y = rand.nextInt(ySize);
                if (bombmap[x][y] == false) {
                    bombmap[x][y] = true;
                    bombset = true;
                }
                iteration++;
                if (iteration > MAXiteration) {
                    err = 1;
                    if (DEBUG) {
                        System.out.println("Could not generate board.");
                    }
                    return err;
                }
            }
        }

        board = new Block[xSize][ySize];
        //Set Board Size
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                board[i][j] = new Block(i, j, bombmap[i][j]);
            }
            if (DEBUG) {
                System.out.printf("\n");
            }
        }
        if (DEBUG) {
            System.out.printf("GameBoard is generated.\n");
        }
        neigbourReferences();
        if (DEBUG) {
            System.out.printf("Neigbours are set.\n");
        }
        minesweeperGUI = new MinesweeperGUI(xSize, ySize);
        board[0][0].setGUIref(minesweeperGUI);

        return err;
    }

    private int neigbourReferences(){
        int err = 0;
        int cntr;
        Block[] neigbours = new Block[9];
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                for(cntr = 0; cntr < 8; cntr++){
                    neigbours[cntr] = null;
                }
                cntr = 0;
                for(int dx = -1; dx <= 1; dx++){
                    for(int dy = -1; dy <= 1; dy++){
                        if(((i+dx)>=0)&((i+dx)<xSize)){
                            if(((j+dy)>=0)&((j+dy)<ySize)&((dx!=0)|(dy!=0))){
                                //System.out.printf("cnt: %d, i: %d, j: %d, dx: %d, dy: %d.\n",cntr,i,j,dx,dy);
                                neigbours[cntr] = board[i+dx][j+dy];
                                cntr++;
                            }
                        }
                    }
                }
                //System.out.println("");
                board[i][j].setNeibours(neigbours);
            }
        }
        return err;
    }

    public void printBoard(){
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                board[i][j].printstate();
            }
            System.out.println("");
        }
    }

    public void setBoard(boolean[][] bombarray) {
        board = new Block[xSize][ySize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; i++) {
                board[i][j] = new Block(i, j, bombarray[i][j]);
            }
        }
    }

    public boolean revealXY(int x, int y) {
        boolean bool = board[x][y].reveal();
        isVictory();
        return bool;
    }

    public boolean flagXY(int x, int y) {
        board[x][y].setFlagged(!board[x][y].getFlag());
        return board[x][y].getFlag();
    }
    public int getBombNeibourXY(int x, int y){
        return board[x][y].getBombNeibour();
    }
    public void setGUIref(){
        board[0][0].setGUIref(minesweeperGUI);

    }
    public boolean isVictory(){
        int x,y;
        int revealedcount = 0;
        for(x=0;x<xSize;x++){
            for(y=0;y<ySize;y++){
                if(!board[x][y].isBomb()&board[x][y].isRevealed()){
                    revealedcount++;
                }
            }
        }
        System.out.println(revealedcount+BombCount);
        if(revealedcount+BombCount>=xSize*ySize) {
            minesweeperGUI.victory();
            return true;
        }
        else {
            return false;
        }
    }
    public int getXSize(){
        return xSize;
    }
    public int getYSize(){
        return ySize;
    }
    public boolean[][] getBombMap(){
        boolean[][] bool = new boolean[xSize][ySize];
        for(int x=0; x<xSize;x++){
            for(int y=0; y<ySize;y++){
                bool[x][y] = board[x][y].isBomb();
            }
        }
        return bool;
    }
}
