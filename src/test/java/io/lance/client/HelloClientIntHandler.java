package io.lance.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author Lance.
 * Date: 2017-08-09 16:13
 * Desc:
 */
public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(HelloClientIntHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelRegistered");
    }

    //连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelActive");

        String msg = "Are you ok?";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);

        ctx.flush();
    }

    //接收server端的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("接收server端的消息。channelRead");
        ByteBuf result = (ByteBuf) msg;

        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        logger.info("消息:{}", new String(result1));

        result.release();//释放资源
    }
}
