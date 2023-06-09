//import communication.CommunicationHandler;
//import communication.StartData;

import java.util.Scanner;

public class Main {
    public static boolean exit = false;
    private static final boolean DEBUG = true;

    private static boolean Multi = false;
    private static boolean turn = false;
    private static GameBoard gameboard = null;
    private static int difficulty = 0;
    private static CommunicationHandler communicationHandler = null;
    private static HighScoreTracker ScoreTracker = new HighScoreTracker();
    private static MainMenuWindow mainmenu = null;
    private static StartData startData = null;
    public static void main(String[] args) {
        int err = 0;
        gameboard = new GameBoard();
        try{Thread.sleep(200);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        //gameboard.setBoardSize(8,8,10);
        //err = gameboard.generateBoard();
        if(err > 0){
            exit = true;
        }
        //GUI
        mainmenu = new MainMenuWindow();
        //GUI gui = new GUI();
        if(err > 0) {
            exit = true;
        }
        //ScoreTracker.loadScoresFromFile("highscore.txt");
        try{Thread.sleep(200);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
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
            gameboard = new GameBoard();
            try{Thread.sleep(200);}
            catch(InterruptedException ex) {Thread.currentThread().interrupt();}
            //timer
            //board update
            if(gameboard.isVictory()){
            }
            if(Multi){

            }
            //GUI update
            //tcp ip update
        }
        //scanner.close();
    }
    public static boolean clickBomb(int x, int y, boolean flag){
        boolean clickValid = (Multi == false)|((Multi == true)&(turn == true));
        if(clickValid) {
            ClickData clickData = new ClickData(x,y,flag);
            communicationHandler.sendClickData(clickData);
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

    public static void clickGame(boolean MultiPlayer){
        Multi = MultiPlayer;
        diffToBoard(difficulty);
        gameboard.generateBoard();
        try{Thread.sleep(1000);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        if(Multi){
            //send board to other player
            startData = new StartData();
            startData.setXsize(gameboard.getXSize());
            startData.setYsize(gameboard.getYSize());
            System.out.println("after setsize");
            startData.setBombMap(gameboard.getBombMap());
            System.out.println("after getbombmap");
            communicationHandler.sendStartGame(startData);
            turn = true;
        }
        else{
            turn = true;
        }
        System.out.println("before dispose");
        mainmenu.dispose();

    }

    public static void CreateServer() {
        communicationHandler = new CommunicationHandler();
        communicationHandler.hostGame();
        //while(communicationHandler.activeConnection == null);

    }

    public static void IPGame(StartData startData){
        turn = true;
        diffToBoard(startData.getDifficulty());
        //gameboard.setBoardSize(startData.getXsize(),startData.getYsize(),0);
        gameboard.setBoard(startData.getBombMap());
    }
    public static void IPConnect(String IP ){
        communicationHandler = new CommunicationHandler();
        communicationHandler.joinGame(IP);
    }

    public static int getBombNeibourXY(int x, int y){
        return gameboard.getBombNeibourXY(x, y);
    }
    public static void setDifficulty(int diff){
        difficulty = diff;
    }

    public static boolean receiveClick(int x, int y, boolean flag) {
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
}