package com.paladin.redis.service;

import com.paladin.redis.config.GeoComponent;
import com.paladin.redis.config.Shop;
import com.paladin.redis.config.ShopData;
import com.paladin.redis.config.ShopVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.paladin.redis.config.ShopData.SHOP_KEY;

@Service
public class ShopService {

    @Autowired
    private GeoComponent geoComponent;

    public void syncShopLocationData() {
        List<Shop> data = ShopData.getData();
        System.out.println("data.size()======>>>>>>>>"+data.size());
        //地理位置信息同步到Redis
        data.forEach(obj->{
            double accuracy = obj.getAccuracy().doubleValue();
            double latitude = obj.getLatitude().doubleValue();
            String id = obj.getId();
            System.out.println("id======>>>>>>>>"+id);
            geoComponent.geoAdd(SHOP_KEY, accuracy, latitude, id);
        });
    }

    public Shop getShopById(String id) {
        return ShopData.getDataMap().get(id);
    }

    public List<ShopVO> getShopListByLocation(Map<String, Object> user) {
        List<ShopVO> shopVOS = new ArrayList<>();
        // 获取用户的坐标位置
        double accuracy = (double) user.get("accuracy");
        double latitude = (double) user.get("latitude");
        String userId = String.valueOf(user.get("id"));
        // 将用户位置加入到Redis
        geoComponent.geoAdd(ShopData.SHOP_KEY, accuracy, latitude, userId);
        // 获取用户附近的门店
        List<String> shopIds = geoComponent.geoRadiusByMember(ShopData.SHOP_KEY, userId, 10, Metrics.KILOMETERS);
        for (String shopId : shopIds) {
            //如果是当前userId则直接跳出
            if (shopId.equals(userId)) {
                continue;
            }
            //获取shop信息
            Shop shop = this.getShopById(shopId);
            ShopVO shopVO = new ShopVO();
            BeanUtils.copyProperties(shop, shopVO);
            //获取两点的距离
            double distance = geoComponent.geoDist(ShopData.SHOP_KEY, userId, shopId, Metrics.KILOMETERS).getValue();
            //保留一位小数
            distance = new BigDecimal(distance).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
            shopVO.setDistance(distance);
            shopVOS.add(shopVO);
        }
        // 删除Redis中用户位置。
        geoComponent.geoDelete(ShopData.SHOP_KEY, userId);
        //排序 返回
        return shopVOS.stream().sorted(Comparator.comparingDouble(ShopVO::getDistance)).collect(Collectors.toList());
    }
}