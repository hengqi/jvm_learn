package com.chenhl.jvm.classloader;

/**
 * 接口初始化规则
 * 当一个接口在初始化时，并不要求其父接口都完成了初始化，
 * 只有在真正使用到父接口的时候（如引用到父接口中定义的常量时），才会初始化。
 */

public class MyTest5_1 {
    public static void main(String[] args) {
        System.out.println(MyParent5_1.thread);
    }
}

interface MyGrandpa5_1 {
    public static Thread thread = new Thread(){
        {
            System.out.println("MyGrandpa5_1 invoked");
        }
    };
}

interface MyParent5_1 extends MyGrandpa5_1 {// 可以证明初始化一个接口时并不会初始化它的父接口
    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5 invoked");
        }
    };
}


