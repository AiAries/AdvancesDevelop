package com.example.dagger_demo.car

internal class Seat(name: String?) {
    init {
        println("new $name seat")
    }
}