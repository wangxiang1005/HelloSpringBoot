package com.paladin.starter.vo;

import lombok.Data;

@Data
public class IpAnalyzeGaodeResponse {

    /**
     * 返回结果状态值,值为0或1,0表示失败；1表示成功
     */
    private String status;

    /**
     * 返回状态说明，status为0时,info返回错误原因,否则返回“OK
     */
    private String info;

    /**
     * 返回状态说明,10000代表正确,详情参阅info状态表
     */
    private String infocode;

    /**
     * 若为直辖市则显示直辖市名称；
     *
     * 如果在局域网 IP网段内,则返回“局域网”
     *
     * 非法IP以及国外IP则返回空
     */
    private String province;

    /**
     * 若为直辖市则显示直辖市名称；
     *
     * 如果为局域网网段内IP或者非法IP或国外IP,则返回空
     */
    private String city;

    /**
     * 城市的adcode编码
     */
    private String adcode;

    /**
     * 所在城市矩形区域范围,所在城市范围的左下右上对标对,如：116.0119343,39.66127144;116.7829835,40.2164962
     */
    private String rectangle;
}