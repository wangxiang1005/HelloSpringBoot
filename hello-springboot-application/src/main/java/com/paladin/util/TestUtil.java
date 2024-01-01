package com.paladin.util;

public class TestUtil {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        String serviceName="express::123";
        int groupIdx = serviceName.indexOf("::");
        if (groupIdx > 0) {
            System.out.println(serviceName.substring(0, groupIdx));
            System.out.println(serviceName.substring(0, groupIdx+2));
        }
    }
}