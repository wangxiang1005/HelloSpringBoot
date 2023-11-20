package com.paladin.coupon.server.util;

import com.paladin.coupon.server.vo.Coupon;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CouponUtil {

    private final static List<Coupon> couponList = new ArrayList<>();

    static {
        Coupon coupon1 = new Coupon();
        coupon1.setAmount(new BigDecimal(50));
        coupon1.setFullReduction(new BigDecimal(300));
        coupon1.setId(1);
        coupon1.setName("满300减50券");
        coupon1.setStartTime(LocalDateTime.parse("2023-10-20 16:25:49", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        coupon1.setEndTime(LocalDateTime.parse("2024-11-11 16:25:49", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Coupon coupon2 = new Coupon();
        coupon2.setAmount(new BigDecimal(100));
        coupon1.setFullReduction(new BigDecimal(500));
        coupon2.setId(2);
        coupon2.setName("满500减100券");
        coupon2.setStartTime(LocalDateTime.parse("2023-10-20 16:25:49", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        coupon2.setEndTime(LocalDateTime.parse("2024-11-11 16:25:49", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Coupon coupon3 = new Coupon();
        coupon3.setAmount(new BigDecimal(200));
        coupon1.setFullReduction(new BigDecimal(800));
        coupon3.setId(3);
        coupon3.setName("满800减100券");
        coupon3.setStartTime(LocalDateTime.parse("2023-10-20 16:25:49", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        coupon3.setEndTime(LocalDateTime.parse("2024-11-11 16:25:49", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        couponList.add(coupon3);
        couponList.add(coupon2);
        couponList.add(coupon1);
    }

    /**
     * 获取具体某个优惠券
     * @param couponId
     * @return
     */
    public static Coupon getCouponById(Integer couponId){
        return couponList.stream().filter(obj-> Objects.equals(obj.getId(), couponId)).collect(Collectors.toList()).get(0);
    }
}