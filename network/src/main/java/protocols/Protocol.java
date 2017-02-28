package protocols;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelPipeline;

/**
 * Created by veininguo on 2017/2/17.
 */
public interface Protocol {
    String getProtocolName();

    boolean applyProtocol(ByteBuf buffer, ChannelPipeline pipeline);
}
