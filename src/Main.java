//import communication.CommunicationHandler;
//import communication.StartData;

import java.util.Scanner;

public class Main {
    public static boolean exit = false;
    private static boolean server = false;
    private static final boolean DEBUG = true;
    private static long startTime = 0;
    private static boolean Multi = false;
    private static boolean turn = true;
    private static GameBoard gameboard = null;
    private static int difficulty = 0;
    private static CommunicationHandler communicationHandler = null;
    private static HighScoreTracker ScoreTracker = new HighScoreTracker();
    private static MainMenuWindow mainmenu = null;
    private static StartData startData = null;
    public static TimeData time = new TimeData();
    private static MultiPlayerWindow multiPlayerWindow = null;
    public static void main(String[] args) {
        int err = 0;
        //gameboard = new GameBoard();
        try{Thread.sleep(200);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
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
        //Scanner scanner = new Scanner(System.in);
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
            try{Thread.sleep(1000);}
            catch(InterruptedException ex) {Thread.currentThread().interrupt();}
            //timer
            getTime();
            //System.out.println(System.currentTimeMillis()-startTime);
        }
        //scanner.close();
    }
    public static boolean clickBomb(int x, int y, boolean flag){
        boolean clickValid = (Multi == false)|((Multi == true)&(turn == true));
        if(clickValid) {
            ClickData clickData = new ClickData(x,y,flag);
            if(Multi) {
                communicationHandler.sendClickData(clickData);
            }
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
        gameboard = new GameBoard();
        diffToBoard(difficulty);
        gameboard.generateBoard();
        try{Thread.sleep(1000);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        if(Multi){
            //send board to other player
            startData = new StartData();
            startData.setXsize(gameboard.getXSize());
            startData.setYsize(gameboard.getYSize());
            startData.setBombMap(gameboard.getBombMap());
            communicationHandler.sendStartGame(startData);
            turn = true;
        }
        else{
            turn = true;
        }
        System.out.println("before dispose");
    }

    public static void CreateServer() {
        communicationHandler = new CommunicationHandler();
        communicationHandler.hostGame();
        //while(communicationHandler.activeConnection == null);

    }

    public static void IPGame(StartData startData){
        turn = true;
        Multi = true;
        diffToBoard(startData.getDifficulty());
        //gameboard.setBoardSize(startData.getXsize(),startData.getYsize(),0);
        gameboard.setBoard(startData.getBombMap());
    }
    public static void IPConnect(String IP ){
        communicationHandler = new CommunicationHandler();
        communicationHandler.joinGame(IP);
        gameboard = new GameBoard();
    }

    public static int getBombNeibourXY(int x, int y){
        return gameboard.getBombNeibourXY(x, y);
    }
    public static void setDifficulty(int diff){
        difficulty = diff;
    }

    public static boolean receiveClick(int x, int y, boolean flag) {
        boolean bomb;
        boolean clickValid = (Multi == false)|((Multi == true)&(turn == true));
        if(clickValid) {
            if (flag == false) {
                bomb = gameboard.revealXY(x, y);
                return bomb;
            }
            else
                bomb = gameboard.flagXY(x, y);
                return bomb;
        }
        return false;
    }
    public static void createTimer(){
        startTime = System.currentTimeMillis();
        server = true;
        return;
    }
    public static long getTime(){
        if(server){
            if(Multi) {
                time.Time = System.currentTimeMillis()-startTime;
                communicationHandler.sendTimeData(time);
                System.out.println("Server send time!");
            }
            return System.currentTimeMillis()-startTime;
        }else{
            return startTime;
        }
    }
    public static void receiveTime(TimeData time){
        System.out.println("Client get time!");
        startTime = time.Time;
    }
    public static void setMultiWindowRef(MultiPlayerWindow multiWindowRef){
        multiPlayerWindow = multiWindowRef;
    }
    public static void SetConnected(boolean connected){
        multiPlayerWindow.setConnected(connected);
    }
}