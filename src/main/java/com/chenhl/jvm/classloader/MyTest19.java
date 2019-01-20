package com.chenhl.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

/*
 扩展类加载器

 在命令行里，先执行cd out/production/classes 然后运行java -Djava.ext.dirs=./ com.chenhl.jvm.classloader.MyTest19
 会抛出ClassNotFoundException，因为我们在命令行里修改了扩展类加载器所检索的目录，而当前目录里没有这个类的class文件
 */
public class MyTest19 {

    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();

        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest19.class.getClassLoader());
    }
}
