package com.paladin.gson;

import com.paladin.gson.bean.Person;
import com.paladin.gson.bean.User;

public class Json2ObjectByGson {
    public static void main(String[] args) {
        String json = "{\"id\":1,\"name\":\"张三\",\"age\":18}";
        User user = GsonUtil.simpleJsonToObj(json, User.class);
        System.out.println(user);//User(id=1, name=张三, age=18)

        json = "{\"id\":1,\"name\":\"张三\",\"age\":18,\"job\":{\"jobName\":\"Java开发\",\"company\":\"某知名大厂\"},\"nickName\":[\"张三\",\"法外狂徒\",\"传奇人物\"]}";
        Person person = GsonUtil.complexJsonToObj(json, Person.class);
        System.out.println(person);//User(id=1, name=张三, age=18, job=Job(jobName=Java开发, company=某知名大厂), nickName=[张三, 法外狂徒, 传奇人物])

        json = "[{\"id\":1,\"name\":\"zhangsan\",\"age\":18},{\"id\":2,\"name\":\"sili\",\"age\":28}]";
        System.out.println(GsonUtil.jsonToList(json, User.class));//json [{id=1.0, name=zhangsan, age=18.0}, {id=2.0, name=sili, age=28.0}]
    }
}