package com.runoob.base;

public class Dog extends Animal{
    public Dog(){
        this.setName("NoNameDog");
    }

    public Dog(String name){
        super(name);
    }
}
