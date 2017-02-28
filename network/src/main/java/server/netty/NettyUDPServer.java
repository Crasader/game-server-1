package server.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.TransmissionProtocol;

import java.util.Map;

/**
 * Created by veininguo on 2017/1/30.
 */
public class NettyUDPServer extends AbstractNettyServer {
    private static final Logger LOG = LoggerFactory.getLogger(NettyUDPServer.class);
    private Bootstrap bootstrap;

    public NettyUDPServer(NettyConfig nettyConfig, ChannelInitializer<?> channelInitializer) {
        super(nettyConfig, channelInitializer);
    }

    @Override
    public void configChannelOption() {
        bootstrap.option(ChannelOption.SO_BROADCAST, true);
    }

    @Override
    public TransmissionProtocol getTransmissionProtocol() {
        return TransmissionProtocol.UDP;
    }

    @Override
    public void startServer() throws Exception {
        try {
            bootstrap = new Bootstrap();
            this.configChannelOption();

            Channel channel = bootstrap.group(this.getBossGroup())
                    .channel(NioDatagramChannel.class)
                    .handler(getChannelInitialzer())
                    .bind(nettyConfig.getSocketAddress()).channel();
            ALL_CHANNELS.add(channel);
        } catch (Exception e) {
            LOG.error("UDP Server start error {}, going to shut down", e);
            super.stopServer();
            throw e;
        }
    }

    @Override
    public void setChannelInitialzer(ChannelInitializer<?> channelInitialzer) {
        this.channelInitializer = channelInitialzer;
        bootstrap.handler(channelInitialzer);
    }
}
