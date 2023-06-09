//package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;

public class Connection {
    Socket serverSocket;
    ServerReceiveThread receiveThread;
    ServerTransmitThread transmitThread;
    public Connection(Socket serverSocket,
                      ObjectInputStream objectInputStream,
                      ObjectOutputStream objectOutputStream
    ){
        this.serverSocket = serverSocket;
        this.receiveThread = new ServerReceiveThread(objectInputStream, this);
        this.transmitThread = new ServerTransmitThread(objectOutputStream, this);
        this.receiveThread.start();
        this.transmitThread.start();
        System.out.println("Created connection class");
//        this.clientIP = ip;
    }

    public void closeConnection() {
//        main.communicationHandler.joinedIP = null;

        if(this.serverSocket != null) {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.receiveThread.stopServer();

        this.receiveThread = null;
        this.transmitThread = null;
    }

}
