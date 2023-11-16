package com.paladin.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {
    public static void main(String[] args) {
        // 启动服务器端
        new Thread(NIOServer::startServer).start();
    }

    // 服务器端示例
    public static void startServer() {
        try {
            // 创建服务器端的SocketChannel并绑定端口
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress("localhost", 8888));

            // 接受客户端连接
            SocketChannel clientChannel = serverChannel.accept();
            System.out.println("服务器端接受到客户端连接");

            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 从客户端接收数据
            clientChannel.read(buffer);
            buffer.flip();

            // 输出接收到的数据
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }

            // 关闭通道
            clientChannel.close();
            serverChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}