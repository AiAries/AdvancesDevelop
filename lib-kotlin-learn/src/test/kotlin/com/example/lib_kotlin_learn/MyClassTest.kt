package com.example.lib_kotlin_learn

import org.junit.Assert.*
import org.junit.Test

class MyClassTest {

    @org.junit.Test
    fun main() {
        println(6+6)
    }

    @Test
    fun add() {
        val my = MyClass()
        assert(my.add(6,6) ==12)
    }
}