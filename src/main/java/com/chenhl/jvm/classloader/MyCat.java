package com.chenhl.jvm.classloader;

public class MyCat {

    public MyCat() {
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());

        // 如果删掉classpath下的MySample的class文件的话，会抛出classNotFoundException的异常，因为命名空间，
        // 由父加载器加载的类看不到由子加载器加载的类
//        System.out.println("from Mycat: " + MySample.class);
    }
}
