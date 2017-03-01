package message;

import com.google.protobuf.Message;

/**
 * Created by veininguo on 2017/1/26.
 */
public class ProtoBufMessage {
    private int id;
    private Message data;

    public ProtoBufMessage(int id, Message data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public Message getData() {
        return data;
    }

    @Override
    public String toString() {
        return "id : " + id + "\ncontent : \n" + data;
    }
}
