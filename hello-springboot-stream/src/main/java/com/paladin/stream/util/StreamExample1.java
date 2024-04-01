package com.paladin.stream.util;

import com.paladin.stream.vo.Employee;
import java.util.*;
import java.util.stream.Collectors;

public class StreamExample1 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Sales", 6000),
                new Employee("Bob", "Sales", 5000),
                new Employee("Charlie", "HR", 5500),
                new Employee("David", "HR", 6500),
                new Employee("Ella", "IT", 7500),
                new Employee("Frank", "IT", 7000),
                new Employee("Grace", "HR", 4500)
        );

        // 筛选出薪水大于5000的员工
        List<Employee> highPaidEmployees = employees.stream()
                .filter(e -> e.getSalary() > 5000) //filter()方法对员工列表进行筛选
                .collect(Collectors.toList());

        System.out.println("高薪员工：");
        highPaidEmployees.forEach(System.out::println);

        // 对员工列表进行分组和聚合
        Map<String, DoubleSummaryStatistics> statsByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, //使用groupingBy()方法对员工列表进行分组
                        Collectors.mapping(Employee::getSalary, Collectors.summarizingDouble(Integer::intValue))));//使用mapping()方法进行值的转换和计算操作

        System.out.println("按部门统计：");
        statsByDepartment.forEach((department, stats) -> {
            System.out.printf("%s：平均薪水 %.2f，员工数量 %d\n",
                    department, stats.getAverage(), stats.getCount());
        });

        // 对符合条件的员工按照薪水从高到低排序
        List<Employee> sortedEmployees = employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()))//使用sorted()方法对员工列表进行排序操作
                .collect(Collectors.toList());

        System.out.println("按薪水从高到低排序：");
        sortedEmployees.forEach(System.out::println);

        // 计算员工薪水的平均值和最高值
        OptionalDouble avgSalary = employees.stream()
                .mapToInt(Employee::getSalary)
                .average();

        int maxSalary = employees.stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);

        System.out.printf("员工平均薪水：%.2f，最高薪水：%d", avgSalary.getAsDouble(), maxSalary);
    }
}