package com.spring.mvc.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class Person {

    private String name;
    private int age;
    private List<String> hobby;

}