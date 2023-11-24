package com.paladin.jwt;

import com.paladin.jwt.util.JWTUtil;
import com.paladin.jwt.vo.LoginUser;
import io.jsonwebtoken.Claims;
import java.util.Map;

public class JwtTest {
    public static void main(String[] args) {
        LoginUser loginUser = LoginUser.builder()
                .age(18)
                .id(1)
                .name("李祥")
                .phone("13820934720")
                .sex("男")
                .username("lixiang")
                .build();

        Map<String, Object> objectMap = JWTUtil.geneJsonWebToken(loginUser);

        System.out.println("LoginUser加密：");
        objectMap.forEach((k,v)->{
            System.out.println("---key:"+k+",value:"+v);
        });

        String accessToken = String.valueOf(objectMap.get("accessToken"));
        System.out.println("Token解密：");
        Claims claims = JWTUtil.checkJWT(accessToken);
        System.out.println("---name:"+claims.get("name"));
        System.out.println("---age:"+claims.get("age"));
        System.out.println("---phone:"+claims.get("phone"));
        System.out.println("---username:"+claims.get("username"));
        System.out.println("---sex:"+claims.get("sex"));
    }
}