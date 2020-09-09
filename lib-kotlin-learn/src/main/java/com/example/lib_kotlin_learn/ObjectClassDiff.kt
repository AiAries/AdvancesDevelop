package com.example.lib_kotlin_learn

//object 不能有构造方法 所有的成员都是静态调用
object Man/*(age: Int)*/ : Person {

    override fun say(h: Float) {
        println("$sex height is $h")
    }

    const val sex:String = "男的"
    @JvmStatic
    var height:Float = 1.8f
}

//class有构造方法 都是对象调用
class Woman(age: Int) : Person {
    //Only members in named objects and companion objects can be annotated with '@JvmStatic'
    //@JvmStatic
    fun proBaby(): Unit {
        println("live baby")
    }
    override fun say(h: Float) {
        println("$sex height is $h")
    }
    var height:Float = 1.6f
    companion object {
        const val sex: String = "女的"

    }
}
object Test{
    @JvmStatic
    fun main(args: Array<String>) {
        Man.say(Man.height);
        val woman = Woman(16)
        woman.say(woman.height)
    }

}

interface Person {
    fun say(h:Float): Unit
}