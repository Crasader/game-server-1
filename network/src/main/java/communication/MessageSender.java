package communication;

/**
 * Created by veininguo on 2017/1/30.
 */
public interface MessageSender {
    void sendMessage(Object message);

    void close() throws Exception;
}