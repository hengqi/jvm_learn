# 深入理解JVM

## 类的加载、连接与初始化
   ![](images/jvm01.png 'jvm加载过程')
   
 * Java程序对类的使用方式有两种
   * 主动使用
   * 被动使用
 * 所有的Java虚拟机实现必须在每个类或接口被Java程序 **_首次主动使用_** 时才初始化它们
 * 主动使用（**七种**）
   1. 创建类的实例
   2. 访问某个类或接口的静态变量，或者对该静态变量赋值
   3. 调用类的静态方法
   4. 反射（如Class.forName("com.test.Test")）
   5. 初始化一个类的子类
   6. Java虚拟机启动时被标明为启动类的类（Java Test）
   7. JDK1.7开始提供的动态语言的支持：
    java.lang.invoke.MethodHandle实例的解析结果REF_getStatic, REF_putStatic, REF_invokeStatic句柄对应的类没有初始化，则初始化（了解）
  
 * 除了以上七种情况外，其他使用Java类的方式都被看作时对类的**被动使用**，都不会导致类的**初始化**