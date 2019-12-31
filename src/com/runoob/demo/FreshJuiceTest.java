package com.runoob.demo;


class FreshJuice {
    public enum FreshJuiceSize{ SMALL, MEDIUM , LARGE }
    FreshJuiceSize size;
    FreshJuiceSize size2;
    FreshJuiceSize size3;
}

public class FreshJuiceTest {
    public static void main(String []args){
        FreshJuice juice = new FreshJuice();
        juice.size = FreshJuice.FreshJuiceSize.SMALL;
        juice.size2 = FreshJuice.FreshJuiceSize.valueOf("MEDIUM");
        juice.size3 = FreshJuice.FreshJuiceSize.values()[2];


        System.out.println(juice.size);
        System.out.println(juice.size2);
        System.out.println(juice.size3);

        System.out.println(juice.size.ordinal());
        System.out.println(juice.size2.ordinal());
        System.out.println(juice.size3.ordinal());

        System.out.println(juice.size.toString());
        System.out.println(juice.size2.toString());
        System.out.println(juice.size3.toString());
    }
}
