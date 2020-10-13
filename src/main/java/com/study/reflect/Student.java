package com.study.reflect;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-13 13:41
 */
public class Student {
    private int id;

    private String name;

    public Student() {
    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    @Override

    public String toString() {

        return "Student{" +

                "id=" + id +

                ", name='" + name + '\'' +

                '}';

    }
}
