package com.chenhl.jvm.classloader;

/*
    当一个接口在初始化时，并不要求其父接口都完成了初始化，
    只有在真正使用到父接口的时候（如引用到父接口中定义的常量时），才会初始化。

 */
public class MyTest5 {

    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

interface MyParent5 {

    public static int a = 5;
}

interface MyChild5 extends MyParent5{

    public static int b =6;
}