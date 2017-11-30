package com.example;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Run {
    public static void main(String[] args) {
        Node node = new Node();
        try {


//            Method getCount = node.getClass().getDeclaredMethod("getCount");
//            getCount.setAccessible(true);
//            getCount.invoke(node);

            Field count = node.getClass().getDeclaredField("count");
            // 为了达到访问的目的，可以使用setAccessible方法将域设置成可访问的；
            // setAccessible是AccessibleObject类中的一个方法，它是Field、Method、Constructor类的公共超类
            count.setAccessible(true);
            // 如果不设置可以使用setAccessible方法, get()则会IllegalAccessException: Class com.example.Run can not access a member of class com.example.Node with modifiers "private"
            // 除非有访问权限，只允许查看人与对象有那些域，而不允许读取他们的值，反射机制默认行为受限于java的访问控制；
            Object o = count.get(node);
            int anInt = count.getInt(node);
            System.out.println(count.getType().getName() + " : " + o.toString() + " | " + anInt);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
