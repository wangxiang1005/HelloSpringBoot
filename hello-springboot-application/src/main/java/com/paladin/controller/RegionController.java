package com.paladin.controller;

import com.paladin.starter.IPStarterService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * IP -> Address Region API测试
 *
 */
@Controller
@EnableAsync
public class RegionController {

    @Resource
    private IPStarterService regionIPStarterServiceImpl;

    @RequestMapping("/get-ip-from-region")
    @ResponseBody
    public String insertLoginMessage(HttpServletRequest httpServletRequest){
        String ip1 = httpServletRequest.getRemoteAddr();
        System.out.println("ip1 : " + ip1);

        String ip2 = httpServletRequest.getLocalAddr();
        System.out.println("ip2 : " + ip2);

        String ip = "39.156.66.10";

        String address = regionIPStarterServiceImpl.ipAnalyze(ip);
        System.out.println("===========>>>>>>>>>>>>>>>"+address);

        return address;
    }
}