package io.demo.server;


import io.demo.utils.ByteBufToBytes;
import io.demo.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Author Lance.
 * Date: 2017-08-17 16:44
 * Desc:解码
 */
public class PersonDecoder extends ByteToMessageDecoder {

    //读取字节码,获取对象
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        ByteBufToBytes read = new ByteBufToBytes();
        Object obj = ByteObjConverter.byteToObject(read.read(in));
        list.add(obj);
    }
}
