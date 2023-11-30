package com.paladin.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paladin.jwt.util.JWTUtil;
import com.paladin.jwt.vo.LoginUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局登录拦截器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中拿token
        String accessToken = request.getHeader("token");
        //从请求参数中拿token
        if (accessToken == null){
            accessToken = request.getParameter("token");
        }
        if(accessToken!=null && !accessToken.equals("")){
            //不为空,判断是否登录过期
            Claims claims = JWTUtil.checkJWT(accessToken);
            if (claims == null){
                sendJsonMessage(response, "账号已过期");
                return false;
            }
            Integer userId = Integer.valueOf(claims.get("id").toString());
            String headImg = (String) claims.get("username");
            String name = (String) claims.get("name");
            String phone = (String) claims.get("phone");
            String sex = (String) claims.get("sex");
            Integer age = (Integer) claims.get("age");
            //设置LoginUser对象属性，建造者模式
            LoginUser loginUser = LoginUser.builder()
                    .name(name)
                    .username(headImg)
                    .id(userId)
                    .phone(phone)
                    .sex(sex)
                    .age(age).build();

            //通过threadLocal共享用户登录信息
            threadLocal.set(loginUser);
            return true;
        }
        sendJsonMessage(response, "账号未登录");
        return false;
    }

    private void sendJsonMessage(HttpServletResponse response, String msg) {
        Map<String,Object> result = new HashMap<>();
        result.put("code",10000);
        result.put("msg",msg);
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(result));
            response.flushBuffer();
        } catch (IOException e) {
            log.warn("响应json数据给前端异常");
        }
    }
}