package com.majun.metaClass;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by majun on 2018/7/20.
 */
public class MetaClassTest {

    private MetaClass metaClass;

    private ReflectorFactory reflectorFactory;

    @Before
    public void init(){
        reflectorFactory = new DefaultReflectorFactory();
        metaClass = MetaClass.forClass(Order.class,reflectorFactory);
    }

    @Test
    public void hasGetter(){

        Assert.assertTrue(metaClass.hasGetter("orderName")); //没有提供getter/setter方法
        Assert.assertTrue(metaClass.hasGetter("orderId"));
        Assert.assertTrue(metaClass.hasGetter("location.city"));
        Assert.assertTrue(!metaClass.hasGetter("test"));
        Assert.assertTrue(metaClass.hasGetter("items[0].itemId"));
        Assert.assertTrue(metaClass.hasGetter("items[0].location"));
        Assert.assertTrue(metaClass.hasGetter("items[0].location.city"));
        Assert.assertTrue(!metaClass.hasGetter("items.itemId")); //一定要带有index

    }

    @Test
    public void hasSetter(){
        Assert.assertTrue(metaClass.hasSetter("orderName"));
        Assert.assertTrue(metaClass.hasSetter("orderId"));
        Assert.assertTrue(metaClass.hasSetter("location"));
        Assert.assertTrue(metaClass.hasSetter("location.city"));
        Assert.assertTrue(metaClass.hasSetter("items"));
        Assert.assertTrue(!metaClass.hasSetter("items[0].itemId")); //应为set集合是不能指set其中第几个元素
        Assert.assertTrue(!metaClass.hasSetter("items.itemId")); //因为是个集合,没有这种写法

    }


    @Test
    public void metaClassForProperty(){

        MetaClass metaClass1 = metaClass.metaClassForProperty("orderName");
        Assert.assertNotNull(metaClass1);
        MetaClass metaClass2 = metaClass.metaClassForProperty("location");
        Assert.assertNotNull(metaClass2);

        metaClass.metaClassForProperty("location.city"); //抛异常,找不到该类getGett

    }


}
