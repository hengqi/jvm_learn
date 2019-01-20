package com.chenhl.jvm.classloader;

import java.lang.reflect.Method;

/**
 * 还是命名空间的问题，
 * 如果不删除classpath下的MyPerson的class文件的话，正常运行没问题
 * 如果删除了，那么就会抛异常
 * 不同的命名空间所加载的类，相互不可见
 */
public class MyTest20 {

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        Class<?> clazz1 = loader1.loadClass("com.chenhl.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.chenhl.jvm.classloader.MyPerson");

        // 因为都是由应用类加载器加载的，clazz2是被AppClassLoader所缓存的，所以本质上是同一个对象
        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);

        method.invoke(object1, object2);


    }
}
