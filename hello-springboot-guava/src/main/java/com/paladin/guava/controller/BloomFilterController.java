package com.paladin.guava.controller;

import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BloomFilterController {

    @Autowired
    private BloomFilter bloomFilter;

    @RequestMapping("/bloomFilter")
    public boolean bloomFilter(){
        String url = "https://www.dqezD.com10";
        boolean flag = bloomFilter.mightContain(url);
        System.out.println("flag====>>>>>"+flag);

        String url2 = "https://www.dqezD.com11";
        boolean flag2 = bloomFilter.mightContain(url2);
        System.out.println("flag2====>>>>>"+flag2);
        //判断是否包含这个内容
        return flag;
    }
}