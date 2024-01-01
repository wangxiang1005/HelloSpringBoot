package com.paladin.screw.controller;

import com.paladin.screw.Response;
import com.paladin.screw.service.ScrewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 数据库文档控住器
 **/
@RestController
@RequestMapping("/screw")
@Api(tags="数据库文档管理", description="生成数据库文档")
public class ScrewController {

    @Resource
    private ScrewService screwService;

    /**
     * 测试服务
     */
    @GetMapping("/test")
    @ApiOperation(value = "测试", notes = "测试方法的备注说明", httpMethod = "GET")
    public Response test() {
        return Response.success("查询成功", "我是测试服务");
    }

    /**
     * 生成数据库文档
     */
    @GetMapping("/contextLoads")
    @ApiOperation(value = "生成APM数据库文档", notes = "生成APM数据库文档", httpMethod = "GET")
    public Response contextLoads() {
        boolean contextLoads = screwService.contextLoads();
        if (contextLoads) {
            return Response.success("APM查询成功", contextLoads);
        }
        return Response.fail("APM查询失败");
    }
}