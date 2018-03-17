package com.chenhl.jvm.classloader;

/*
    对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为[Lcom.chenhl.jvm.classloader.MyParent4
    这种形式，动态生成的类型，其父类型就是Object

    对于数组来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型。


    助记符：
    anewarray：表示创建一个引用类型的（如类，接口，数组）数组，并将其引用值压入栈顶
    newarray：表示创建一个指定的原始类型（如int, float, char等）的数组，并将其引用值压入栈顶

 */
public class MyTest4 {

    public static void main(String[] args) {
        /*
            七种主动使用的第一种，--创建类的实例
         */
        MyParent4 myParent4 = new MyParent4();

        System.out.println("-------");
        /*
            也是对这个类的主动使用，但不是首次主动使用，因此不会导致类的初始化
         */
//        new MyParent4();

        /*
            不会导致类的初始化
            对于数组，java vm 会在运行期创建出来的数组类型 --[Lcom.chenhl.jvm.classloader.MyParent4
            二维数组的类型 --[[Lcom.chenhl.jvm.classloader.MyParent4
         */
        MyParent4[] myParent4s = new MyParent4[1];
        System.out.println(myParent4s.getClass());

        MyParent4[][] myParent4s1 = new MyParent4[1][1];
        System.out.println(myParent4s1.getClass());

        // 父类型是Object
        System.out.println(myParent4s.getClass().getSuperclass());

        System.out.println("----");

        //对于原生类型的数组   表示为[I这种形式
        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());


        byte[] bytes = new byte[1];
        System.out.println(bytes.getClass());//[B

        char[] chars = new char[1];
        System.out.println(chars.getClass());//[C

        short[] shorts = new short[1];
        System.out.println(shorts.getClass());//[S

        boolean[] booleans = new boolean[1];
        System.out.println(booleans.getClass());//[Z


    }
}


class MyParent4 {

    static {
        System.out.println("MyParent4 static block");
    }
}