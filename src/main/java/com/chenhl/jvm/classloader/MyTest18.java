package com.chenhl.jvm.classloader;

public class MyTest18 {

    public static void main(String[] args) {
        // 根类加载器加载的位置
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展类加载器加载类的位置
        System.out.println(System.getProperty("java.ext.dirs"));
        // 系统类加载器加载类的位置
        System.out.println(System.getProperty("java.class.path"));
    }
}
