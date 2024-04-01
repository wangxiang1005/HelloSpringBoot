package com.paladin.stream.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtil2 {

    public static void main(String[] args) {
        StreamUtil2 streamUtil2 = new StreamUtil2();
        streamUtil2.test1();
    }

    private void test1(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream()//创建流
                .filter(s -> s.startsWith("c"))//执行过滤, 过滤出以c为前缀的字符串
                .map(String::toUpperCase)//转换成大写
                .sorted()//排序
                .forEach(System.out::println);//for循环打印
    }

    private void test2(){
        Stream.of("a1", "a2", "a3")//创建流
                .findFirst()//找到第一个元素
                .ifPresent(System.out::println);//如果存在即输出
    }

    private void test3(){
        IntStream.range(1, 4).forEach(System.out::println);
    }


    private void test4(){
        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)//对数值中的每个对象执行2*n+1操作
                .average()//求平均值
                .ifPresent(System.out::println);//如果值不为空则输出
    }

    private void test5(){
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1)) //对每个字符串元素从下标1位置开始截取
                .mapToInt(Integer::parseInt)//转成int基础类型类型流
                .max() //取最大值
                .ifPresent(System.out::println);//不为空则输出
    }

    private void test6(){
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)//for循环1->4拼接前缀a
                .forEach(System.out::println);//for循环打印
    }

    private void test7(){
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)//double类型转int
                .mapToObj(i -> "a" + i)//对值拼接前缀a
                .forEach(System.out::println);//for循环打印
    }

    private void test8(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    private void test9(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); //转大写
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A"); //过滤出以A为前缀的元素
                });
    }

    private void test10(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); //转大写
                })
                .filter(s -> {////放在后面,调用次数会比较多
                    System.out.println("filter: " + s);
                    return s.startsWith("A"); //过滤出以A为前缀的元素
                })
                .forEach(s -> System.out.println("forEach: " + s)); //for循环输出
    }

    private void test11(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {//放在前面,减少调用次数
                    System.out.println("filter: " + s);
                    return s.startsWith("a");//过滤出以a为前缀的元素
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();//转大写
                })
                .forEach(s -> System.out.println("forEach: " + s));//for循环输出
    }

    private void test12(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    private void test13(){//Stream复用-Supplier
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
    }
}