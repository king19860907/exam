package com.majun.typeParameterResover;

import java.util.Map;

/**
 * Created by majun on 2018/7/18.
 */
public class ClassA<K,V> {

    protected Map<K,V> map;

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }
}
