package kkuring.runtime

import kkuring.Ring
import kotlinx.coroutines.CancellableContinuation

internal val localContext = ThreadLocal<KKUringContext>()

internal class KKUringContext(
    val ring: Ring
) {

    // better to use JNI object pointer
    private val continuationMap = mutableMapOf<Long, CancellableContinuation<*>>()
    private var count: Long = 0L

    fun save(continuation: CancellableContinuation<*>): Long {
        val key = count++
        continuationMap[key] = continuation
        return key
    }

    fun getAndRemove(key: Long): CancellableContinuation<*>? {
        return continuationMap.remove(key)
    }
}
