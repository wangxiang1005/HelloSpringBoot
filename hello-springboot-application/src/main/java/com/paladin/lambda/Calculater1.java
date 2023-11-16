package com.paladin.lambda;

/**
 * 不使用lambda表达式 - 原始写法
 */
public class Calculater1 {
    public static void main(String[] args) {
        final int a = 1, b = 2;
        int result = add(new IIntegerMath() {
            @Override
            public int operation() {
                return a + b;
            }
        });
        System.out.println(result);
    }

    public static int add(IIntegerMath iIntegerMath) {
        return iIntegerMath.operation();
    }
}