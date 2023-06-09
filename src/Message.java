//package communication;

import java.io.Serializable;

public class Message implements Serializable {
    public enum MessageType {
        joinRequest,
        joinAccepted,
        stopCommunication,
        startingMinefieldData,
        clickData,
    }

    public Object data;
    public MessageType type;
    public final int id;
    public static int counter = 0;

    Message(MessageType type, Object data) {
        this.type = type;
        this.data = data;
        this.id = counter;
        counter++;
    }
}
