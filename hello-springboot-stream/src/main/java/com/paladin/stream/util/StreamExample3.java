package com.paladin.stream.util;

import com.paladin.stream.vo.Student;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExample3 {
    public static void main(String[] args) {

        /**
         * 使用groupingBy()方法来实现多级分组,同时也可以使用嵌套的groupingBy()方法来实现多个分组条件的组合.
         *
         */

        // 准备学生数据
        List<Student> students = Arrays.asList(
                new Student("Alice", "F", "Computer Science", 21),
                new Student("Bob", "M", "Mathematics", 20),
                new Student("Charlie", "M", "Physics", 23),
                new Student("David", "M", "Computer Science", 22),
                new Student("Ella", "F", "Physics", 20),
                new Student("Frank", "M", "Mathematics", 19),
                new Student("Grace", "F", "Computer Science", 21),
                new Student("Henry", "M", "Physics", 22)
        );

        // 按院系和性别进行分组，并计算每个组的平均年龄和学生数量
        Map<String, Map<String, Double>> statsByDepartmentAndGender = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment,
                         Collectors.groupingBy(Student::getGender,
                         Collectors.mapping(Student::getAge, Collectors.averagingInt(Integer::intValue)))));

        // 输出统计结果
        System.out.println("按院系和性别统计：");
        statsByDepartmentAndGender.forEach((department, statsByGender) -> {
            System.out.println(department + ":");
            statsByGender.forEach((gender, stats) -> {
                System.out.printf("  %s：平均年龄 %.2f，人数 %d\n",
                        gender, stats, students.stream()
                                .filter(s -> s.getDepartment().equals(department))
                                .filter(s -> s.getGender().equals(gender))
                                .count());
            });
        });
    }
}