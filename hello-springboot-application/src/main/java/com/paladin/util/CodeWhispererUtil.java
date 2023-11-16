package com.paladin.util;

public class CodeWhispererUtil {

    public static void main(String[] args) {
        int a = 1, b = 2;
        add(a, b);
    }

    // 启动一个异步线程，并在线程中执行指定的任务
    public static void runAsync(Runnable runnable) {
        new Thread(runnable).start();
    }

    // 求两数之和
    public static int add(int a, int b) {
        System.out.println(a + b);
        return a + b;
    }

    // 求两数之和并采用lambda表达式调用
    public static int addLambda(int a, int b) {
        return add(a, b);
    }
}