package com.paladin.gson;

import com.paladin.gson.bean.Job;
import com.paladin.gson.bean.Person;
import com.paladin.gson.bean.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Object2JsonByGson {
    public static void main(String[] args) {
        // 简单对象转json
        User user = new User(1, "张三", 18);
        String json = GsonUtil.simpleObjToJson(user);
        System.out.println("简单对象转json===>>>>"+json);//{"id":1,"name":"张三","age":18}

        // 简单对象转json
        user = new User("张三");
        json = GsonUtil.simpleObjToJson(user);
        System.out.println(json);//{"id":0,"name":"张三"}


        Person person = new Person();
        person.setId(1);
        person.setName("张三");
        person.setAge(18);
        Job job = new Job();
        job.setJobName("Java开发");
        job.setCompany("某知名大厂");
        person.setJob(job);
        List<String> list = Arrays.asList("张三", "法外狂徒", "传奇人物");
        person.setNickName(list);
        json = GsonUtil.complexObjToJson(user);
        System.out.println(json);
        //输出：
        /*{
            "id":1,
                "name":"张三",
                "age":18,
                "job":{
            "jobName":"Java开发",
                    "company":"某知名大厂"
        },
            "nickName":[
            "张三",
                    "法外狂徒",
                    "传奇人物"
            ]
        }*/

        List<String> ls = new ArrayList<>();
        ls.add("zhangsan");
        ls.add("lisi");
        ls.add("wangwu");
        json = GsonUtil.listToJson(ls);
        System.out.println(json);//["zhangsan","lisi","wangwu"]
    }
}