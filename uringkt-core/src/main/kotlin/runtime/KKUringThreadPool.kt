package kkuring.runtime

import kkuring.cancel
import kkuring.nativetype.KernelTimespec
import kotlinx.coroutines.CancellableContinuation
import uringkt.binding.ILibUring
import uringkt.binding.IORING_TIMEOUT_ETIME_SUCCESS
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

    fun addDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        val thread = threads.random() // TODO
        val ts = KernelTimespec.alloc()
        ts.setSec(timeMillis / 1000)
        ts.setNsec((timeMillis % 1000) * 1_000_000)
        thread.queue.add {
            val context = localContext.get()
            val sqe = context.ring.getSqe()
            val key = context.save(continuation)
            sqe.setUserData(key)
            ILibUring.io_uring_prep_timeout(sqe.raw, ts.ptrHelper.raw, 1, 0)
            context.ring.submit()
            ts.free()
//            no need
//            continuation.invokeOnCancellation {
//                context.ring.cancel(key)
//            }
        }
        thread.notifyNop()
    }
}