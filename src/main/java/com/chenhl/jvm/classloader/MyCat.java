package com.chenhl.jvm.classloader;

public class MyCat {

    public MyCat() {
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());

        System.out.println("from Mycat: " + MySample.class);
    }
}
