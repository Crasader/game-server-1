package server;

import java.net.InetSocketAddress;

/**
 * Created by veininguo on 2017/1/29.
 */
public interface NetworkServer {
    TransmissionProtocol getTransmissionProtocol();

    void startServer() throws Exception;

    void startServer(int port) throws Exception;

    void startServer(InetSocketAddress socketAddress) throws Exception;

    void stopServer() throws Exception;

    InetSocketAddress getSocketAddress();
}