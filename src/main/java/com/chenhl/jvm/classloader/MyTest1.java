package com.chenhl.jvm.classloader;

/**
 * 主动使用与被动使用的关系
 *
 * 关于JVM参数：以-XX:开头，然后+/-表示开启和关闭后面的选项，option:表示具体的参数
 * -XX:+<option>,表示开启option选项
 * -XX:-<option>,表示关闭option选项
 * -XX:<option>=<value>,表示给option选项赋值为value
 */
public class MyTest1 {

    public static void main(String[] args) {

        /* 输出结果并没有子类的静态代码块语句
           说明子类并没有被初始化，没有被初始化，是否加载了呢，
           可以通过设置虚拟机参数-XX:TraceClassLoading用于追踪类的class信息并打印出来

           [Loaded com.chenhl.jvm.classloader.MyParent1 from file:/C:/Users/TF019387/myProjects/jvm_learn/out/production/classes/]
           [Loaded com.chenhl.jvm.classloader.MyChild1 from file:/C:/Users/TF019387/myProjects/jvm_learn/out/production/classes/]

           说明子类虽然没有被初始化，但也被加载到内存了

        */
        System.out.println(MyChild1.str);

        /*
           此时会输出子类的静态代码块语句
           说明，对于静态字段来说，只有直接定义了该字段的类才会被初始化，
           对于从父类类继承的str字段来说，并不是定义在子类里的，并不会导致子类的初始化
           而str2直接定义在子类里所以会导致子类的初始化。
           在初始化子类的时候属于主动使用中的一种，一个子类在初始化时，会导致所有的父类先初始化。
         */
//        System.out.println(MyChild1.str2);

    }
}

class MyParent1 {
    public static String str = "hello world";

    //初始化阶段会被执行
    static {
        System.out.println("MyParent1 static block.");
    }
}

class MyChild1 extends MyParent1 {

    public static String str2 = "welcome";

    static {
        System.out.println("MyChild static block.");
    }
}
