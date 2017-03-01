package protocols;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelPipeline;

/**
 * Created by veininguo on 2017/2/17.
 */
public class ProtobufProtocol implements Protocol {
    @Override
    public String getProtocolName() {
        return "Protocol buffer";
    }

    @Override
    public boolean applyProtocol(ByteBuf buffer, ChannelPipeline pipeline) {

        return false;
    }
}
