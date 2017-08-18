package io.demo.server;

import io.demo.utils.ByteObjConverter;
import io.demo.vo.Person;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.collections.Maps;

import java.util.Map;

/**
 * Author Lance.
 * Date: 2017-08-17 18:05
 * Desc:处理返回结果
 */
public class BusinessOutHandler extends ChannelOutboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(BusinessOutHandler.class);


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info("返回值");
        Person person = new Person();
        person.setAge(10);
        person.setName("lance");
        person.setSex("F");

        byte[] bytes = ByteObjConverter.objectToByte(person);

        ByteBuf encoded = ctx.alloc().buffer(bytes.length);
        encoded.writeBytes(bytes);

        ctx.write(encoded);
        ctx.flush();
    }
}
