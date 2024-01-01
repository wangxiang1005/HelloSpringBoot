package com.paladin.screw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2的配置文件
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * swagger2的配置文件,这里可以配置swagger2的一些基本的内容,比如扫描的包等等
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.zrj.screw.controller")).paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("数据库文档管理")
                // 创建人信息
                .contact(new Contact("jerry", "http://jerry.com", "jerry@163.com"))
                // 版本号
                .version("Screw1.0")
                // 描述
                .description("数据库文档生成工具")
                .build();
    }
}