package org.example.continuation

import kotlinx.coroutines.delay

suspend fun main() {
    coroutine()
}

suspend fun coroutine() {
    val text: String = getText()
    printText(text)
}

suspend fun getText(): String {
    delay(2000)
    return "hello!!!"
}

fun printText(text: String) {
    println(text)
}

