package com.paladin.redis.config;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShopData {

    public static void main(String[] args) {
        List<Shop> data = ShopData.getData();
        for (Shop datum : data) {
            System.out.println(datum);
        }
    }

    public final static String SHOP_KEY = "shop:location";

    private final static List<Shop> SHOP_LIST;

    static {
        SHOP_LIST = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("D:\\JakartaEE\\IntelliJ-WorkSpace\\Hello\\HelloSpringBoot\\hello-springboot-redis\\src\\main\\resources\\data\\data.txt"));
            String line;
            do{
                line = reader.readLine();
                if (!StringUtils.isEmpty(line)){
                    String[] split = line.split(" ");
                    Shop shop = new Shop();
                    shop.setId(split[0]);
                    shop.setName(split[1]);
                    shop.setAccuracy(new BigDecimal(split[2]));
                    shop.setLatitude(new BigDecimal(split[3]));
                    shop.setStar(split[4]);
                    shop.setScore(new BigDecimal(split[5]));
                    SHOP_LIST.add(shop);
                }
            }while (line != null);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据列表
     * @return
     */
    public static List<Shop> getData(){
        return SHOP_LIST;
    }

    /**
     * 获取数据map结构，根据ID获取商家信息
     * @return
     */
    public static Map<String,Shop> getDataMap(){
        return SHOP_LIST.stream().collect(Collectors.toMap(Shop::getId, obj->obj));
    }
}