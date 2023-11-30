package com.paladin.jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paladin.jwt.mapper.UserMapper;
import com.paladin.jwt.model.UserDO;
import com.paladin.jwt.service.UserService;
import com.paladin.jwt.util.JWTUtil;
import com.paladin.jwt.vo.LoginReq;
import com.paladin.jwt.vo.LoginUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(LoginReq req) {
        //13830567835
        System.out.println("req.getPhone()=====>>>>>"+req.getPhone());

        UserDO user = userMapper.selectOne(new QueryWrapper<UserDO>().eq("phone", req.getPhone()));
        System.out.println("user.getId()===>>>>>>>"+user.getId());

        Map<String,Object> result = new HashMap<>();

        //判断是否已经注册的
        if ("".equals(user.getUsername())) {
            //未注册
            result.put("code",10000);
            result.put("msg","用户未注册");
            return result;
        }

        System.out.println("req.getPassword()===>>>>>>>"+req.getPassword());
        System.out.println("user.getPassword()===>>>>>>>"+user.getPassword());

        if (req.getPassword().equals(user.getPassword())) {
            //登录成功,生成token,UUID生成token,存储到redis中并设置过期时间
            LoginUser loginUser = LoginUser.builder().build();
            System.out.println("user.getId()===>>>>>>>"+user.getId());
            System.out.println("user.getPhone()===>>>>>>>"+user.getPhone());
            System.out.println("user.getPassword()===>>>>>>>"+user.getPassword());
            System.out.println("user.getName()===>>>>>>>"+user.getName());
            System.out.println("user.getUsername()===>>>>>>>"+user.getUsername());
            System.out.println("user.getSex()===>>>>>>>"+user.getSex());
            System.out.println("user.getAge()===>>>>>>>"+user.getAge());
            System.out.println("user.getCreateTime()===>>>>>>>"+user.getCreateTime());

            loginUser.setId(user.getId());
            loginUser.setAge(user.getAge());
            loginUser.setName(user.getName());
            loginUser.setUsername(user.getUsername());
            loginUser.setPhone(user.getPhone());
            loginUser.setSex(user.getSex());

            System.out.println("loginUser.getId()=11==>>>>>>>"+loginUser.getId());
            System.out.println("loginUser.getAge()=11==>>>>>>>"+loginUser.getAge());
            System.out.println("loginUser.getName()=11==>>>>>>>"+loginUser.getName());
            System.out.println("loginUser.getUsername()=11==>>>>>>>"+loginUser.getUsername());
            System.out.println("loginUser.getPhone()=11==>>>>>>>"+loginUser.getPhone());
            System.out.println("loginUser.getSex()=11==>>>>>>>"+loginUser.getSex());


            return JWTUtil.geneJsonWebToken(loginUser);
        }
        result.put("code",10000);
        result.put("msg","密码错误");
        return result;
    }
}