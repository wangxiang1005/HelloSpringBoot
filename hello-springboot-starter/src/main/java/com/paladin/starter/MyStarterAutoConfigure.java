package com.paladin.starter;

import com.paladin.starter.gaode.GaodeStarterServiceImpl;
import com.paladin.starter.geoip.GeoIPStarterServiceImpl;
import com.paladin.starter.region.RegionIPStarterServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(value = {IPStarterService.class, GaodeStarterServiceImpl.class, GeoIPStarterServiceImpl.class, RegionIPStarterServiceImpl.class})
public class MyStarterAutoConfigure {

    @Bean // 实例化IMyStarterService并载入Spring IoC容器
    @ConditionalOnMissingBean // 当spring上下文中不存在bean时实现自动配置
    IPStarterService gaodeStarterServiceImpl() {
        return new GaodeStarterServiceImpl();
    }

    @Bean // 实例化IMyStarterService并载入Spring IoC容器
    @ConditionalOnMissingBean // 当spring上下文中不存在bean时实现自动配置
    IPStarterService geoIPStarterServiceImpl() {
        return new GeoIPStarterServiceImpl();
    }

    @Bean // 实例化IMyStarterService并载入Spring IoC容器
    @ConditionalOnMissingBean // 当spring上下文中不存在bean时实现自动配置
    IPStarterService regionIPStarterServiceImpl() {
        return new RegionIPStarterServiceImpl();
    }
}