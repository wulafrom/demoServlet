package com.study.reflect;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-13 13:41
 */
public class ReflectTest {

    @Test
    public void testOne() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //Class是一个类，它描述的是Java中类的信息,初始化它的时候，封装了类的信息
        Class<Class> classClass = Class.class;

        //studentClass 指向的的是 Student类 对象
        Student student = new Student();
        student.setName("盘安安");
        System.out.println(student.getName());

        //方式一
        Class<Student> studentClass = Student.class;
        //方式二
        Class<? extends Student> studentClass1 = student.getClass();
        //方式三
        Class<?> studentClass2 = Class.forName("com.study.reflect.Student");
        Constructor<?> constructor = studentClass2.getConstructor();
        Object o = constructor.newInstance();
        Method setName = studentClass2.getMethod("setName", String.class);
        //setName属性的对象，对Student对象的方法进行调用
        setName.invoke(o, "盘安安");
        //获取传入的值
        Method getName = studentClass2.getMethod("getName");
        System.out.println(getName.invoke(o));
    }
}
