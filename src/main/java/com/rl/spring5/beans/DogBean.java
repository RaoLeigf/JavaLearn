package com.rl.spring5.beans;

public class DogBean {

    private String name;

    private int age;

    public DogBean() {
        this.name = "未起名Dog";
        this.age = 1;
    }

    public DogBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
