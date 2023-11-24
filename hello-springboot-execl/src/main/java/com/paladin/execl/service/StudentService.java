package com.paladin.execl.service;

import com.paladin.execl.vo.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StudentService {

    List<String> classNames;

    List<String> gradeNames;

    {
        classNames = new ArrayList<>();
        classNames.add("一班");
        classNames.add("二班");
        classNames.add("三班");
        gradeNames = new ArrayList<>();
        gradeNames.add("2017级");
        gradeNames.add("2018级");
        gradeNames.add("2019级");
    }

    public List<Student> getStudentData(){
        List<Student> list = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            Student student = new Student();
            student.setName("李祥"+i);
            student.setClassName(getClassName());
            student.setGradeName(getGradeName());
            student.setSex("男");
            student.setAge(18);
            list.add(student);
        }
        return list;
    }

    /**
     * 获取班级
     * @return
     */
    private String getClassName(){
        Random rand = new Random();
        return classNames.get(rand.nextInt(classNames.size()));
    }

    /**
     * 获取年级
     * @return
     */
    private String getGradeName(){
        Random rand = new Random();
        return gradeNames.get(rand.nextInt(gradeNames.size()));
    }
}