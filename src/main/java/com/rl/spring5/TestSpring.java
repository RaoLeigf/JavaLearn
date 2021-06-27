package com.rl.spring5;

import com.alibaba.fastjson.JSON;
import com.rl.spring5.beans.*;
import javafx.util.converter.DateStringConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TestSpring {

    //ApplicationContext : BeanFactory
    @Test
    public void TestApplication(){

        //xml 要放在resources文件夹下
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        PersonBean person = context.getBean("personBean", PersonBean.class);
        System.out.println(person.getName());
    }

    // BeanFactory Spring内部使用接口,一般不由开发人员使用
    @Test
    public void TestBeanFactory(){

        //xml 要放在resources文件夹下
        BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
        PersonBean person = factory.getBean("personBean", PersonBean.class);
        System.out.println(person.getName());
    }

    @Test
    public void TestIoc() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        /* 和下面效果一样
         * Class<PersonBean> clazz = PersonBean.class;
         * PersonBean person = clazz.newInstance();
         */

        Class<?> clazz = Class.forName("com.rl.spring5.beans.PersonBean");
        PersonBean person = (PersonBean)clazz.newInstance();
        System.out.println(person.getName());
    }

    @Test
    public void TestDogBean(){
        //xml 要放在resources文件夹下
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        DogBean dog = context.getBean("dogBean", DogBean.class);
        System.out.println(dog.getName());
    }

    @Test
    public void TestYdxBean(){
        //xml 要放在resources文件夹下
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        YdxBean ydx = context.getBean("ydxBean", YdxBean.class);
        System.out.println(ydx.address);
    }

    @Test
    public void TestListStream(){
        List<DogBean> dogs = new ArrayList<DogBean>();
        dogs.add(new DogBean("一号狗",1));
        dogs.add(new DogBean("二号狗",2));
        dogs.add(new DogBean("三号狗",3));
        dogs.add(new DogBean("四号狗",4));
        dogs.add(new DogBean("五号狗",5));

        List<Integer> ages = dogs.stream().map(DogBean::getAge).collect(Collectors.toList());

        System.out.println("年龄合计:" + dogs.stream().map(DogBean::getAge).reduce(0,Integer::sum));
        System.out.println("到了这里");
    }

    @Test
    public void TestMapStream(){
        List<DogBean> dogs = new ArrayList<DogBean>();
        dogs.add(new DogBean("3号狗",1));
        dogs.add(new DogBean("二号狗",2));
        dogs.add(new DogBean("3号狗",3));
        dogs.add(new DogBean("四号狗",4));
        dogs.add(new DogBean("3号狗",5));

        Map<String, DogBean> map = dogs.stream().collect(Collectors.toMap(p -> p.getName() + "&" + p.getAge(),p -> p));
        System.out.println("到了这里");
    }

    @Test
    public void TestCopy(){
        List<DogBean> dogs = new ArrayList<>();
        dogs.add(new DogBean("3号狗",1));
        dogs.add(new DogBean("二号狗",2));
        dogs.add(new DogBean("3号狗",3));
        dogs.add(new DogBean("四号狗",4));
        dogs.add(new DogBean("3号狗",5));

        System.out.println(dogs.get(0).getName());
    }

    /*
     * 20210610 测试Consumer的使用
     */
    @Test
    public void TestConsumer(){
        TestFunction("又",s ->{
            System.out.println("TestConsumer:" + s);
        });
    }

    private void TestFunction(String s , Consumer<String> con){
        con.accept(s);
        System.out.println("TestFunction:" + s);
        if(s.length() < 100){
            TestFunction(s+s,con);
        }
    }

    /*
     * 测试排序
     */
    @Test
    public void TestStringSort(){
        List<DogBean> dogs = new ArrayList<DogBean>();
        dogs.add(new DogBean("401",1));
        dogs.add(new DogBean("403",2));
        dogs.add(new DogBean("40102",3));
        dogs.add(new DogBean("40101",4));
        dogs.add(new DogBean("40201",5));
        dogs.add(new DogBean("40301",5));
        dogs.add(new DogBean("40210",5));
        dogs.add(new DogBean("40302",5));
        dogs.add(new DogBean("40201",5));

        List<DogBean> dogs2 = dogs.stream().sorted(Comparator.comparing(DogBean::getName)).collect(Collectors.toList());
        List<String> dogNames = dogs2.stream().map(DogBean::getName).collect(Collectors.toList());

    }


    /*
     * 测试根据编码生成树
     */
    @Test
    public void TestMadeTree(){
        List<TreeBean> dogs = new ArrayList<TreeBean>();
        dogs.add(new TreeBean("401",1));
        dogs.add(new TreeBean("403",2));
        dogs.add(new TreeBean("40102",3));
        dogs.add(new TreeBean("40101",4));
        dogs.add(new TreeBean("40201",5));
        dogs.add(new TreeBean("40301",5));
        dogs.add(new TreeBean("40210",5));
        dogs.add(new TreeBean("40302",5));
        dogs.add(new TreeBean("40201",5));
        dogs.add(new TreeBean("4020101",5));
        dogs.add(new TreeBean("4020102",5));
        dogs.add(new TreeBean("501",5));
        dogs.add(new TreeBean("502",5));
        dogs.add(new TreeBean("50101",5));
        dogs.sort((TreeBean t1,TreeBean t2)-> t1.getName().compareTo(t2.getName()));
        List<TreeBean> incomeList = GetChildren(dogs,"4");
        List<TreeBean> outcomeList = GetChildren(dogs,"4");
        System.out.println(JSON.toJSONString(incomeList));
        System.out.println(JSON.toJSONString(outcomeList));
    }

    private List<TreeBean> GetChildren(List<TreeBean> allData, String parentCode){
        if(allData == null || allData.isEmpty()){
            return allData;
        }
        List<TreeBean> children = new ArrayList<>(allData);


        Iterator<TreeBean> iter = allData.iterator();

        //当前节点的下级
        List<TreeBean> childrenList = new ArrayList<>();
        List<TreeBean> restList = new ArrayList<>();

        while (iter.hasNext()){
            TreeBean sub = iter.next();
            if(children.stream().anyMatch(p -> sub.getName().startsWith(p.getName())
                    && p.getName().length() < sub.getName().length())){

            }
            else if(sub.getName().startsWith(parentCode)
                    && parentCode.length() < sub.getName().length()){
                iter.remove();
                childrenList.add(sub);
            }


        }

        for(TreeBean child : childrenList){
            child.setChildren(GetChildren(allData,child.getName()));
        }

        return childrenList;
    }

    @Test
    public void TestStreamMap(){
        List<String> names = new ArrayList<>();
        names.add("aa");
        names.add("bb");
        names.add("cc");
        names.add("dd");
        names.add("ee");

        List<DogBean> dogs = names.stream()
                .map(p -> new DogBean(p,1))
                .filter(p -> p.getName().equals("aa") || p.getName().equals("dd") || p.getName().equals("ee"))
                .limit(2)
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(dogs));

    }

    @Test
    public void TestSummaryStatistics(){
        List<DogBean> dogs = Arrays.asList(new DogBean("1",1),new DogBean("2",2));
        List<DogBean> dogs2 = Collections.singletonList(new DogBean("2", 2));
        List<Integer> nums = Arrays.asList(1,2,3,4,5,7,8,9,10,11,12,13,14);
        List<Integer> nums2 = Arrays.asList(111,2,3,4,15,7,8,911,10,111,12,13,14);
        IntSummaryStatistics stats = nums.stream().mapToInt(x -> x).summaryStatistics();
        IntSummaryStatistics stats2 = nums2.stream().mapToInt(x -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        stats.accept(2);

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

        stats.combine(stats2);

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    @Test
    public void TestDemo(){

        List<DogBean> dogs = Arrays.asList(
                new DogBean("aaqweaqw",1),
                new DogBean("derewq",2),
                new DogBean("bwq",3),
                new DogBean("crwwe",4),
                new DogBean("derewq",2)
        );

       dogs.sort(Comparator.comparing(x->x.getName().length()));

        System.out.println(JSON.toJSONString(dogs.size()));
    }

    @Test
    public void TestFindFirst(){
        List<DogBean> dogs = Arrays.asList(
                new DogBean("a",1),
                new DogBean("d",2),
                new DogBean("b",3),
                new DogBean("c",4)
        );

        List<DogBean> dogs2 = dogs.stream().filter(p -> p.getName().equals("f")).collect(Collectors.toList());

        if(!dogs2.isEmpty()){
            DogBean dog = dogs2.get(0);
            System.out.println(JSON.toJSONString(dog));
        }

    }

    @Test
    public void TestBigDecimal(){
        BigDecimal b1 = new BigDecimal("987654321.12345678");
        BigDecimal b2 = BigDecimal.valueOf(123456789.98765432);
        System.out.println(b1.multiply(b2));
    }

    @Test
    public void TestInteger(){
        DemoBean demo = new DemoBean();
        System.out.println(demo.i);//int 的默认值会是0
        System.out.println(demo.i2);//Integer 的默认值会是null
    }

    @Test
    public void TestSetSet(){
        DogBean dog = new DogBean();

        dog.setName("aa");
    }

    @Test
    public void TestString(){
        StringBuilder builder = new StringBuilder();
        List<Character> characters = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j');
        int i = 0;
        for(; i < 10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 10 ; j++){
                        System.out.println(Thread.currentThread().getName()
                                + "****"
                                + j
                                + characters.get(j).toString());
                    }
                }
            }).start();
        }
    }

    @Test
    public void TestHashMap(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        System.out.println(map.get("a"));
        System.out.println(map.get(1));
    }

    @Test
    public void TestDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        calendar.set(2021, Calendar.JUNE,24);
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        calendar.set(Calendar.DATE,19);
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        calendar.set(Calendar.YEAR,2014);
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        calendar.set(Calendar.MONTH,Calendar.DECEMBER);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }

    @Test
    public void TestMain(){
        int a = 3,b = 4;
        System.out.println((a+b)/2);
        BigDecimal b1 = new BigDecimal("1.2");
        BigDecimal b2 = new BigDecimal("9.7");
        System.out.println(b1.add(b2));
    }
}
