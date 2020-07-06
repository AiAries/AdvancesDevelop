package com.example.lib_kotlin_learn



open class AAA {
    constructor(name: String) {
        println("$name A")
    }

    constructor(name: String, age: Int) {
        println("我是AAA的两个参数的构造方法")
    }

    init {
        println("222")
        }
}

class BBB : AAA {
    constructor(name: String) : this(name, 0) {
        println("我是BBB的一个参数的构造方法")
    }

    constructor(name: String, age: Int) : super(name, age) {
        println("我是BBB的两个参数的构造方法")
    }
}