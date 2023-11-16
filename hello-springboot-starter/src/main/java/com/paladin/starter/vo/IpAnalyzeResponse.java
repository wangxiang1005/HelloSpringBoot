package com.paladin.starter.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IpAnalyzeResponse {

    /**
     * 状态码
     */
    private String status;

    /**
     * 是否成功
     */
    private Boolean successFlag;

    /**
     * 失败原因
     */
    private String msg;

    /**
     * 省份名称
     */
    private String province;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 经度
     */
    private Double x;

    /**
     * 维度
     */
    private Double y;
}