package server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by veininguo on 2017/1/30.
 */
public abstract class AbstractNettyServer implements NettyServer {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractNettyServer.class);
    public static final ChannelGroup ALL_CHANNELS = new DefaultChannelGroup("CHANNELS", GlobalEventExecutor.INSTANCE);
    protected final NettyConfig nettyConfig;
    protected ChannelInitializer<?> channelInitializer;

    public AbstractNettyServer(NettyConfig nettyConfig, ChannelInitializer<?> channelInitializer) {
        this.nettyConfig = nettyConfig;
        this.channelInitializer = channelInitializer;
    }

    @Override
    public void startServer(int port) throws Exception {
        nettyConfig.setPort(port);
        nettyConfig.setSocketAddress(new InetSocketAddress(port));
        startServer();
    }

    @Override
    public void startServer(InetSocketAddress socketAddress) throws Exception {
        nettyConfig.setSocketAddress(socketAddress);
        startServer();
    }

    @Override
    public void stopServer() throws Exception {
        LOG.debug("In stopServer method of class: {}", this.getClass().getName());
        ChannelGroupFuture future = ALL_CHANNELS.close();
        try {
            future.await();
        } catch (Exception e) {
            LOG.error("Execption occurred while waiting for channels to close: {}", e);
        } finally {
            EventLoopGroup bossGroup = this.getBossGroup();
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }

            EventLoopGroup workerGroup = this.getWorkerGroup();
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
        }
    }

    @Override
    public NettyConfig getNettyConfig() {
        return nettyConfig;
    }

    @Override
    public InetSocketAddress getSocketAddress() {
        return nettyConfig.getSocketAddress();
    }

    @Override
    public ChannelInitializer getChannelInitialzer() {
        return this.channelInitializer;
    }

    protected EventLoopGroup getBossGroup(){
        return nettyConfig.getBossGroup();
    }

    protected EventLoopGroup getWorkerGroup(){
        return nettyConfig.getWorkerGroup();
    }
}
