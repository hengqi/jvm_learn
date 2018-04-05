# 深入理解JVM

## 类加载器ClassLoader
  
### 获取ClassLoader的途径
 * 获取当前类的classLoader `clazz.getClassLoader();`
 * 获取当前线程上下文的ClassLoader `Thread.currentThread.getContextClassLoader();`
 * 获取系统的ClassLoader `ClassLoader.getSystemClassLoader();`
 * 获取调用者的ClassLoader `DriverManager.getCallerClassLoader();`
 
### ClassLoader源码解读
 > ClassLoader的Javadoc  
   一个类加载器是一个负责加载类的一个对象。ClassLoader是一个抽象类。给定一个类的二进制名字，类加载器应该尝试着去定位或者是生成一些构成了这个类定义的数据。
   一种典型的策略是将这个二进制的名字转换成一个文件的名字然后从文件系统中读取它的class文件。<br>
   每一个Class对象都包含一个定义它的类加载器的引用。<br>
   针对于数组类的class对象并不是由类加载器来创建的，而是由JVM在需要的时候自动的创建的。对于数组类型的类加载器，通过 `Class.getClassLoader`所返回的类加载器与数组中任意一个元素通过该方法所返回的元素的类加载器是一样的；
   如果数组类元素是元素类型，那么这个数组类是没有类加载器的。<br>
   实现了ClassLoader的应用是为了扩展JVM动态加载类的方式。<br>
   类加载器可以典型的被使用于安全管理器去标识安全域。<br>
   ClassLoader类使用了委托模式去搜索类和资源。ClassLoader的每一个实例都会有一个与之关联的父类加载器。当被请求去查找一个类或资源的时候，类加载器会在自己尝试加载这个类或资源之前，委托这种查找给自己的父类加载器。
   虚拟机内建的类加载器，被称为根类加载器的加载器，没有一个父类加载器，但是可以作为一个父类加载器。<br>
   支持并发的加载类的类加载器，我们称之为并发能力的类加载器，这种类加载器被要求注册它们自身，在类初始化时，通过调用 `ClassLoader.registerAsPallelCapable`方法。我们使用的类加载器默认情况下已经被注册为并行的加载类的能力的类加载器。
   然而，该类的子类依然需要注册自身，如果它们需要这种并行加载类的能力。<br>
   在委托模型并不是严格层次化的这种环境下，类加载器需要是并行的，否则，类加载器可能会导致死锁，因为加载器的锁在加载类的过程中一直被持有。<br>
   通常情况下，JVM是以一种平台相关的方式从本地的文件系统中加载类。比如，在Unix上，虚拟机会从CLASSPATH所指定的目录中加载类<br>
   然而，一些类可能并不是来源于文件，它们可能来源于网络，或者由应用产生。在这种情况下， `defineClass`这个方法会将字节数组转换成一个Class的实例。这个新定义的这个类的实例可以通过
   `Class.newInstance`来创建。<br>
   由类加载器所创建的对象的方法和构造器，可能会引用其他的方法。为了确定被引用的类都是那些，JVM还会调用最初加载这个应用类的类加载器的 `loadClass`方法来去创建其他的被引用的类。
   
   >> `protected Class<?> findClass(String name) throws ClassNotFoundException`<br>
     根据指定的二进制的名字来查找类，这个方法应该被类加载器ClassLoader的实现类（遵循着双亲委托模型加载类的实现类）所重写，这个方法会被 `loadClass` 方法在检查完针对于要加载的类的父加载器之后调用。
     该方法默认抛 `ClassNotFoundException`。返回指定的二进制名字对应的类的Class对象。
     
   >> `protected final Class<?> defineClass(byte[] b, int off, int len)
               throws ClassFormatError`<br>
     将一个字节数组转化成一个Class类的实例。在这个类能够使用之前，它必须被解析。<br>
     
   >> `public Class<?> loadClass(String name) throws ClassNotFoundException`<br>
     加载类使用指定的二进制的名字name,该方法搜索类的方式与 `loadClass(String, boolean)` 方法所使用的方式一致，它被jvm调用，去解析类的引用。调用该方法与调用 `loadClass(name, false)` 一样 <br>
     
   >> `protected Class<?> loadClass(String name, boolean resolve)
               throws ClassNotFoundException`
     加载类使用指定的二进制的名字name,这个方法搜索类的默认的实现以下面的顺序来查找类：<br>
     1. 调用 `findLoadedClass(String)` 来去检查这个类是否已经被加载。  
     2. 调用父类加载器的 `loadClass(String)`方法。 如果父类加载器为null，则会调用jvm内置的根类加载器来加载。  
     3. 调用 `findClass(String)` 来去查找类。  
    如果按照以上的步骤找到了类文件，并且resolve为true，那么就会在找到的类对象上调用 `resolveClass(Class)`。
    ClassLoader的子类被鼓励去重写 `findClass(String)`方法，而不是这个方法。
    
    
   
   
 