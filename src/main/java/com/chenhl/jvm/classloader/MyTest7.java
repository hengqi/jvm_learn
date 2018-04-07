package com.chenhl.jvm.classloader;

public class MyTest7 {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader()); // 根类加载器

        Class<?> clazz2 = Class.forName("com.chenhl.jvm.classloader.C");
        System.out.println(clazz2.getClassLoader());// 系统类加载器
    }
}

class C {

}
