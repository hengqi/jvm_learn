package com.chenhl.jvm.classloader;

public class MyTest15 {

    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass());//[Ljava.lang.String;
        System.out.println(strings.getClass().getClassLoader());//null

        System.out.println("-----");

        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println(myTest15s.getClass().getClassLoader());//AppClassLoader

        System.out.println("-----");

        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());//null
    }
}

/*
    关于数组类型的类加载器：
    1、对于数组类型的类，是由JVM在运行期自动的生成的
    2、对于引用类型的数组，加载它的类加载器和加载数组中元素的类加载器相同
    3、对于原生类型的数组，没有类加载器
 */