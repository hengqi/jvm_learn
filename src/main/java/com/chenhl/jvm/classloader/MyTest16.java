package com.chenhl.jvm.classloader;

import java.io.*;

/**
 * 自定义类加载器
 */
public class MyTest16 extends ClassLoader {

    private String classLoaderName;

    private final String fileExtension = ".class";

    public MyTest16(String classLoaderName) {
        super();//将系统类加载器当做该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        super(parent);//显示指定父加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        /*
         发现这两行打印根本没有执行，说明该方法没有被调用。
         原因，在构造这个类加载器的时候，使用的是系统类加载器作为父类加载器的，根据双亲委托机制，父加载器能加载，就有父类来加载。
         */
        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + this.classLoaderName);
        byte[] data = loadClassData(className);
        return defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            this.classLoaderName = this.classLoaderName.replace(".", "\\");

            is = new FileInputStream(new File(name + this.fileExtension));
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

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.chenhl.jvm.classloader.MyTest1");
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());
    }

    public static void main(String[] args)throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        test(loader1);
    }

}
