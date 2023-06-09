//package communication;

//import com.sun.tools.javac.Main;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ClientReceiveThread extends Thread{
    private final ObjectInputStream objectInputStream;
    private boolean isRunning;

    public ClientReceiveThread(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
        this.isRunning = true;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (this.isRunning) {
                try {
                    Message msg = (Message) this.objectInputStream.readObject();

                    switch (msg.type) {
                        //todo check if all related request are handled
                        case startingMinefieldData -> {
                            System.out.println("Received start minefield from server");
                            Main.IPGame((StartData) msg.data);
                        }
                        case clickData -> {
                            System.out.println("Received cilck data from server");
//                            main.handleClickData(msg.data);
                            Main.receiveClick(((ClickData)msg.data).x_cord,((ClickData)msg.data).y_cord,((ClickData)msg.data).flag);
                            //todo handle request
                        }
                        case stopCommunication -> {
                            System.out.println("Received stop comm from server");
//                            main.communicationHandler.stopAllCommunication();
                        }
                    }
                } catch (SocketException e) {
                    if(this.isRunning) {
                        e.printStackTrace();
                    }
                } catch (SocketTimeoutException | EOFException ignored) {

                }

                synchronized (this) {
                    try {
                        this.wait(10);
                    } catch (InterruptedException e) {
                        this.isRunning = false;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stopReceive() {
        this.isRunning = false;
    }
}
