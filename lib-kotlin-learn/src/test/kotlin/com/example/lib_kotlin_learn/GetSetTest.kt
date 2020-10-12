package com.example.lib_kotlin_learn

import org.junit.Before
import org.junit.Test

class GetSetTest {

    @Before
    fun setUp() {

    }
    @Test
    fun set(){
        var obj = GetSet()
        println(obj.age)
        obj.age = -1
        println(obj.age)
        obj.age = 16
        println(obj.age)
    }

}