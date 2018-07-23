package com.majun.typeParameterResover;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.apache.ibatis.reflection.TypeParameterResolver;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by majun on 2018/7/18.
 */
public class TestType {

    SubClassA<Long> sa = new SubClassA<>();

    public static void main(String[] args) throws NoSuchFieldException {
        Field f = ClassA.class.getDeclaredField("map");

        System.out.println(f.getGenericType());
        System.out.println(f.getGenericType() instanceof ParameterizedType);

        System.out.println(TestType.class.getDeclaredField("sa").getType());
        System.out.println(TestType.class.getDeclaredField("sa").getGenericType());

        Type type = TypeParameterResolver.resolveFieldType(f,TestType.class.getDeclaredField("sa").getGenericType());
        //Type type = TypeParameterResolver.resolveFieldType(f,SubClassA.class);  //这样写会丢失Map中的Long参数,变成Object Type


        System.out.println(type.getClass());

        ParameterizedType parameterizedType = (ParameterizedType)type;
        System.out.println(parameterizedType.getRawType());
        System.out.println(parameterizedType.getOwnerType());

        for(Type t : parameterizedType.getActualTypeArguments()){
            System.out.println(t);
        }
    }

}
