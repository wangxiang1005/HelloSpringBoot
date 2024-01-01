package com.paladin.guacamelo.ssh.config;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.websocket.GuacamoleWebSocketTunnelEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ServerEndpoint(value = "/webSocketTunnel", subprotocols = "guacamole")
@Component
@EnableAutoConfiguration
public class WebSocketTunnel extends GuacamoleWebSocketTunnelEndpoint {

    @Value("${guacamelo.host}")
    private String host;

    @Value("${guacamelo.port}")
    private int port;

    /**
     * Returns a new tunnel for the given session. How this tunnel is created
     * or retrieved is implementation-dependent.
     *
     * @param session        The session associated with the active WebSocket connection.
     * @param endpointConfig information associated with the instance of the endpoint created for handling this single connection.
     * @return A connected tunnel, or null if no such tunnel exists.
     * @throws GuacamoleException If an error occurs while retrieving the tunnel, or if access to the tunnel is denied.
     */
    @Override
    protected GuacamoleTunnel createTunnel(Session session, EndpointConfig endpointConfig) throws GuacamoleException {
        System.out.println("sessionMap:" + session.getRequestParameterMap());

        String height = session.getRequestParameterMap().get("height").get(0);
        String width = session.getRequestParameterMap().get("width").get(0);
        String protocol = session.getRequestParameterMap().get("protocol").get(0);

        System.out.println("=====webSocket=====height=====>>>>>>>>"+height);
        System.out.println("=====webSocket=====width=====>>>>>>>>"+width);
        System.out.println("=====webSocket=====protocol=====>>>>>>>>"+protocol);

        System.out.println("=====webSocket=====host=====>>>>>>>>"+host);
        System.out.println("=====webSocket=====port=====>>>>>>>>"+port);

        /*GuacamoleClientInformation information = new GuacamoleClientInformation();
        information.setOptimalScreenHeight(Integer.parseInt(height));
        information.setOptimalScreenWidth(Integer.parseInt(width));*/

        //guacamole server地址端口
        host = "192.168.5.180";
        port = 4822;
        GuacamoleConfiguration configuration = new GuacamoleConfiguration();
        configuration.setProtocol(protocol);

        if(protocol.equals("ssh")) {
            configuration.setParameter("width", width);
            configuration.setParameter("height", height);
            configuration.setParameter("color-scheme", "white-black");
            configuration.setParameter("enable-sftp", "false");
        }
        else if(protocol.equals("vnc")){
            configuration.setParameter("width", width);
            configuration.setParameter("height", height);
        }else if(protocol.equals("rdp")) {
            configuration.setParameter("ignore-cert", "true");
            configuration.setParameter("width", width);
            configuration.setParameter("height", height);
        }

        configuration.setParameter("hostname", "192.168.5.181");
        configuration.setParameter("port", "22");
        configuration.setParameter("username", "root");
        configuration.setParameter("password", "root");
        configuration.setParameter("ignore-cert", "true");

        String fileName = getNowTime() + ".guac";//文件名
        String outputFilePath = "/recording";
        //添加会话录制--录屏
        configuration.setParameter("recording-path", outputFilePath);
        configuration.setParameter("create-recording-path", "true");
        configuration.setParameter("recording-name", fileName);

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(host, port),
                configuration
        );

        return new SimpleGuacamoleTunnel(socket);
    }

    private void optClose(Session session) {
        // 判断当前连接是否还在线
        if (session.isOpen()) {
            try {
                // 关闭连接
                CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "进行关闭！");
                session.close(closeReason);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}