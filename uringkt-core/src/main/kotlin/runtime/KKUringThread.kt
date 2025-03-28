package kkuring.runtime

import kkuring.Cqe
import kkuring.Ring
import kkuring.submitNop
import kotlinx.coroutines.CancellableContinuation
import uringkt.binding.ILibUring
import uringkt.binding.KKUnsafe
import uringkt.binding.PtrHelper
import uringkt.binding.pointedLong
import java.io.Closeable
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.coroutines.resume

internal class KKUringThread() : Thread(), Closeable {

    init {
        name = "KKUringThread #" + hashCode()
    }

    internal val queue = ArrayBlockingQueue<Runnable>(1024)

    internal var context: KKUringContext? = null

    private var cqePtr = KKUnsafe.theUnsafe.allocateMemory(8)
    private val lock = ReentrantLock()

    internal var latch: CountDownLatch? = null

    fun notifyNop() {
        if (queue.isEmpty()) return
        if (lock.tryLock()) {
            context!!.ring.submitNop(-1234)
            lock.unlock()
        }
    }

    fun stop(cause: Throwable? = null) {
        lock.withLock {
            context!!.ring.submitNop(-1235)
        }
    }

    override fun run() {
        val context = KKUringContext(Ring.alloc())
        context.ring.init()
        this.context = context
        localContext.set(context)

        latch!!.countDown()
        latch = null

        try {
            while (true) {
                try {
                    val poll = queue.poll()
                    if (poll != null) {
                        lock.withLock { poll.run() }
                    } else {
                        handleRingEvent()
                    }
                } catch (e: KKUringCancel) {
                    // TODO log
                    break
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } finally {
            this.context = null
            localContext.get().ring.close()
            localContext.remove()
        }
    }

    private fun handleRingEvent() {
        try {
            val wait = ILibUring.io_uring_wait_cqe(context!!.ring.ptr, cqePtr)
            if (wait != 0) {
                // TODO check -ETIME or error
                val cqe = Cqe(PtrHelper(cqePtr).pointedLong())
                println(cqe.getRes())
                System.err.println("Error in io_uring_wait_cqe $wait")
            } else {
                resumeCqe(PtrHelper(cqePtr).pointedLong())
            }
        } finally {
            ILibUring.io_uring_cqe_seen(context!!.ring.ptr, PtrHelper(cqePtr).pointedLong())
        }
    }

    private fun resumeCqe(cqe: Long) {
        if (cqe == 0L) {
            // TODO
        }
        val cqeObj = Cqe(cqe)
        val data = cqeObj.getUserData()
        if (data == -1234L) {
            return // nop from notifyNop
        }
        if (data == -1235L) {
            throw KKUringCancel("canceled")
        }
        val res = cqeObj.getRes()
        val continuation = context!!.getAndRemove(data) as CancellableContinuation<Int>
        lock.withLock { continuation.resume(res) }
    }

    override fun close() {
        if (cqePtr != 0L) {
            KKUnsafe.theUnsafe.freeMemory(cqePtr)
            cqePtr = 0
        }
    }

    override fun toString(): String {
        return "KKUringThread" + hashCode()
    }

}
