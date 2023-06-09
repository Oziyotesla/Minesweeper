import java.util.Scanner;

public class Main {
    public static boolean exit = false;
    private static final boolean DEBUG = true;

    private static boolean Multi = false;
    private static boolean turn = false;
    private static GameBoard gameboard;

    private static HighScoreTracker ScoreTracker = new HighScoreTracker();
    private static MainMenuWindow mainmenu;
    public static void main(String[] args) {
        int err = 0;
        gameboard = new GameBoard();
        //gameboard.setBoardSize(8,8,10);
        //err = gameboard.generateBoard();
        if(err > 0){
            exit = true;
        }
        //GUI
        mainmenu = new MainMenuWindow();
        //GUI gui = new GUI();
        if(err > 0){
            exit = true;
        }
        //IP
        IP ip = new IP();
        if(err > 0){
            exit = true;
        }

        //event handler while loop
        //gameboard.revealXY(0,0);
        //gameboard.printBoard();
        //ScoreTracker.loadScoresFromFile("highscore.txt");
        loop();
    }
    private static void loop(){
        Scanner scanner = new Scanner(System.in);
        while(exit == false){
            if(DEBUG){
                //gameboard.printBoard();
                //System.out.println("X kordinata:");
                //int x = scanner.nextInt();
                //System.out.println("Y koordinata:");
                //int y = scanner.nextInt();
                //if(gameboard.revealXY(x,y))
                {
                //    gameboard.printBoard();
                //    System.out.println("Felrobbantal vege a jateknak");
                //    exit = true;
                }
            }

            //GUI is there change
            //TCP ip is there change
            //timer
            //board update
            //GUI update
            //tcp ip update
        }
        //scanner.close();
    }
    public static boolean clickBomb(int x, int y, boolean flag){
        boolean clickValid = (Multi == false)|((Multi == true)&(turn == true));
        if(clickValid) {
            if (flag == false) {
                return gameboard.revealXY(x, y);
            }
            else
                return gameboard.flagXY(x, y);
        }
        return false;
    }


    private static void diffToBoard(int diff){
        switch(diff) {
            case 1:
                gameboard.setBoardSize(8,8,10);
                break;
            case 2:
                gameboard.setBoardSize(16,16,40);
                break;
            case 3:
                gameboard.setBoardSize(30,16,99);
                break;
            default:
                gameboard.setBoardSize(8,8,10);
                break;
        }
    }

    public static void clickGame(boolean MultiPlayer,int difficulty){
        Multi = MultiPlayer;
        diffToBoard(difficulty);
        gameboard.generateBoard();
        if(Multi){
            //send board to other player
            turn = true;
        }
        else{
            turn = true;
        }
        mainmenu.dispose();
    }

    public void IPGame(int difficulty,boolean[][] bombarray){
        turn = false;
        diffToBoard(difficulty);
        gameboard.setBoard(bombarray);
    }

    public static int getBombNeibourXY(int x, int y){
        return gameboard.getBombNeibourXY(x, y);
    }
}