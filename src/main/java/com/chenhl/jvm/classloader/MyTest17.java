package com.chenhl.jvm.classloader;

public class MyTest17 {

    public static void main(String[] args) throws Exception{
        MyTest16 loader1 = new MyTest16("loader1");
        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MySample");

        System.out.println("clazz: " + clazz.hashCode());
        // 如果注释掉该行，那么并不会实例化MySample对象，
        Object object = clazz.newInstance();
    }
}
