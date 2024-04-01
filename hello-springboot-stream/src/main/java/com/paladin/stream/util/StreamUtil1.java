package com.paladin.stream.util;

import java.util.stream.Stream;

public class StreamUtil1 {

    public static void main(String[] args) {
        StreamUtil1 streamUtil1 = new StreamUtil1();

        streamUtil1.test1();

        streamUtil1.test2();
    }

    /*及早求值*/
    private void test1(){
        System.out.println("及早求值");

    }

    /*惰性求值*/
    private void test2(){
        Stream.of(2, 3, 4, 1, 5, 3, 7)
                .filter(x -> {
                    System.out.println("惰性求值");
                    return x > 2;
                });
    }
}
