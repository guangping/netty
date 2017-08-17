package io.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Author Lance.
 * Date: 2017-08-17 16:56
 * Desc:ByteBufToBytes 对ByteBuf的数据进行读取，支持流式读取（reading 和 readFull方法结合使用）
 */
public class ByteBufToBytes {
    private ByteBuf temp;

    private boolean end = true;

    public ByteBufToBytes() {
    }

    public ByteBufToBytes(int length) {
        temp = Unpooled.buffer(length);
    }

    public void reading(ByteBuf datas) {
        datas.readBytes(temp, datas.readableBytes());
        if (this.temp.writableBytes() != 0) {
            end = false;
        } else {
            end = true;
        }
    }

    public boolean isEnd() {
        return end;
    }

    public byte[] readFull() {
        if (end) {
            byte[] contentByte = new byte[this.temp.readableBytes()];
            this.temp.readBytes(contentByte);
            this.temp.release();
            return contentByte;
        } else {
            return null;
        }
    }

    public byte[] read(ByteBuf datas) {
        byte[] bytes = new byte[datas.readableBytes()];
        datas.readBytes(bytes);
        return bytes;
    }

}
