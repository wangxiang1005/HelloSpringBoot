package com.paladin.stream.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamUtil4 {

    public static void main(String[] args) {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        StreamUtil4 streamUtil4 = new StreamUtil4();
        streamUtil4.test1(values);
        streamUtil4.test2(values);
    }

    /*顺序流排序*/
    private void test1(List<String> values){
        // 纳秒
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        // 纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.printf("顺序流排序耗时: %d ms%n", millis);
    }

    /*并行流排序*/
    private void test2(List<String> values){
        // 纳秒
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        // 纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("并行流排序耗时: %d ms", millis));
    }
}