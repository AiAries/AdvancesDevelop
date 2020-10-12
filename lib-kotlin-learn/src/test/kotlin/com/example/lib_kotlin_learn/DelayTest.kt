package com.example.lib_kotlin_learn

import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DelayTest {

    @Before
    fun setUp() {

    }

    @Test
    fun delay_with_thread() {
        println("hello")
        Thread.sleep(3000)
        println("world")
    }

    @Test
    fun delay_with_schedule() {
        println("hello")
        var e = Executors.newScheduledThreadPool(1)
        e.schedule({
            println("world")
        }, 3, TimeUnit.SECONDS)
        e.awaitTermination(3, TimeUnit.SECONDS)
    }
}