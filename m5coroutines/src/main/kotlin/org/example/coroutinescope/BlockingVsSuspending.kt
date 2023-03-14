package org.example.coroutinescope

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.suspendingCall(ctx: CoroutineContext) =
    launch(ctx) {
        println("Start_delay")
        delay(1) // 1
        println("foo bar")
    }

fun CoroutineScope.blockingCall(ctx: CoroutineContext) =
    launch(ctx) {
        runBlocking {
            println("Taking delay")
            delay(500)
            println("foo bar")
        }
    }

fun main() {
    runBlocking {
        val ctx = newSingleThreadContext("MyOwnThread")
        repeat(50000) { //10000
            suspendingCall(ctx)
//            blockingCall(ctx)
        }
    }
}
