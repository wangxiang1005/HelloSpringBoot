package com.paladin.skywalking.cargo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.paladin.skywalking.cargo.bean.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ItemMapper extends BaseMapper<Item> {
    @Update("update item set count=count-#{count} where id=#{id} and count>=#{count}")
    Integer updateItem(@Param("id") Integer id, @Param("count") Integer count);
}