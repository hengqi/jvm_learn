package com.chenhl.jvm.classloader;

public class MyTest13 {

    public static void main(String[] args) throws Exception{
        // 获取系统类加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        System.out.println(loader);

        while (null != loader) {
            loader = loader.getParent();
            System.out.println(loader);
        }
    }
}
