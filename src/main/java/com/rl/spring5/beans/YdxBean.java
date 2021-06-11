package com.rl.spring5.beans;

public class YdxBean {

    public String name;
    public String address;

    public YdxBean(){
        this.name = "未命名Ydx";
    }

    public YdxBean(String name,String address) {
        this.name = name;
        this.address = address;
    }

}
