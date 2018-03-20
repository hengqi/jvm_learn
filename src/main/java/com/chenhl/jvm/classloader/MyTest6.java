package com.chenhl.jvm.classloader;

public class MyTest6 {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(Singleton.counter1);
        System.out.println(Singleton.counter2);
    }
}

class Singleton {

    public static  int counter1;

    private static Singleton singleton = new Singleton();// 此时counter1 为1和counter2为0

    public static int counter2 = 0;

//    private static Singleton singleton = new Singleton();// 此时counter1和counter2都是1

    private Singleton() {
        counter1 ++;
        counter2 ++;
    }

    public static Singleton getInstance() {
        return singleton;
    }
}
