package com.example.dagger_demo;

import javax.inject.Inject;

public class Person {
    public int age;
    @Inject
    public Person(int age) {
        this.age = age;
    }
}
