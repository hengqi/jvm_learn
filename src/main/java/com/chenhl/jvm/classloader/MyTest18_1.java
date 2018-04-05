package com.chenhl.jvm.classloader;

/*
如果将要加载的类比如MyTest1放到根类加载器加载时所寻找的目录下，则根类加载器会加载MyTest1
 */
public class MyTest18_1 {

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");

        loader1.setPath("C:\\Users\\TF019387\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: " + clazz.hashCode());

        System.out.println("class loader: " + clazz.getClassLoader());
    }
}
