package com.art_int_labs.lead_up.internal

import kotlinx.coroutines.*

fun <T> lazyDeferred(block:suspend CoroutineScope.()->T):Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}