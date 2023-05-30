public class Main {
    public static boolean exit = false;
    private GameBoard board;
    private boolean Multi = false;
    private boolean turn = false;
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        //board.setBoardSize(8,8,10);
        //board.generateBoard();
        //GUI
        GUI gui = new GUI();
        //event handler while loop
        loop();
    }
    private static void loop(){
        while(exit == false){
            //gameloop System.currentTimeMillis() to make timer
            //GUI is there change
            //TCP ip is there change
            //timer
            //board update
            //GUI update
            //tcp ip update
        }
    }
    public void clickBomb(int x, int y){
        boolean clickValid = (Multi == false)|((Multi == true)&(turn == true));
        if(clickValid) {
            board.revealXY(x, y);
        }
    }
    public void clickGame(boolean MultiPlayer,int difficulty){
        Multi = MultiPlayer;
        switch(difficulty) {
            case 1:
                board.setBoardSize(8,8,10);
                break;
            case 2:
                board.setBoardSize(16,16,40);
                break;
            case 3:
                board.setBoardSize(24,16,99);
                break;
            default:
                board.setBoardSize(8,8,10);
                break;
        }
        if(Multi){
            //turn;
        }
        else{
            turn = true;
        }
    }
}