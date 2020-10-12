package com.example.lib_kotlin_learn

import kotlin.coroutines.coroutineContext

@Suppress("ImplicitThis")
object SuspendLearn {
    private suspend fun test1() {
        Thread.sleep(1000)
        println("test1")
    }

    private suspend fun test2() {
        Thread.sleep(2000)
        println("test2")
    }

    private suspend fun test() {
        coroutineContext.also {
            test1()
            test2()
        }
        println("done")
    }
    @JvmStatic
     fun main(strs:Array<String>){
    }

}