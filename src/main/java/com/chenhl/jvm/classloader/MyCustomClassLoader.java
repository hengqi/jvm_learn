package com.chenhl.jvm.classloader;

import java.io.*;

/**
 * 自定义类加载器
 *
 */
public class MyCustomClassLoader extends ClassLoader {

    private String classLoaderName;

    private String path;// 指定从哪里加载类

    private final String fileExtension = ".class";

    public MyCustomClassLoader(String classLoaderName) {
        super();//将系统类加载器当做该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyCustomClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);//显示指定父加载器
        this.classLoaderName = classLoaderName;
    }

    /*
        MyTest23时新增，用于指定新的系统类加载器时，所用到
     */
    public MyCustomClassLoader(ClassLoader classLoader) {
        super(classLoader);
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

}
