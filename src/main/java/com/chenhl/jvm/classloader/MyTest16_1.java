package com.chenhl.jvm.classloader;

import java.io.*;

/**
 * 自定义类加载器，命名空间
 *
 * ClassLoader虽然是抽象类，但没有抽象方法
 */
public class MyTest16_1 extends ClassLoader {

    private String classLoaderName;

    private String path;// 指定从哪里加载类

    private final String fileExtension = ".class";

    public MyTest16_1(String classLoaderName) {
        super();//将系统类加载器当做该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16_1(ClassLoader parent, String classLoaderName) {
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

    public static void main(String[] args)throws Exception {
//       test1();
//       test2();
//       test3();
//       test4();
       test5();
    }

    /**
     * 一个类加载器从不同的位置加载类
     * 如果类路径下没有MyTest1这个类，系统类加载器加载不成功，那么就会使用我们自定义的类加载器来加载，
     *         如果存在，则使用系统类加载器来加载
     * @throws Exception
     */
    public static void test1() throws Exception{
        MyTest16_1 loader1 = new MyTest16_1("loader1");

        //loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes\\");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

    }

    /**
     * 两个类加载器从不同的位置加载类 这两个类加载器的父加载器都是系统类加载器
     * 如果类路径下没有MyTest1这个类，系统类加载器加载不成功，那么就会使用我们自定义的类加载器来加载，
     *         如果存在，则使用系统类加载器来加载
     * @throws Exception
     */
    public static void test2() throws Exception{
        MyTest16_1 loader1 = new MyTest16_1("loader1");


        //loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes\\");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("=======");
        MyTest16_1 loader2 = new MyTest16_1("loader2");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());
    }

    /**
     * 两个类加载器从不同的位置加载类 loader1的父加载器都是系统类加载器;loader2的父加载器都是系loader1
     * 如果类路径下没有MyTest1这个类，系统类加载器加载不成功，那么就会使用我们自定义的类加载器来加载，
     *         如果存在，则使用系统类加载器来加载
     * @throws Exception
     */
    public static void test3() throws Exception{
        MyTest16_1 loader1 = new MyTest16_1("loader1");


        //loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes\\");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("=======");
        MyTest16_1 loader2 = new MyTest16_1(loader1,"loader2");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());
    }

    /**
     * 三个类加载器从不同的位置加载类
     * loader1的父加载器都是系统类加载器;
     * loader2的父加载器都是系loader1;
     * loader3的父加载器都是系统类加载器;
     * 如果类路径下没有MyTest1这个类，系统类加载器加载不成功，那么就会使用我们自定义的类加载器来加载，
     *         如果存在，则使用系统类加载器来加载
     * @throws Exception
     */
    public static void test4() throws Exception{
        MyTest16_1 loader1 = new MyTest16_1("loader1");


        //loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes\\");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("=======");
        MyTest16_1 loader2 = new MyTest16_1(loader1,"loader2");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz2: "+ clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());

        System.out.println("=======");

        MyTest16_1 loader3 = new MyTest16_1("loader2");
        loader3.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz3 = loader3.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz3: "+ clazz3.hashCode());
        Object object3 = clazz3.newInstance();
        System.out.println(object3);
        System.out.println(object3.getClass().getClassLoader());

    }

    /**
     * 三个类加载器从不同的位置加载类
     * loader1的父加载器都是系统类加载器;
     * loader2的父加载器都是系loader1;
     * loader3的父加载器都是loader2;
     * 如果类路径下没有MyTest1这个类，系统类加载器加载不成功，那么就会使用我们自定义的类加载器来加载，
     *         如果存在，则使用系统类加载器来加载
     * @throws Exception
     */
    public static void test5() throws Exception{
        MyTest16_1 loader1 = new MyTest16_1("loader1");


        //loader1.setPath("C:\\Users\\TF019387\\myProjects\\jvm_learn\\out\\production\\classes\\");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("=======");
        MyTest16_1 loader2 = new MyTest16_1(loader1,"loader2");
        loader1.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz2: "+ clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());

        System.out.println("=======");

        MyTest16_1 loader3 = new MyTest16_1(loader2,"loader3");
        loader3.setPath("C:\\Users\\chenh\\Desktop\\");

        Class<?> clazz3 = loader3.loadClass("com.chenhl.jvm.classloader.MyTest1");
        System.out.println("clazz3: "+ clazz3.hashCode());
        Object object3 = clazz3.newInstance();
        System.out.println(object3);
        System.out.println(object3.getClass().getClassLoader());

    }

}
