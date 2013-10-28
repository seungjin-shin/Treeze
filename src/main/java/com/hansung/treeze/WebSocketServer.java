package com.hansung.treeze;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

public class WebSocketServer implements Runnable{
	private static int countInstance = 0;
	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	
	public void start() {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info("Web Socket Server Started.");
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		if (0 == countInstance) {
			Thread t = new Thread(this);
			t.start();
			countInstance++;
		} else {
			logger.info(">>>> instance count is : " + countInstance);
		}
	}
	
	public void sendToClient(String message) {
		for(Channel channel : channels) {
			channel.writeAndFlush(new TextWebSocketFrame(message));
		}
	}
	public void run() {
		logger.info(">>>> connection waitting..");
		try {
			run(8090);
			
			
		} catch (Exception e) {
			logger.error("Socket Exception", e);
		}
	}
	
	/*default*/ static List<Channel> channels = new ArrayList<Channel>();
    public void run(final int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new WebSocketServerInitializer());

            Channel ch = b.bind(port).sync().channel();
            
            System.out.println("Web socket server started at port " + port + '.');
            System.out.println("Open your browser and navigate to http://localhost:" + port + '/');

            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
