package com.paladin.jwt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;
}