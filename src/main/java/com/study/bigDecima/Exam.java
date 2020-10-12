package com.study.bigDecima;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-12 18:44
 */
public class Exam {

    @Test
    public void ExamOne() {
        BigDecimal bigDecimalOne = new BigDecimal("2000.0");
        BigDecimal bigDecimalTwo = new BigDecimal("4000.0");
        BigDecimal bigDecimalThree = new BigDecimal("5000.0");
        BigDecimal bigDecimalFour = new BigDecimal("7000.0");
        BigDecimal bigDecimalFive = new BigDecimal("9999.0");
        Employee employeeOne = new Employee(106, "ff", "保安大哥", bigDecimalOne);
        Employee employeeTwo = new Employee(101, "aa", "部门助理", bigDecimalTwo);
        Employee employeeThree = new Employee(103, "cc", "技术支持", bigDecimalThree);
        Employee employeeFour = new Employee(104, "dd", "人事主管", bigDecimalFour);
        Employee employeeFive = new Employee(102, "bb", "部门主管", bigDecimalFive);
        List<Employee> list = new ArrayList<>();
        list.add(employeeOne);
        list.add(employeeTwo);
        list.add(employeeThree);
        list.add(employeeFour);
        list.add(employeeFive);
        Collections.sort(list);
        for (Employee employee : list) {
            System.out.println(employee);

            BigDecimal payment = new BigDecimal("0");
            BigDecimal salary = employee.getSalary();

            BigDecimal ladderPaymentOne = new BigDecimal("1500").multiply(new BigDecimal("0.03"));
            BigDecimal ladderPaymentTwo = new BigDecimal("3000").multiply(new BigDecimal("0.1"));
            BigDecimal beyond;

            if (salary.compareTo(new BigDecimal(8000)) >= 0) {
                beyond = salary.subtract(new BigDecimal("8000")).multiply(new BigDecimal("0.2"));
                payment = payment.add(ladderPaymentOne).add(ladderPaymentTwo).add(beyond);
            } else if (salary.compareTo(new BigDecimal(5000)) >= 0) {
                beyond = salary.subtract(new BigDecimal("5000")).multiply(new BigDecimal("0.1"));
                payment = payment.add(ladderPaymentOne).add(beyond);
            } else if (salary.compareTo(new BigDecimal(3500)) >= 0) {
                beyond = salary.subtract(new BigDecimal("3500")).multiply(new BigDecimal("0.03"));
                payment = payment.add(beyond);
            }
            System.out.println("纳税额度: " + payment);
        }
    }
}

