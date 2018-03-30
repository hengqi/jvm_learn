package com.chenhl.jvm.classloader;

/*
    当一个接口在初始化时，并不要求其父接口都完成了初始化，
    只有在真正使用到父接口的时候（如引用到父接口中定义的常量时），才会初始化。

 */
public class MyTest5 {

    public static void main(String[] args) {
//        System.out.println(MyChild5.b);
        System.out.println(MyParent5_1.thread);
    }
}

class MyGrandPa {


    public static Thread thread = new Thread(){
        {
            System.out.println("MyGrandPa invoked!");
        }
    };
}

class MyParent5 extends MyGrandPa{


    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5 invoked!");
        }
    };
}

class MyChild5 extends MyParent5{

    public static int b =6;
}


interface MyGrandPa5_1 {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyGrandPa5_1 invoked!");
        }
    };
}

interface MyParent5_1 extends MyGrandPa5_1 {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent5_1 invoked!");
        }
    };
}