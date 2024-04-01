package com.paladin.stream.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        StreamTest streamTest = new StreamTest();
        streamTest.test1();
    }

    private void test1(){
        // ifPresent表示Optional中的对象存在才会执行Consumer接口对象中的方法
        List<String> dataList = new ArrayList<>();
        // 1.不为空没有值的集合
        Optional.ofNullable(dataList)
                .ifPresent(t -> {
                    System.out.println("1"); //输出1
                    t.forEach(a -> System.out.println(a));
                });
    }

    private void test2(){
        String sr = "[\"2024-02-05T14:07:00\",0.2176978234573176]";
        System.out.println(sr);

        String[] split = sr.substring(1, sr.lastIndexOf("]")).split(",");

        Arrays.stream(split)
                .map(p1 -> p1.replaceAll("\"", ""))
                .forEach(System.out::println);

        List<String> list = Arrays.stream(split)
                .map(p1 -> p1.replaceAll("\"", ""))
                .collect(Collectors.toList());

        System.out.println("==============>>>>>>>>>"+list);
        System.out.println("==============>>>>>>>>>"+list.get(0));
        System.out.println("==============>>>>>>>>>"+list.get(1));
    }
}