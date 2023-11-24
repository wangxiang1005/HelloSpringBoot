package com.paladin.jwt.util;

import com.paladin.jwt.vo.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
@Slf4j
public class JWTUtil {

    /**
     * token过期时间，正常是7天
     */
    private static final long EXPIRE = 1000L * 60 * 60 * 24 * 7;

    /**
     * 加密的密钥
     */
    private static final String SECRET = "lixiang.com";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "LONGIN-TEST";

    /**
     * subject 颁布地址
     */
    private static final String SUBJECT = "lixiang";

    /**
     * 根据用户信息生成token
     * @param loginUser
     * @return
     */
    public static Map<String,Object> geneJsonWebToken(LoginUser loginUser){
        if(loginUser == null){
            throw new NullPointerException("loginUser对象为空");
        }
        Date endDate = new Date(System.currentTimeMillis() + EXPIRE);
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("age",loginUser.getAge())
                .claim("id",loginUser.getId())
                .claim("name",loginUser.getName())
                .claim("phone",loginUser.getPhone())
                .claim("sex",loginUser.getSex())
                .setIssuedAt(new Date())
                .setExpiration(endDate)
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
        token = TOKEN_PREFIX+token;
        Map<String,Object> map = new HashMap<>();
        map.put("accessToken",token);
        map.put("accessTokenExpires",endDate);
        return map;
    }

    /**
     * 检验token方法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try{
            return Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        }catch (Exception e){
            log.info("JWT token解密失败");
            return null;
        }
    }
}