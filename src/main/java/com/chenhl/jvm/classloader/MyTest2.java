package com.chenhl.jvm.classloader;

import java.util.Random;

/**
 * 常量的本质与反编译及助记符
 */
public class MyTest2 {

    public static void main(String[] args) {
        System.out.println(MyParent2.a);
//        System.out.println(MyParent2.s);
//        System.out.println(MyParent2.i);
//        System.out.println(MyParent2.m);
    }
}

class MyParent2 {


//    public static String str = "hello world";
//    public static final String str = "hello world";
    public static final int a = new Random().nextInt(3);

//    public static final short s = 7;

//    public static final int i = 128;

//    public static final int m = -2;

    static {
        System.out.println("MyParent2 static block");
    }
}

/*
   使用final关键字和不使用final关键字的区别：
   常量在编译阶段，会存入到调用这个常量的方法所在的类的常量池中。
   本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化

   注：这里指的是，将常量str存放到了MyTest2的常量池中了，之后MyTest2与MyParent2就没有任何关系了
   甚至，我们可以将MyParent2的class文件删除


   助记符：
   ldc表示将int,float,或者是String类型的常量值从常量池中推送至栈顶
   bipush表示将单字节（-128 ~ 127）的常量值推送至栈顶
   sipush表示将一个短整型（-32768 ~ 32767）常量值推送至栈顶
   iconst_1表示将int类型1推送至栈顶（其中后面的数字是从-m1~5,m1代表-1）
*/