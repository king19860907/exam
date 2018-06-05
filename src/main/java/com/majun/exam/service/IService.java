package com.majun.exam.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by majun on 05/06/2018.
 */
public interface IService<T> {

    List<T> selectAll();

    PageInfo<T> selectPage(int pageNum, int pageSize);

}
