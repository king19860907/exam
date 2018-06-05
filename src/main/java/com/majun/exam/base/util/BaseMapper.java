package com.majun.exam.base.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by majun on 05/06/2018.
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
