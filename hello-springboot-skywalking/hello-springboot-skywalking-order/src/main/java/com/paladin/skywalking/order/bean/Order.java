package com.paladin.skywalking.order.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "order")
public class Order {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "item_id")
    private Integer itemId;
    @TableField(value = "count")
    private Integer count;
}