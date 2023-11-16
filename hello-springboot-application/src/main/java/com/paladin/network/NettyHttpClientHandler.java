package com.paladin.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class NettyHttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("http://127.0.0.1:8080");
        String msg = "Are you ok?";
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes(StandardCharsets.UTF_8)));
        // 构建http请求
        // request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
        // request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        // 发送http请求
        ctx.channel().writeAndFlush(request);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) {
        msg.headers().get(HttpHeaderNames.CONTENT_TYPE);
        ByteBuf buf = msg.content();
        System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
    }
}