package io.demo.client;

import io.demo.coder.PersonDecoder;
import io.demo.coder.PersonEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Author Lance.
 * Date: 2017-08-17 16:41
 * Desc:
 */
public class PersonClient {

    public static void main(String[] args) throws Exception {
        PersonClient client = new PersonClient();
        client.connect("127.0.0.1", 8080);
    }

    private void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("decoder",new PersonDecoder());
                    pipeline.addLast("encoder",new PersonEncoder());

                    pipeline.addLast("handler",new ClientInitHandler());
                }
            });
            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
