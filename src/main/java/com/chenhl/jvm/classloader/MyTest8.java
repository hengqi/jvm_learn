package com.chenhl.jvm.classloader;

import java.util.Random;

public class MyTest8 {

    public static void main(String[] args) {
//        System.out.println(FinalTest.x);
        System.out.println(FinalTest.y);
    }
}


class FinalTest {

    public static final int x = new Random().nextInt(3);
    public static final int y = 3; // 编译器常量
//    public static int y = 3;

    static {
        System.out.println("FinalTest invoked!");
    }
}