package com.study.http;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-12 16:13
 */
class Show<T> {
    public void print1(T t) {
        System.out.println(t);
    }

    public <E> void print2(E e) {
        System.out.println(e);
    }
}

public class GenericDemo {

    public static void main(String[] args) {
        Show<String> show = new Show<String>();
        //show.print1(new Integer(1));// 不能编译
        show.print2(new Integer(1));// 可以编译
        Class<? extends Show> aClass = show.getClass();
        String str = "123456789";
        str = str.substring(1, 3);
        System.out.print(str);

        int a = 100;
        while (a != 0) {
            System.out.print(a);
            a = a / 4;
        }
    }



}
