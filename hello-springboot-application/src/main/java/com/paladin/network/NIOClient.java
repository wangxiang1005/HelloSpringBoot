package com.paladin.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) {
        // 启动客户端
        new Thread(NIOClient::startClient).start();
    }

    // 客户端示例
    public static void startClient() {
        try {
            // 创建客户端的SocketChannel并连接服务器端
            SocketChannel clientChannel = SocketChannel.open();
            clientChannel.connect(new InetSocketAddress("localhost", 8888));
            System.out.println("客户端连接服务器端");

            // 发送数据到服务器端
            String message = "Hello, server!";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            clientChannel.write(buffer);

            // 关闭通道
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}