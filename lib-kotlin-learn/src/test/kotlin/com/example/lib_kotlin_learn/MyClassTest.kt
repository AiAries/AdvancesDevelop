package com.example.lib_kotlin_learn

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MyClassTest {
    private lateinit var  myClass:MyClass
    @org.junit.Test
    fun main() {
        println(6+6)
    }

    @After
    fun tearDown() {
        print("tear down")
    }

    @Before
    fun setUp() {
        println("setUp")
        myClass = MyClass()
    }

    @Test
    fun add() {
        assert(myClass.add(6,6) ==12)
    }
    @Test
    fun judgeIsEven(){
        assertTrue(myClass.judgeIsEven(2))
        assertFalse(myClass.judgeIsEven(3))
    }
    @Test
    fun forDemo() {
        myClass.forDemo(arrayOf("lily","lucy","jack"))
    }
}