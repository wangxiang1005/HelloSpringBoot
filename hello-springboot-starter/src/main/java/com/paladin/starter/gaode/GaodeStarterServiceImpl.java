package com.paladin.starter.gaode;

import com.alibaba.fastjson.JSONObject;
import com.paladin.starter.IPStarterService;
import com.paladin.starter.util.SmartHttpUtil;
import com.paladin.starter.vo.IpAnalyzeGaodeResponse;
import com.paladin.starter.vo.IpAnalyzeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class GaodeStarterServiceImpl implements IPStarterService {

    /**
     * 响应成功状态码
     */
    private final String OKOK = "1";

    @Value("${ip.convert.conf.gaode.key}")
    private String key;

    @Value("${ip.convert.conf.gaode.url}")
    private String url;

    /*public static void main(String[] args) {
        String ip = "39.156.66.10";
        if (StringUtils.isBlank(ip)) {
            System.out.println("=========>>>>>>>>>>>>>");
        }
        Map<String,String> params = new HashMap<>();
        params.put("key", "8aa7b7abd4d843d8d5b55d2fdb3ff74b");
        params.put("ip", ip);
        IpAnalyzeResponse ipAnalyzeResponse = null;
        try {
            String json = SmartHttpUtil.sendGet("https://restapi.amap.com/v3/ip", params, null);
            IpAnalyzeGaodeResponse response = JSONObject.parseObject(json, IpAnalyzeGaodeResponse.class);
            if("1".equals(response.getStatus())) {
                ipAnalyzeResponse = IpAnalyzeResponse.builder()
                        .status(response.getInfocode())
                        .successFlag(true)
                        .msg(response.getInfo())
                        .province(response.getProvince())
                        .city(response.getCity())
                        .build();
                // 对rectangle进行解析
                String rectangle = response.getRectangle();
                String[] split = rectangle.split(";|,");
                if(split.length == 1) {
                    ipAnalyzeResponse.setSuccessFlag(false);
                    ipAnalyzeResponse.setStatus("0");
                    ipAnalyzeResponse.setMsg("ip[" + ip + "]非法");

                    System.out.println(ip);
                }
                ipAnalyzeResponse.setX((Double.parseDouble(split[0]) + Double.parseDouble(split[2])) / 2.0);
                ipAnalyzeResponse.setY((Double.parseDouble(split[1]) + Double.parseDouble(split[3])) / 2.0);

                System.out.println(ip);
                System.out.println(ipAnalyzeResponse.getProvince());
                System.out.println(ipAnalyzeResponse.getCity());
                System.out.println(ipAnalyzeResponse.getX());
                System.out.println(ipAnalyzeResponse.getY());
            }
            System.out.println("response.getInfocode()==>>>>"+response.getInfocode());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e.getMessage()==>>>>"+e.getMessage());
        }
    }*/

    @Override
    public String ipAnalyze(String ip) {
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        Map<String,String> params = new HashMap<>();
        params.put("key", key);
        params.put("ip", ip);
        IpAnalyzeResponse ipAnalyzeResponse = null;
        try {
            String json = SmartHttpUtil.sendGet(url, params, null);
            IpAnalyzeGaodeResponse response = JSONObject.parseObject(json, IpAnalyzeGaodeResponse.class);
            if(OKOK.equals(response.getStatus())) {
                ipAnalyzeResponse = IpAnalyzeResponse.builder()
                        .status(response.getInfocode())
                        .successFlag(true)
                        .msg(response.getInfo())
                        .province(response.getProvince())
                        .city(response.getCity())
                        .build();
                // 对rectangle进行解析
                String rectangle = response.getRectangle();
                String[] split = rectangle.split(";|,");
                if(split.length == 1) {
                    ipAnalyzeResponse.setSuccessFlag(false);
                    ipAnalyzeResponse.setStatus("0");
                    ipAnalyzeResponse.setMsg("ip[" + ip + "]非法");

                    System.out.println(ip);
                    return ipAnalyzeResponse.getCity();
                }
                ipAnalyzeResponse.setX((Double.parseDouble(split[0]) + Double.parseDouble(split[2])) / 2.0);
                ipAnalyzeResponse.setY((Double.parseDouble(split[1]) + Double.parseDouble(split[3])) / 2.0);

                System.out.println(ip);
                System.out.println(ipAnalyzeResponse.getProvince());
                System.out.println(ipAnalyzeResponse.getCity());
                System.out.println(ipAnalyzeResponse.getX());
                System.out.println(ipAnalyzeResponse.getY());
                return ipAnalyzeResponse.getCity();
            }
            System.out.println("response.getInfocode()==>>>>"+response.getInfocode());
            return IpAnalyzeResponse.builder()
                    .status(response.getInfocode())
                    .successFlag(false)
                    .msg(response.getInfo())
                    .build().toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e.getMessage()==>>>>"+e.getMessage());
            return IpAnalyzeResponse.builder()
                    .status("500")
                    .successFlag(false)
                    .msg(e.getMessage())
                    .build().toString();
        }
    }
}