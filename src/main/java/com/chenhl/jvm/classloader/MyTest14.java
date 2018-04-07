package com.chenhl.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest14 {

    public static void main(String[] args) throws IOException {
        // 获取classLoader的第二种方法
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String resourceName = "com/chenhl/jvm/classloader/MyTest13.class";

        Enumeration<URL> resources = contextClassLoader.getResources(resourceName);

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(url);
        }

        System.out.println("---------");

//        Class<?> clazz = MyTest14.class;
        Class<?> clazz = String.class;
        System.out.println(clazz.getClassLoader());
    }
}
