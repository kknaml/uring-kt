package kkuring.runtime

import java.util.concurrent.CountDownLatch

internal class KKUringThreadPool(
    val n: Int
) {

    private val threads = Array<KKUringThread>(n) { KKUringThread() }

    fun start() {
        val latch = CountDownLatch(n)
        threads.forEach {
            it.latch = latch
            it.start()
        }
        latch.await()
    }

    fun dispatch(runnable: Runnable) {
        val thread = threads.random() // TODO
        thread.queue.add(runnable)
        thread.notifyNop()
    }
}