package org.example

fun sout(block: MyContext.() -> Any?) {
    val ctx = MyContext()
    val value = ctx.block()
    println("$value")
}

class MyContext() {
    fun currTime() = System.currentTimeMillis()
}

infix fun String.time(value: String): String {
    return "$this : $value"
}
fun main() {
    sout {
        val time = this.currTime()
        "some string - $time" time "13:24"
    }
}
