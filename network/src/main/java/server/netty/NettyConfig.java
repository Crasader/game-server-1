package server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by veininguo on 2017/2/15.
 */
public class NettyConfig {
    private Map<ChannelOption<?>, Object> channelOptions;
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private int bossThreadCount;
    private int workerThreadCount;
    private int port = 8888;
    private InetSocketAddress socketAddress;
    protected ChannelInitializer<? extends Channel> channelInitializer;

    public Map<ChannelOption<?>, Object> getChannelOptions() {
        if (channelOptions == null) {
            channelOptions = new HashMap<>();
        }
        return channelOptions;
    }

    public void setChannelOptions(Map<ChannelOption<?>, Object> channelOptions) {
        this.channelOptions = channelOptions;
    }

    public synchronized NioEventLoopGroup getBossGroup() {
        if (bossGroup == null) {
            if (bossThreadCount > 0) {
                bossGroup = new NioEventLoopGroup(bossThreadCount);
            } else {
                bossGroup = new NioEventLoopGroup();
            }
        }
        return bossGroup;
    }

    public void setBossGroup(NioEventLoopGroup bossGroup) {
        this.bossGroup = bossGroup;
    }

    public synchronized NioEventLoopGroup getWorkerGroup() {
        if (workerGroup == null) {
            if (bossThreadCount > 0) {
                workerGroup = new NioEventLoopGroup(workerThreadCount);
            } else {
                workerGroup = new NioEventLoopGroup();
            }
        }
        return workerGroup;
    }

    public void setWorkerGroup(NioEventLoopGroup workerGroup) {
        this.workerGroup = workerGroup;
    }

    public int getBossThreadCount() {
        return bossThreadCount;
    }

    public void setBossThreadCount(int bossThreadCount) {
        this.bossThreadCount = bossThreadCount;
    }

    public int getWorkerThreadCount() {
        return workerThreadCount;
    }

    public void setWorkerThreadCount(int workerThreadCount) {
        this.workerThreadCount = workerThreadCount;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public synchronized InetSocketAddress getSocketAddress() {
        if (socketAddress == null) {
            socketAddress = new InetSocketAddress(port);
        }
        return socketAddress;
    }

    public void setSocketAddress(InetSocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public ChannelInitializer<? extends Channel> getChannelInitializer() {
        return channelInitializer;
    }

    public void setChannelInitializer(ChannelInitializer<? extends Channel> channelInitializer) {
        this.channelInitializer = channelInitializer;
    }
}
