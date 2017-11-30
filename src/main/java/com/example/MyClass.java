package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public class MyClass {
    private void print (String msg){
//        System.out.println(">> " + msg);
        Throwable throwable = new Throwable();
        throwable.printStackTrace();
    }

    public MyClass(String src) {

    }
    public MyClass() {

    }

    private int count = 100;
    public void d(String string) {
        print(string);
    }

    public static void main(String[] args) {
        /*
        try {
            Method print = MyClass.class.getDeclaredMethod("print", String.class);
            MyClass m = new MyClass();
            m.print("getDeclaredMethod () ");

            Method[] methods = m.getClass().getMethods();

//            for (Method method : methods) {
//                System.out.println(method.getName());
//            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Invoke.i();
        */

        MyClass c = new MyClass();
        try {
            Field count = c.getClass().getDeclaredField("count");
            Object o = count.get(c);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
/*
获取Class
1.
    Employee e;
    Class cl = e.getClass();
2.
    String className = "java.util.Date";
    Class cl = Class.forName(className);
3.
    Class cl = Date.class;

可以用==运算符比较两个类对象
    if (e.getClass() == Employee.class) {}
快速创建一个Class的实例
    e.getClass().newInstance();
java.lang.reflect包中又三个类Field 类的域、Method 方法和Constructor 构造器;
三个类都又getName方法，返回项目的名称；
Class类中的getFields、getMethods、getConstructors方法返回类提供的public域、public方法、和构造器，包括超类的共有成员；
getDeclareFields、getDeclareMethods、getDeclareConstructors返回类中声明的全部域、方法、构造器，包括private、protected，但不包括超类的成员；

public int count;
Field field = cl.getDeclaredField("count");
Modifier.toString(field.getModifiers())
field.getType().getName()
field.getName()
 */
