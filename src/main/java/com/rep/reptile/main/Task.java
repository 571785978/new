package com.rep.reptile.main;

import com.google.common.collect.Maps;
import com.rep.common.BaseRepository;
import com.rep.common.SpringUtil;
import com.rep.reptile.annotations.Href;
import com.rep.reptile.annotations.Next;
import com.rep.reptile.annotations.Selector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.rep.common.Util.*;

public class Task {

    public static Map<String,Object> queue = Maps.newConcurrentMap();

    public static void start(){
        if(!queue .isEmpty()){
            queue .entrySet().stream().forEach(entry -> {
                resolve(entry.getValue(),getDocument(entry.getKey()));
                try {
                    Method method = entry.getValue().getClass().getMethod("getRepository");
                    method.setAccessible(true);
                    Object repository = method.invoke(entry.getValue());
                    if(checkObject(repository)){
                        ((BaseRepository)SpringUtil.getBean(repository.toString())).save(entry.getValue());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
            emptyQueue ();
        }
    }

    private static Document getDocument(String url){
        try {
            Document document = Jsoup.parse(new URL(url),(50000));
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object resolve(Object obj, Document document){
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if(checkArray(fields) && checkObject(document)){
            Arrays.asList(fields).stream().forEach(field -> {
                field.setAccessible(true);
                if(field.isAnnotationPresent(Selector.class)){
                    Selector selector = field.getAnnotation(Selector.class);
                    Elements elements = document.select(selector.value());
                    setElement(elements,selector,field,obj);
                }
                if(field.isAnnotationPresent(Next.class)){
                    setNext(field,document,obj);
                }
                if (field.isAnnotationPresent(Href.class)){
                    setHref(clazz,obj,field,document);
                }
            });
        }
        return obj;
    }

    private static void setElement(Elements elements,Selector selector,Field field,Object obj){
        Object element = null;
        if(checkList(elements) && checkObject(selector.attr())){
            if(field.getType() == List.class){
                List attrs = new ArrayList();
                elements.stream().forEach(ele -> {
                    String attr;
                    if("text".equals(selector.attr())){
                        attr = ele.text();
                    }else {
                        attr = ele.attr(selector.attr());
                    }
                    attrs.add(attr);
                });
                element = attrs;
            }else {
                if("text".equals(selector.attr())){
                    element = elements.get(0).text();
                }else {
                    element = elements.get(0).attr(selector.attr());
                }

            }
        }
        if(checkObject(element)){
            try {
                field.set(obj,element);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void emptyQueue (){
        queue .clear();
    }

    private static void setNext(Field field, Document document,Object obj){
        try {
            Object next = null;
            if(field.getType() == java.util.List.class){
                List list = new ArrayList();
                // 如果是List类型，得到其Generic的类型
                Type genericType = field.getGenericType();
                if(checkObject(genericType)) return;
                // 如果是泛型参数的类型
                if(genericType instanceof ParameterizedType){
                    ParameterizedType pt = (ParameterizedType) genericType;
                    //得到泛型里的class类型对象
                    Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];
                    Object result = resolve(genericClazz.newInstance(),document);
                    list.add(result);
                }
                next = list;
            }
            field.set(obj,next);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setHref(Class clazz,Object obj,Field field,Document document){
        try {
            Method method = clazz.getDeclaredMethod("getUrl");
            method.setAccessible(true);
            Object url = method.invoke(obj);
            Href href = field.getAnnotation(Href.class);
            Elements elements = document.select(href.hrefSelector());
            if(checkObject(url)){
                elements.stream().forEach(element -> {
                    String newUrl = url.toString();
                    newUrl += element.attr(href.attr());
                    if(checkObject(newUrl)){
                        try {
                            Object next = resolve(field.getType().newInstance(), getDocument(newUrl));
                            field.set(obj,next);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
