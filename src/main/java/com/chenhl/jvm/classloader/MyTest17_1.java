package com.chenhl.jvm.classloader;

/*
    关于类加载器的重要说明：
    子加载器所加载的类能够访问父加载器所加载的类
    父加载器所加载的类无法访问子加载器所加载的类
 */
public class MyTest17_1 {

    public static void main(String[] args) throws Exception{
        MyCustomClassLoader loader1 = new MyCustomClassLoader("loader1");

        loader1.setPath("C:\\Users\\chenhl\\Desktop\\");
        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MySample");


        System.out.println("clazz: " + clazz.hashCode());
        // 如果注释掉该行，那么并不会实例化MySample对象，
        Object object = clazz.newInstance();
    }
}
