package com.spring.mvc.chap04.entity;

import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;
    private String personName;
    private int personAge;
}
