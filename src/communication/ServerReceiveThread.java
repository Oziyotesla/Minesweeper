package communication;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ServerReceiveThread extends Thread {
    private final ObjectInputStream objectInputStream;
    private final Connection connection;
    private boolean isRunning;

    public ServerReceiveThread(ObjectInputStream objectInputStream, Connection connection) {
        this.objectInputStream = objectInputStream;
        this.connection = connection;
        this.isRunning = false;
        System.out.println("Created ServerReceiveThread");
    }

    @Override
    public void run() {
        try {
            while (this.isRunning) {
                try {
                    Message msg = (Message) this.objectInputStream.readObject();

                    switch (msg.type) {
                        case clickData -> {
                            System.out.println("Received click data from client");
//                            main.handleClickData(msg.data); //todo
                        }
                        case stopCommunication -> {
                            System.out.println("Received stop comm from client");
//                            main.communicationHandler.stopAllCommunication();//todo
                        }
                    }
                } catch (SocketException | EOFException e) {
                    if(this.isRunning) {
                        e.printStackTrace();
                    }
                } catch (SocketTimeoutException ignored) {

                }

                synchronized (this) {
                    try {
                        this.wait(1);
                    } catch (InterruptedException e) {
                        this.isRunning = false;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        this.isRunning = false;
    }
    public void startServer() {
        this.isRunning = true;
    }
}
