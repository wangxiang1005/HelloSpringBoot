package com.paladin.lambda.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest1 {
    public static void main(String[] args) {
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream() //创建流
                .filter(s -> s.startsWith("c")) //执行过滤, 过滤出以c为前缀的字符串
                .map(String::toUpperCase) //转换成大写
                .sorted() //排序
                .forEach(System.out::println); //for 循环打印

        Arrays.asList("a1", "a2", "a3")
                .stream() //创建流
                .findFirst() //找到第一个元素
                .ifPresent(System.out::println);  //如果存在即输出

        //在集合上调用stream()方法会返回一个普通的Stream流, 但是您大可不必刻意地创建一个集合, 再通过集合来获取Stream流, 您还可以通过如下这种方式：
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);

        IntStream.range(1, 4).forEach(System.out::println); //相当于for(int i = 1; i < 4; i++) {}

        LongStream.range(1, 4).forEach(System.out::println); //相当于for(long i = 1; i < 4; i++) {}

        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1) //对数值中的每个对象执行2*n+1操作
                .average() //求平均值
                .ifPresent(System.out::println);  //如果值不为空则输出

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1)) //对每个字符串元素从下标1位置开始截取
                .mapToInt(Integer::parseInt) //转成int基础类型类型流
                .max() //取最大值
                .ifPresent(System.out::println);  //不为空则输出

        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i) //for循环1->4拼接前缀a
                .forEach(System.out::println); //for循环打印

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue) //double类型转int
                .mapToObj(i -> "a" + i) //对值拼接前缀 a
                .forEach(System.out::println); //for循环打印

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); //转大写
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A"); //过滤出以A为前缀的元素
                });

        List<String> ls = IntStream.range(1, 4)
                .mapToObj(i -> "a" + i) //for循环1->4拼接前缀a
                .collect(Collectors.toList());
        System.out.println(ls.size());
        for (String l : ls) {
            System.out.println(l);
        }
    }
}