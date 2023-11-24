package com.paladin.jwt.service;

import com.paladin.jwt.vo.LoginReq;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface UserService {
    /**
     * 登录方法
     * @param req
     * @return
     */
    Map<String, Object> login(LoginReq req);
}