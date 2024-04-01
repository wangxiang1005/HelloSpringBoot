package com.paladin.util;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        test2();
    }

    private static void test1(){
        String serviceName="express::123";
        int groupIdx = serviceName.indexOf("::");
        if (groupIdx > 0) {
            System.out.println(serviceName.substring(0, groupIdx));
            System.out.println(serviceName.substring(0, groupIdx+2));
        }
    }

    private static void test2(){
        List<String> metricsNameList = new ArrayList<>();
        metricsNameList.add("cpu.util");
        metricsNameList.add("memory.util");

        String stringFromList = String.join(",", metricsNameList);
        System.out.println(stringFromList);
    }
}