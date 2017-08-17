package io.demo.server;

import io.demo.vo.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author Lance.
 * Date: 2017-08-17 17:08
 * Desc:
 */
public class BusinessHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(BusinessHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Person person = (Person) msg;
        logger.info("BusinessHandler read msg from client :{}" , person);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("异常捕获");
    }
}
