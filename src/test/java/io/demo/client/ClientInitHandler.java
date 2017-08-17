package io.demo.client;

import io.demo.vo.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author Lance.
 * Date: 2017-08-17 17:12
 * Desc:
 */
public class ClientInitHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(ClientInitHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("ClientInitHandler.channelActive");
        Person person = new Person();
        person.setName("guowl");
        person.setSex("man");
        person.setAge(30);
        ctx.write(person);
        ctx.flush();
    }
}
