package com.example.jawabasic.generic;

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
 * ���� PECS  producer extends  consumer super
 */
class Test2{
    private List<? extends Fruit> fruits;//Э��
    private List<? super Fruit> fruits2;//��� �����˷��͵����� ������Fruit�ĸ���
    private List<?> fruit3;
    public void addFruit(){
        //���벻����<? extends Fruit> ���Ͷ��������ޣ�������orange Apple ��Fruit������
//        fruits.add(new Orange());
        Fruit fruit = fruits.get(0);//������

        fruits2.add(new Orange());//������
        fruits2.add(new Apple());
        fruits2.add(new Fruit());
//        fruits2.add(new Object());//���벻��
//        fruits2.add(new ShengXian());//���벻��

//        fruit3.add(new Object());//���벻��
//        fruit3.add(new Orange());//���벻��
    }
}
