package com.example.lib_kotlin_learn

import java.util.*

class ApplyAlsoLetRunWith {
    var str : String = "abc"

    fun upperCase() {
        println(str)
        val toUpperCase = str.toUpperCase()
        println(toUpperCase)
    }
    fun testApply(){
        println("Apply")
        var str0 = str.apply {
            this.toUpperCase(Locale.getDefault())
            println(this)
        }
        println(str0)
    }

    fun testAlso(){
        println("also")
        var str0 = str.also {
            it.toUpperCase()
            println(it)
        }
        println(str0)
    }

    fun testLet(){
        println("let")
        var str0 = str.let {
            it.toUpperCase()
            println(it)
            Unit
        }
        println(str0)
    }

    fun testRun(){
        println("run")
        var str0 = str.run {
            this.length
            UInt
        }
        println(str0)
    }

    fun testWith(){
        println("with")
        val str = "I love kotlin"
        val array = str.toCharArray()
        val builder = StringBuilder()
        //with函数有返回值，返回值是函数体中最后一行表达式
        val result= with(builder){
            for (ch in array){
                val toInt = ch.toInt()
                print("$toInt ")
                this.append(toInt)
                //this 可以省略
                //   append(ch.toInt())
            }
            //最后一行表达式为返回值
//            toString()
        }
        println()
        println(result)

    }
}