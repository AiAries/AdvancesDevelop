package com.example.lib_kotlin_learn

import org.junit.Test

class ClassLearn (name:String){
    @Test
    fun test() {
    }

    val firstProperty = "First property: $name".also(::println)

        init {
            println("First initializer block that prints $name")
        }

        val secondProperty = "Second property: ${name.length}".also(::println)

        init {
            println("Second initializer block that prints ${name.length}")
        }
}