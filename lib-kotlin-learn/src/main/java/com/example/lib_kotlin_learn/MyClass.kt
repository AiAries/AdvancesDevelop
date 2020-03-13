package com.example.lib_kotlin_learn

import java.lang.Exception

public class MyClass {
    fun main(args: Array<String>) {
        println(66)
    }
    fun add(a:Int ,b:Int):Int
    {
        val sum:Int = a + b
        return sum
    }

    /**
     * 判断一个数是否为偶数，如果是，返回true，否则false
     *
     */
    fun judgeIsEven(a:Int): Boolean {
        if (a == 0) {
            throw Exception("0 非奇数 非偶数")
        }
        return a % 2 == 0
    }
    fun forDemo(strArray: Array<String>) {
        for (s in strArray) {
            println(s)
        }
    }


}
