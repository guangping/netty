package io.demo.server;

import io.demo.coder.PersonDecoder;
import io.demo.coder.PersonEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author Lance.
 * Date: 2017-08-17 16:37
 * Desc:
 */
public class PersonServer {

    private static final Logger logger = LogManager.getLogger(PersonServer.class);

    public static void main(String[] args) throws Exception {
        PersonServer server = new PersonServer();
        server.start(8080);
    }

    private void start(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //pipeline.addLast(new BusinessOutHandler());

                            pipeline.addLast("decoder", new PersonDecoder());
                            pipeline.addLast("encoder", new PersonEncoder());
                            pipeline.addLast("handler", new BusinessHandler());

                        }
                    });

            ChannelFuture f = b.bind(port).sync();
            logger.info("server is start.......");
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}
