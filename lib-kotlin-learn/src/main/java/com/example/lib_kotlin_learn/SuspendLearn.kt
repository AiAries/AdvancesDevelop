package com.example.lib_kotlin_learn

@Suppress("ImplicitThis")
class SuspendLearn {
    private suspend fun test1() {
        Thread.sleep(1000)
        println("test1")
    }

    private suspend fun test2() {
        Thread.sleep(2000)
        println("test2")
    }

    private fun test() {
        Thread {
            kotlin.run {
//                test1()
//                test2()
            }
        }.start()

        println("done")
    }
//    fun main(strs:Array){
//
//    }

}