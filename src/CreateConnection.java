//package communication;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;

public class CreateConnection {
    public CreateConnection(Socket serverSocket, CommunicationHandler communicationHandler) {
        Message response;
        try {

            System.out.println("try create connect class");
            InputStream inputStream = serverSocket.getInputStream();
            OutputStream outputStream = serverSocket.getOutputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            Message msg = (Message) objectInputStream.readObject();
            System.out.println("managed to read msg");
            if (msg.type == Message.MessageType.joinRequest) {
                response = new Message(Message.MessageType.joinAccepted, null);
                objectOutputStream.writeObject(response);
                System.out.println("Try to creating the new Connection class");
                communicationHandler.activeConnection = new Connection(serverSocket, objectInputStream, objectOutputStream);
                Main.SetConnected(true);
            } else {
                System.out.println("Received unexpected message type");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("failed to create Connection class");
            e.printStackTrace();
        }
    }
}
