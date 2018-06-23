package com.rep.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Util {

    public static boolean checkObject(Object object){
        return null != object && !"".equals(object);
    }
    public static boolean checkArray(Object [] array){
        return null != array && array.length > 0;
    }
    public static Logger getLogger(Class clazz){
        return LoggerFactory.getLogger(clazz);
    }
    public static boolean checkList(List list){
        return null != list && !list.isEmpty();
    }

}
