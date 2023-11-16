package com.paladin.lambda;

/**
 * 使用lambda表达式-2
 */
public class Calculator3 {
    public static void main(String[] args) {
        int a = 1, b = 2;
        IIntegerMath integerMath = () -> a + b;
        int result = integerMath.operation();
        System.out.println(result);
    }
}