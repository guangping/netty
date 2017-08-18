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


    /**
     * @desc:链接成功时触发
     * @author lance
     * @time: 2017-08-17 17:55:01
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("向服务端信息...");
        logger.info("ClientInitHandler.channelActive");
        Person person = new Person();
        person.setName("guowl");
        person.setSex("man");
        person.setAge(30);
        ctx.write(person);
        ctx.flush();
    }


    /**
     * @desc:获取返回数据
     * @author lance
     * @time: 2017-08-17 17:55:28
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("server返回的数据:{}", msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
