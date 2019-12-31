package com.runoob.demo;

import com.runoob.base.Animal;
import com.runoob.base.Dog;

public class AnimalTest {
    public static void main(String[] args){
        Animal animal = new Animal();
        Animal animal2 = new Animal("东西");

        animal.animalCall();
        animal2.animalCall();

        Dog dog = new Dog();
        Dog dog2 = new Dog("大黄");
        dog.animalCall();
        dog2.animalCall();
    }
}
