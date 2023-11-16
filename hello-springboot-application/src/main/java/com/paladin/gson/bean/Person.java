package com.paladin.gson.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    private String name;
    private Integer age;
    private Job job;
    private List<String> nickName;

    public Person(int id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}