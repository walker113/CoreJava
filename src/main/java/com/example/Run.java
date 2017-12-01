package com.example;


import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Observer;

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
            // Object get (Object obj) 返回obj对象中用Field对象表示的域值

            // void set(Object obj, Object newValue) 用一个新值设置obj对象中Field对象表示的域
            count.set(node, 100);
            System.out.println(count.getType().getName() + " : " + count.get(node).toString() + " | " + count.getInt(node));


            /* TODO setAccessible
             * 设置一组对象数组可访问标志的快捷方法
             * AccessibleObject.setAccessible(AccessibleObject[] array, boolean flag)
             *
             * Field[] fields = cl.getDeclaredFields();
             * AccessibleObject.setAccessible(fields, true);
             */

            // TODO getComponentType() isPrimitive()
            String[] arr = new String[] {"admin"};
            // Returns the Class representing the component type of an array.返回表示数组的组件类型的Class。
            // If this class does not represent an array class this method returns null.
            Class arrClass = arr.getClass();
            Class componentType = arrClass.getComponentType();
//            Determines if the specified Class object represents a primitive type.
            System.out.println("ComponentType = " + componentType.getName() + " | " + componentType.isPrimitive());

            // java.lang.reflect中的Array类

            // TODO 使用反射编写泛型数组代码
//            Node[] nodes = new Node[3];
//            Object o1 = badCopyOf(nodes, 5);
//            Node node1 = ((Node[]) o1)[3];

            int[] ints = new int[]{1, 2, 3, 4, 5};
            Object cInt = goodCopyOf(ints, 7);
            for (int i = 0; i <5; i++) {
                System.out.println(((int[]) cInt)[i]);
            }



        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static Object[] badCopyOf(Object[] a, int newLength) {
        Object[] newA = new Object[newLength];
        System.arraycopy(a, 0, newA, 0, Math.min(a.length, newLength));
        return newA;
    }

    public static Object goodCopyOf(Object obj, int newLength) {
        Class cl = obj.getClass();
        if (! cl.isArray()) return null;

        Class componentType = cl.getComponentType();
        int length = Array.getLength(obj);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(obj, 0, newArray, 0, Math.min(length, newLength));

        return newArray;
    }
}
