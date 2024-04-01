package com.paladin.stream.vo;

import lombok.Data;

@Data
public class Student {
    private String name;
    private String gender;
    private String department;
    private int age;

    public Student(String name, String gender, String department, int age) {
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }
}