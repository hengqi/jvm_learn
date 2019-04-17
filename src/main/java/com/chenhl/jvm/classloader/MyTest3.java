package com.chenhl.jvm.classloader;

import java.util.UUID;

/**
 * 编译期常量与运行期常量的区别
 */
public class MyTest3 {

    public static void main(String[] args) {

        System.out.println(MyParent3.str);
    }
}


class MyParent3 {

//    当一个常量的值在编译期间无法确定时，那么其值就不会放到调用类的常量池中，这时，在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类初始化。
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static block");
    }
}
