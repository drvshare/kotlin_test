package org.example.continuation


//fun coroutine(continuation: Continuation<Any?>) {
//    val cont: YourCustomContinuation = continuation as? YourCustomContinuation ?: object: YourCustomContinuation {
//        /** state coroutine */
//        private var label: Int = 0
//        override var result: T // Any
//
//        fun resumeWith(result: Result<T>) { // thenCompose
//            this.result = result
//            coroutine(this)
//        }
//    }
//    when(cont.label){
//        0 -> {
//            cont.label = 1
//            getText(cont)
//        }
//
//        1 -> {
//            val result = cont.result as String
//            printText(result)
//        }
//    }
//}
//
//fun getText(continuationFrom: Continuation, continutation: Continuation<T?>) {
//...
//
//    1 -> {
///
//    }
//    continuationFrom.resumeWith("hello, world")
//}
