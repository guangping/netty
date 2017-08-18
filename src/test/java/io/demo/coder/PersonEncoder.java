package io.demo.coder;

import io.demo.utils.ByteObjConverter;
import io.demo.vo.Person;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Author Lance.
 * Date: 2017-08-17 16:45
 * Desc:编码
 */
public class PersonEncoder extends MessageToByteEncoder<Person> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Person person, ByteBuf out) throws Exception {
        out.writeBytes(ByteObjConverter.objectToByte(person));
        ctx.flush();
    }
}
