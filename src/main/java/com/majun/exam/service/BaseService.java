package com.majun.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by majun on 05/06/2018.
 */
public abstract class BaseService<T> implements IService<T>  {

    @Autowired
    private Mapper<T> mapper;

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }
}
