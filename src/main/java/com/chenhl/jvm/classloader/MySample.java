package com.chenhl.jvm.classloader;

public class MySample {

    public MySample() {
        System.out.println("MySample is loaded by: " + this.getClass().getClassLoader());

        new MyCat();

        // 如果删掉classpath下的MySample的class文件的话，不会抛出classNotFoundException的异常，因为命名空间，
        // 由子加载器加载的类能看到由父加载器加载的类
        System.out.println("from MySample: " + MyCat.class);
    }
}
