package com.example.lambda_demo.pojo;

import lombok.Data;

@Data
public class Person {
    private String firstName;
    private String lastName;
    private String job;
    private String gender;
    private int age;
    private int salary;
    public Person(String firstName,String lastName,String job,String gender,int salary,int age){
        this.firstName=firstName;
        this.lastName=lastName;
        this.job=job;
        this.gender=gender;
        this.age=age;
        this.salary=salary;
    }
}
