package com.spring.mvc.rest;

public class Animal {
    String name;
    int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Student extends Animal {
    Student(String name, int age) {
        super(name, age);

    }
}
