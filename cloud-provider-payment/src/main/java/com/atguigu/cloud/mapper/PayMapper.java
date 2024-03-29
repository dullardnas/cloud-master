package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Pay;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface PayMapper extends Mapper<Pay>, InsertListMapper<Pay> {
}