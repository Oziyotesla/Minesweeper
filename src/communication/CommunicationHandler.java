package communication;

import communication.ClientReceiveThread;
import communication.ClientTransmitThread;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationHandler extends Thread {
//    public static final int roomDiscoveryPort = 9981;
    public static final int Port = 5000;
    private Inet4Address myIP = null;

    private ObjectInputStream clientObjectInputStream;
    private ObjectOutputStream clientObjectOutputStream;
    private ClientReceiveThread clientReceiveThread = null;
    private ClientTransmitThread clientTransmitThread = null;
    private JoinReqHandler joinReqHandler = null;
//    private Inet4Address joinedIP = null;
    public Connection activeConnection = null;
    public CommunicationHandler() {

    }
    public boolean joinGame(String ip) {
        this.stopAllCommunication();
        System.out.println("Joining Game");
        Socket socket;
        try {
            socket = new Socket(ip, CommunicationHandler.Port);
            socket.setSoTimeout(100);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            this.clientObjectOutputStream = new ObjectOutputStream(outputStream);
            this.clientObjectInputStream = new ObjectInputStream(inputStream);

            Message msg = new Message(Message.MessageType.joinRequest,null);// (Inet4Address)  ip);
            Message response = null;
            try {
                this.clientObjectOutputStream.writeObject(msg);
                response = (Message) this.clientObjectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            if(response != null && response.type == Message.MessageType.joinAccepted) {
                this.clientReceiveThread = new ClientReceiveThread(this.clientObjectInputStream);
                this.clientTransmitThread = new ClientTransmitThread(this.clientObjectOutputStream);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void hostGame() {
        this.stopAllCommunication();
        System.out.println("create host");
        this.joinReqHandler = new JoinReqHandler(this);
        this.joinReqHandler.start();
    }

    public void sendStartGame(Object data) {
        System.out.println("send start game");
        Message msg = new Message(Message.MessageType.startingMinefieldData, data);
        try {
            this.activeConnection.transmitThread.sendStartGame(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendClickData(Object data) {
        // todo tiltás amennyiben nem mi jövünk.
        Message msg = new Message(Message.MessageType.clickData, data);
        if(this.clientReceiveThread != null) {
            System.out.println("SEND CLICK FROM CLIENT");
            this.clientTransmitThread.sendMessage(msg);
        } else {
            System.out.println("SEND CLICK FROM SERVER");
            try {
                this.activeConnection.transmitThread.sendClickData(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void stopAllCommunication(){
        System.out.println("stop communication");
        if(this.clientReceiveThread != null) { //send to server that we stop
            Message msg = new Message(Message.MessageType.stopCommunication, null);
            this.clientTransmitThread.sendMessage(msg);
            this.clientReceiveThread.stopReceive();
            this.clientReceiveThread = null;
            this.clientTransmitThread.stopTransmit();
            this.clientTransmitThread = null;
        }


        if(this.activeConnection != null){
            Message msg = new Message(Message.MessageType.stopCommunication, null);
            try {
                this.activeConnection.transmitThread.sendStopCommunication(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.activeConnection.closeConnection();
            this.activeConnection = null;
        }

        if(this.joinReqHandler != null){
            this.joinReqHandler.stopListening();
            this.joinReqHandler = null;
        }

    }
}
