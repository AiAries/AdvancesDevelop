package com.example.lib_kotlin_learn

import java.util.*


object Out_Keyword_Use {
    fun getAnimal(): List<Animal> {

        val arrayList = ArrayList<Dog>()
        arrayList.add(Dog())
        return arrayList
    }
    @JvmStatic
    fun main(args: Array<String>){
        val dog = getAnimal()[0] as Dog
        dog.eat()
    }
    fun feed(): Feed<out Animal> {
        return object : Feed<Dog> {}
    }
//    fun feed2(): Feed<Animal> {
        //Type mismatch.
        //Required:
        //Feed<Animal>
        //Found:
//        return object : Feed<Dog> {}
//    }
}

interface Feed<T>{

}


open class Animal{

}
class Dog:Animal(){
    fun eat(): Unit {
        println("eat bone")
    }
}
