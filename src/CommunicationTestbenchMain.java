import communication.CommunicationHandler;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CommunicationHandler communicationHandler1 = new CommunicationHandler();
        CommunicationHandler communicationHandler2 = new CommunicationHandler();
        communicationHandler1.hostGame();
        communicationHandler2.joinGame("localhost");

        while(communicationHandler1.activeConnection==null){}

        communicationHandler1.sendStartGame(1);

        try{Thread.sleep(1000);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        communicationHandler2.sendClickData(1);

        try{Thread.sleep(1000);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        communicationHandler1.sendClickData(1);


        try{Thread.sleep(1000);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        communicationHandler2.sendClickData(1);


        try{Thread.sleep(1000);}
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        communicationHandler1.stopAllCommunication();
    }
}