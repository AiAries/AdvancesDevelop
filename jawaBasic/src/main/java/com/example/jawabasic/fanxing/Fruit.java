package com.example.jawabasic.fanxing;

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
    private List<? extends Fruit> fruits;//协变
    private List<? super Fruit> fruits2;//逆变 定义了泛型的下限 必须是Fruit的父类
    private List<?> fruit3;
    public void addFruit(){
        //编译不过，<? extends Fruit> 泛型定义了上限，可以是orange Apple 等Fruit的子类
//        fruits.add(new Orange());
        Fruit fruit = fruits.get(0);//生产者

        fruits2.add(new Orange());//消费者
        fruits2.add(new Apple());
//        fruits2.add(new ShengXian());//编译不过

//        fruit3.add(new Object());//编译不过
//        fruit3.add(new Orange());//编译不过
    }
}
