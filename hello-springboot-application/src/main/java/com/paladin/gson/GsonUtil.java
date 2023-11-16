package com.paladin.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GsonUtil {
    /**
     * 简单对象转Json
     *
     */
    public static String simpleObjToJson(Object obj) {
        if (Objects.isNull(obj)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 复杂对象转Json
     *
     */
    public static String complexObjToJson(Object obj) {
        if (Objects.isNull(obj)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * list To Json
     *
     */
    public static String listToJson(List list) {
        if (Objects.isNull(list)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 简单Json转对象
     *
     */
    public static <T> T simpleJsonToObj(String json, Class<T> cls) {
        Gson gson = new Gson();
        if (Objects.isNull(json)) return null;
        T obj = gson.fromJson(json, cls);
        if (Objects.isNull(obj)) {
            return null;
        } else {
            return obj;
        }
    }

    /**
     * 复杂Json转对象
     *
     */
    public static <T> T complexJsonToObj(String json, Class<T> cls) {
        Gson gson = new Gson();
        if (Objects.isNull(json)) return null;
        T obj = gson.fromJson(json, cls);
        if (Objects.isNull(obj)) {
            return null;
        } else {
            return obj;
        }
    }

    /**
     * json to list
     *
     */
    public static <T> T jsonToList(String json,Class<T> cls) {
        if (Objects.isNull(json)) return null;
        try {
            Gson gson = new Gson();
            // 需要注意这里的type
            Type type = new TypeToken<ArrayList<T>>(){}.getType();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}