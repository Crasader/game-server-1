package communication;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

/**
 * Created by veininguo on 2017/1/30.
 */
public class NettyTCPMessageSender implements MessageSender {
    private Channel channel;

    public NettyTCPMessageSender(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void sendMessage(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public void close() throws Exception {
        ChannelFuture future = channel.close();
        future.sync();
    }
}