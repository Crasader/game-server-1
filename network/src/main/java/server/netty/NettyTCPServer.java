package server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.TransmissionProtocol;

/**
 * Created by veininguo on 2017/1/30.
 */
public class NettyTCPServer extends AbstractNettyServer {
    private static final Logger LOG = LoggerFactory.getLogger(NettyTCPServer.class);
    private ServerBootstrap serverBootstrap;

    public NettyTCPServer(NettyConfig nettyConfig, ChannelInitializer<?> channelInitializer) {
        super(nettyConfig, channelInitializer);
    }

    @Override
    public void configChannelOption() {
        serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.option(ChannelOption.SO_REUSEADDR, true);
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
    }

    @Override
    public TransmissionProtocol getTransmissionProtocol() {
        return TransmissionProtocol.TCP;
    }

    @Override
    public void startServer() throws Exception {
        try {
            serverBootstrap = new ServerBootstrap();
            this.configChannelOption();

            serverBootstrap.group(getBossGroup(), getWorkerGroup())
                    .channel(NioServerSocketChannel.class)
                    .childHandler(getChannelInitialzer());
            Channel serverChannel = serverBootstrap.bind(getSocketAddress()).sync().channel();
            ALL_CHANNELS.add(serverChannel);
        } catch (Exception e) {
            LOG.error("TCP server start error {}, go to shut down.", e);
            super.stopServer();
            throw e;
        }
    }

    @Override
    public void setChannelInitialzer(ChannelInitializer<?> channelInitialzer) {
        this.channelInitializer = channelInitialzer;
        serverBootstrap.childHandler(channelInitialzer);
    }
}
