package base;

/**
 * Created by veininguo on 2017/1/29.
 */
public interface Session {
    enum Status {
        NOT_CONNECTED, CONNECTING, CONNECTED, CLOSED
    }

    Object getId();

    long getCreationTime();

    long getLastReadWriteTime();

    void setStatus(Status status);

    Status getStatus();

    boolean isConnected();

    boolean isClosed();

    void close();
}