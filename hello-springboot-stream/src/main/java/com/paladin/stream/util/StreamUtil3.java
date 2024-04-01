package com.paladin.stream.util;

import com.paladin.stream.vo.Person;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamUtil3 {

    // 构建一个Person集合
    List<Person> persons =
            Arrays.asList(
                    new Person("Max", 18),
                    new Person("Peter", 23),
                    new Person("Pamela", 23),
                    new Person("David", 12));


    public static void main(String[] args) {
        StreamUtil3 streamUtil3 = new StreamUtil3();
        streamUtil3.test1();

        Consumer<StreamUtil3> test2 = StreamUtil3::test2;


    }

    private void test1(){
        List<Person> filtered =
                persons
                        .stream()//构建流
                        .filter(p -> p.name.startsWith("P"))//过滤出名字以P开头的
                        .collect(Collectors.toList());//生成一个新的List
        System.out.println(filtered);//[Peter, Pamela]
    }

    private void test2(){
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));//以年龄为key进行分组

        personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
    }

    private void test3(){
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));//聚合出平均年龄
        System.out.println(averageAge);     //19.0
    }

    private void test4(){
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)//过滤出年龄大于等于18的
                .map(p -> p.name)//提取名字
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
                //以In Germany开头, and连接各元素, 再以are of legal age.结束
        System.out.println(phrase);
        //In Germany Max and Peter and Pamela are of legal age.
    }

    private void test5(){
        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));//对于同样key将值拼接
        System.out.println(map);
        //{18=Max, 23=Peter;Pamela, 12=David}
    }

    private void test6(){
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),//supplier供应器
                        (j, p) -> j.add(p.name.toUpperCase()),  //accumulator累加器
                            StringJoiner::merge,                //combiner组合器
                        StringJoiner::toString);                //finisher终止器
        String names = persons
                .stream()
                .collect(personNameCollector);//传入自定义的收集器
        System.out.println(names);// MAX | PETER | PAMELA | DAVID

    }

    private void test7(){
        persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);//Pamela
    }

    private void test8(){
        Person result =
                persons.stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.age += p2.age;
                            p1.name += p2.name;
                            return p1;
                        });
        System.out.format("name=%s; age=%s", result.name, result.age);
        //name=MaxPeterPamelaDavid; age=76
    }

    private void test9(){
        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
        System.out.println(ageSum);//76
    }

    private void test10(){
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }
}