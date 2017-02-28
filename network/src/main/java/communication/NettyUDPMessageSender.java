package communication;

import base.Session;
import io.netty.channel.socket.DatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SessionRegistryService;

import java.net.SocketAddress;

/**
 * Created by veininguo on 2017/1/30.
 */
public class NettyUDPMessageSender implements MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(NettyUDPMessageSender.class);

    private final SocketAddress remoteAddress;
    private final DatagramChannel channel;
    private final SessionRegistryService<SocketAddress> sessionRegistryService;

    public NettyUDPMessageSender(SocketAddress remoteAddress,
                                 DatagramChannel channel,
                                 SessionRegistryService<SocketAddress> sessionRegistryService) {
        this.remoteAddress = remoteAddress;
        this.channel = channel;
        this.sessionRegistryService = sessionRegistryService;
    }

    @Override
    public void sendMessage(Object object) {
        channel.writeAndFlush(object);
    }

    @Override
    public void close() {
        Session session = sessionRegistryService.getSession(remoteAddress);
        if (sessionRegistryService.removeSession(remoteAddress)) {
            logger.debug("Successfully remove session : {}", session);
        } else {
            logger.trace("No udp session found for address: {}", remoteAddress);
        }
    }

    @Override
    public String toString() {
        String channelId = "UDP Channel with id: ";
        if (null != channel) {
            channelId += channel.id().asLongText();
        } else {
            channelId += "0";
        }
        return "Netty " + channelId + " RemoteAddress: " + remoteAddress;
    }
}
