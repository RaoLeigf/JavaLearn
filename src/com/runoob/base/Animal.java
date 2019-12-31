package com.runoob.base;

import org.jetbrains.annotations.Contract;

public class Animal {

    public enum Sex{ man, woman }

    private String name;

    private int Age;

    private Sex sex;

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Animal() {
        this.name = "NoNameAnimal";
    }

    public Animal(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void animalCall(){
        System.out.println("我的名字是:" + this.name);
    }
}
