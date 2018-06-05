package com.majun.exam.service;

import java.util.List;

/**
 * Created by majun on 05/06/2018.
 */
public interface IService<T> {

    List<T> selectAll();

}
