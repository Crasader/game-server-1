package server.netty;

import io.netty.channel.ChannelInitializer;
import server.NetworkServer;

/**
 * Created by veininguo on 2017/1/30.
 */
public interface NettyServer extends NetworkServer {
    NettyConfig getNettyConfig();

    void configChannelOption();

    void setChannelInitialzer(ChannelInitializer<?> channelInitialzer);

    ChannelInitializer getChannelInitialzer();
}