package com.majun.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<T> selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return returnPage(mapper.select(null));
    }

    protected PageInfo<T> returnPage(List<T> list){
        return new PageInfo<>(list);
    }

}
