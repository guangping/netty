package io.demo.server;

import io.demo.utils.ByteObjConverter;
import io.demo.vo.Person;
import io.netty.buffer.ByteBuf;
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
        logger.info("获取请求数据:{}", person);
        //业务处理

        //ctx.write(msg);

        Person rValue = new Person();
        rValue.setAge(10);
        rValue.setName("lance");
        rValue.setSex("F");

        byte[] bytes = ByteObjConverter.objectToByte(rValue);

        ByteBuf encoded = ctx.alloc().buffer(bytes.length);
        encoded.writeBytes(bytes);

        ctx.write(encoded);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("异常捕获");
        cause.printStackTrace();
        ctx.close();
    }
}
