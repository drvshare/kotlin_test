package org.example.structuredconcurency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

suspend fun main() {
    val vocabulary = Vocabulary()

    val startTime = System.currentTimeMillis()
//    findWordsSlowly(vocabulary, "сильный", "невозмутимый")

    coroutineScope {
        findWordsAsync(vocabulary, "привет", "любимый", this)
    }

    val duration = System.currentTimeMillis() - startTime
    println("duration = $duration")

}

// Loads sequantially
// Time measure == time of each function++
suspend fun findWordsSlowly(vocabulary: Vocabulary, word: String, word2: String) {
    val word = vocabulary.find(word)
    val word2 = vocabulary.find(word2, withTime = 5000)

    println("Make some other stuff")
    println("Found $word && $word2")
    println("End some other stuff")
}


suspend fun findWordsAsync(vocabulary: Vocabulary, word: String, word2: String, scope: CoroutineScope) {
    val deferred = scope.async { vocabulary.find(word) }
    val deferred2 = scope.async { vocabulary.find(word2, withTime = 5000) } // Resume (with GlobalScope)

    println("Make some other stuff")
    println("Make very hard stuff")

//    println("deffered is active? ${deferred.isActive}; deffered2 is active? ${deferred2.isActive}")
    runCatching {
        println("Found ${deferred.await()} && ${deferred2.await()}")
    }.onFailure {
        println("Deferred still running? ${deferred.isActive}")
        println("Deferred is canceled? ${deferred.isCancelled}")
        println("Deferred2 still running? ${deferred2.isActive}")
        println("Deferred2 is canceled? ${deferred2.isCancelled}")
    }.getOrThrow()
    println("End some other stuff")
}
