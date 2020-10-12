package com.example.lib_kotlin_learn

class GetSet {
    var age: Int = 1
        set(value) {
            field = if (value > 0) value else field
        }
        get() {
            return field + 1
        }
    var name: String = "lisi"
}