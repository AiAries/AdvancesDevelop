package com.example.jawabasic.generic_泛型;

import java.util.ArrayList;
import java.util.List;

class Fruit extends ShengXian{

}
class ShengXian{

}

class Orange extends Fruit {

}
class Apple extends Fruit {

}

/**
 * 法则 PECS  producer extends  consumer super
 */
class Test2{
    private List<? extends Fruit> xieBianfruits;//协变,定义了泛型的上限 --协变是生产者，定义了上限
    private List<? super Fruit> nibianfruits2;//逆变 定义了泛型的下限 必须是Fruit的父类
    private List<?> fruit3;
    public void addFruit(){
        //编译不过，<? extends Fruit> 泛型定义了上限，可以是orange Apple 等Fruit的子类
//        fruits.add(new Orange());
        xieBianfruits = new ArrayList<Apple>();
        xieBianfruits = new ArrayList<Orange>();
        Fruit fruit = xieBianfruits.get(0);//生产者

        nibianfruits2 = new ArrayList<Object>();
        nibianfruits2 = new ArrayList<ShengXian>();

        nibianfruits2.add(new Orange());//消费者
        nibianfruits2.add(new Apple());
        nibianfruits2.add(new Fruit());
//        fruits2.add(new Object());//编译不过
//        fruits2.add(new ShengXian());//编译不过
        Object object = nibianfruits2.get(0);
//        fruit3.add(new Object());//编译不过
//        fruit3.add(new Orange());//编译不过
    }

    public static void main(String[] args) {

    }
}
