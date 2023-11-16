package com.paladin.lambda;

public interface IIntegerMath {
    int operation();

    // 求平方根
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}