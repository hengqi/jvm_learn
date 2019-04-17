package com.chenhl.jvm.classloader;

import java.io.*;

/**
 * 自定义类加载器 类的卸载
 *
 * ClassLoader虽然是抽象类，但没有抽象方法
 */
public class MyTest16_2 extends ClassLoader {

    private String classLoaderName;

    private String path;// 指定从哪里加载类

    private final String fileExtension = ".class";

    public MyTest16_2(String classLoaderName) {
        super();//将系统类加载器当做该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16_2(ClassLoader parent, String classLoaderName) {
        super(parent);//显示指定父加载器
        this.classLoaderName = classLoaderName;
    }


    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + this.classLoaderName);
        byte[] data = loadClassData(className);
        return defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        name = name.replace(".", "\\");
        try {
            is = new FileInputStream(new File(this.path + name + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }

            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public String toString() {
        return "MyTest16{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }


    //类的卸载
    // -XX:+TraceClassUnloading
    public static void main(String[] args)throws Exception {
        MyTest16_2 loader1 = new MyTest16_2("loader1");
//        loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());
        System.out.println("------");


        loader1 = null;
        clazz = null;
        object = null;

        System.gc();

//        Thread.sleep(100000);

        loader1 = new MyTest16_2("loader1");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

    }

}
