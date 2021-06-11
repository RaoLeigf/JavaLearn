package com.rl.spring5.beans;

public class PersonBean {
    private String name;

    public PersonBean() {
        this.name = "未命名";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
