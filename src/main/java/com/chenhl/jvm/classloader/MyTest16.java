package com.chenhl.jvm.classloader;

import java.io.*;

/**
 * 自定义类加载器
 */
public class MyTest16 extends ClassLoader {

    private String classLoaderName;

    private String path;// 指定从哪里加载类

    private final String fileExtension = ".class";

    public MyTest16(String classLoaderName) {
        super();//将系统类加载器当做该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        super(parent);//显示指定父加载器
        this.classLoaderName = classLoaderName;
    }

    /*
        MyTest23时新增，用于指定新的系统类加载器时，所用到
     */
    public MyTest16(ClassLoader classLoader) {
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
//            this.classLoaderName = this.classLoaderName.replace(".", "\\");
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

//    public static void test(ClassLoader classLoader) throws Exception {
//        Class<?> clazz = classLoader.loadClass("com.chenhl.jvm.classloader.MyTest1");
//        Object object = clazz.newInstance();
//        System.out.println(object);
//        System.out.println(object.getClass().getClassLoader());
//    }

    public static void main(String[] args)throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
//        test(loader1);

//        loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes");
        /*
        如果类路径下没有MyTest1这个类，系统类加载器加载不成功，那么就会使用我们自定义的类加载器来加载，
        如果存在，则使用系统类加载器来加载
         */
        loader1.setPath("C:\\Users\\TF019387\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);

        System.out.println("------");



        loader1 = null;
        clazz = null;
        object = null;

        System.gc();

        Thread.sleep(100000);

        loader1 = new MyTest16("loader1");
//        test(loader1);

//        loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes");
        /*
        如果类路径下没有MyTest1这个类，系统类加载器加载不成功，那么就会使用我们自定义的类加载器来加载，
        如果存在，则使用系统类加载器来加载
         */
        loader1.setPath("C:\\Users\\TF019387\\Desktop\\");

        clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        object = clazz.newInstance();
        System.out.println(object);



//        //MyTest16 loader2 = new MyTest16("loader2");
//        MyTest16 loader2 = new MyTest16(loader1, "loader2");
//        loader2.setPath("C:\\Users\\TF019387\\Desktop\\");
//        Class<?> clazz2 = loader2.loadClass("com.chenhl.jvm.classloader.MyTest1");
//        System.out.println("clazz: " + clazz2.hashCode());
//
//        Object object2 = clazz2.newInstance();
//        System.out.println(object2);
//
//        System.out.println("------");
//        MyTest16 loader3 = new MyTest16(loader2,"loader3");
//        loader3.setPath("C:\\Users\\TF019387\\Desktop\\");
//
//        Class<?> clazz3 = loader3.loadClass("com.chenhl.jvm.classloader.MyTest1");
//        System.out.println("clazz: "+ clazz3.hashCode());
//        Object object3 = clazz3.newInstance();
//        System.out.println(object3);

    }

}
