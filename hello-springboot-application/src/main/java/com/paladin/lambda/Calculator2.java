package com.paladin.lambda;

/**
 * 使用lambda表达式-1
 */
public class Calculator2 {
    public static void main(String[] args) {
        int a = 1, b = 2;
        int result = add(() -> a + b);
        System.out.println(result);
    }

    public static int add(IIntegerMath iIntegerMath) {
        return iIntegerMath.operation();
    }
}