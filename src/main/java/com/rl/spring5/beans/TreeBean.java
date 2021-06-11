package com.rl.spring5.beans;

import java.util.List;

public class TreeBean {
    private List<TreeBean> children;
    private String name;

    private int age;

    public List<TreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<TreeBean> children) {
        this.children = children;
    }

    public TreeBean() {
        this.name = "未起名Dog";
        this.age = 1;
    }

    public TreeBean(String name, int age) {
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
