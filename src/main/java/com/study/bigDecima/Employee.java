package com.study.bigDecima;

import java.math.BigDecimal;

/**
 * @version V1.0
 * @Description: 员工类
 * @author: h'mm
 * @date: 2020-10-12 18:41
 */
public class Employee implements Comparable<Employee>{
    private Integer userId;
    private String userName;
    private  String position;
    private BigDecimal salary;

    public Employee() {
    }

    public Employee(Integer userId, String userName, String position, BigDecimal salary) {
        this.userId = userId;
        this.userName = userName;
        this.position = position;
        this.salary = salary;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }



    @Override
    public int compareTo(Employee o) {
        return this.salary.compareTo(o.salary);
    }
}
