package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ServerTransmitThread extends Thread {
    private ObjectOutputStream objectOutputStream;
    private Connection connection;
    public ServerTransmitThread(ObjectOutputStream objectOutputStream, Connection connection) {
        this.objectOutputStream = objectOutputStream;
        this.connection = connection;
        System.out.println("Created ServerTransmitThread");
    }
    public void sendStartGame(Message msg) throws IOException {
        this.objectOutputStream.writeObject(msg);
    }
    public void sendClickData(Message msg) throws IOException {
        this.objectOutputStream.writeObject(msg);
    }
    public void sendStopCommunication(Message msg) throws IOException {
        this.objectOutputStream.writeObject(msg);
    }
}
