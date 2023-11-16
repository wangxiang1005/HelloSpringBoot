package com.paladin.lambda.Stream;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StreamTest2 {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        List<Person> filtered =
                persons
                        .stream() //构建流
                        .filter(p -> p.name.startsWith("P")) //过滤出名字以P开头的
                        .collect(Collectors.toList()); //生成一个新的List
        System.out.println(filtered);    //[Peter, Pamela]

        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age)); //以年龄为key进行分组

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
        // age 18: [Max]
        // age 23: [Peter, Pamela]
        // age 12: [David]

        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age)); //聚合出平均年龄
        System.out.println(averageAge);     //19.0

        persons.stream().filter(p -> p.age>20).collect(Collectors.toList()).forEach(System.out::println);

        persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s [%s]\n", sum, p, Thread.currentThread().getName());
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        //顺序流排序
        // 纳秒
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        // 纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.printf("顺序流排序耗时: %d ms", millis);

        //并行流排序
        // 纳秒
        long t2 = System.nanoTime();

        long count2 = values.parallelStream().sorted().count();
        System.out.println(count2);

        long t3 = System.nanoTime();

        // 纳秒转微秒
        long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.printf("并行流排序耗时: %d ms", millis2);
    }
}