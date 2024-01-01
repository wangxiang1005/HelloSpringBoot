package com.paladin.guacamelo.ssh.config;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/servletTunnel")
public class ServletTunnel extends GuacamoleHTTPTunnelServlet {

	private static final long serialVersionUID = -3224942386695394818L;

	@Override
	protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {
        /*System.out.println("request:"+request.getParameterMap());
        System.out.println("request2:"+request.getParameterNames());

        String height = request.getParameterMap().get("height").toString();
        String width = request.getParameterMap().get("width").toString();
        String protocol = request.getParameterMap().get("protocol").toString();

        System.out.println("=====ServletTunnel=====height=====>>>>>>>>"+height);
        System.out.println("=====ServletTunnel=====width=====>>>>>>>>"+width);
        System.out.println("=====ServletTunnel=====protocol=====>>>>>>>>"+protocol);

        *//*GuacamoleClientInformation information = new GuacamoleClientInformation();
        information.setOptimalScreenHeight(Integer.parseInt(height));
        information.setOptimalScreenWidth(Integer.parseInt(width));*//*

	    String hostname = "192.168.5.180"; //guacamole server地址
        int port = 4822; //guacamole server端口
        GuacamoleConfiguration configuration = new GuacamoleConfiguration();
        configuration.setProtocol(protocol); // 远程连接协议

        if(protocol.equals("ssh")) {
            configuration.setParameter("width", width);
            configuration.setParameter("height", height);
            configuration.setParameter("color-scheme", "white-black");
            configuration.setParameter("enable-sftp", "false");
        }else if(protocol.equals("vnc")){
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
        String outputFilePath = "/home/guacamole";
        //添加会话录制--录屏
        configuration.setParameter("recording-path", outputFilePath);
        configuration.setParameter("create-recording-path", "true");
        configuration.setParameter("recording-name", fileName);

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(hostname, port),
                configuration
        );
        return new SimpleGuacamoleTunnel(socket);*/
        return null;
	}

    private String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}