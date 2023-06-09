//package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JoinReqHandler extends Thread{
    ServerSocket serverSocket = null;
    CommunicationHandler communicationHandler = null;
    private boolean isRunning;

    public JoinReqHandler(CommunicationHandler communicationHandler) {
        this.communicationHandler = communicationHandler;
        this.isRunning = true;
    }

    @Override
    public void run() {
        Socket serverSocket = null;
        this.isRunning = true;

        try {
            this.serverSocket = new ServerSocket(CommunicationHandler.Port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (this.isRunning) {
            System.out.println("Wait for someone to connect to server");
            try {
                serverSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(this.isRunning) {
                    e.printStackTrace();
                }
            }

            if(serverSocket != null) {
                new CreateConnection(serverSocket, communicationHandler);
                System.out.println("Stop Join Req handler");
                System.out.println("start Server thread");
                communicationHandler.activeConnection.receiveThread.startServer();
                stopListening();
            }
        }
    }

    public void stopListening() {
        this.isRunning = false;
        if(this.serverSocket != null) {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
