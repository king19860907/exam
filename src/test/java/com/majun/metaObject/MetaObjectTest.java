package com.majun.metaObject;

import com.majun.metaClass.Item;
import com.majun.metaClass.Order;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by majun on 2018/7/21.
 */
public class MetaObjectTest {

    private MetaObject metaObject;

    private Order order;

    @Before
    public void init(){
        order = new Order();
        metaObject = MetaObject.forObject(order,new DefaultObjectFactory(),new DefaultObjectWrapperFactory(),new DefaultReflectorFactory());
    }

    @Test
    public void test(){

        metaObject.setValue("orderId",3L);
        Assert.assertTrue(3==order.getOrderId());

        //metaObject.setValue("items",new ArrayList<Item>());
        //order.getItems().add(null);
        //metaObject.setValue("items[0]",new Item()); // 没有初始化过,所以这里会报错
        //metaObject.setValue("items[0].id",4L); // 没有初始化过,所以这里会报错
        metaObject.setValue("location.city","shanghai");

        System.out.println(metaObject.getValue("orderId"));
        System.out.println(metaObject.getValue("location.city"));

        List<Item> itemList = new ArrayList<>();
        MetaObject listMeta = MetaObject.forObject(itemList,new DefaultObjectFactory(),new DefaultObjectWrapperFactory(),new DefaultReflectorFactory());

        Item item = new Item();
        item.setItemId(10000L);
        listMeta.add(item);

        metaObject.setValue("items",itemList);

        System.out.println(metaObject.getValue("items"));
        System.out.println(metaObject.getValue("items[0].itemId"));
        System.out.println(order);


    }

}
