package kkuring.util

import kkuring.Cqe
import kkuring.Ring
import uringkt.binding.ILibUring
import uringkt.binding.KKUnsafe
import uringkt.binding.PtrHelper
import uringkt.binding.pointedLong
import java.io.Closeable
import java.util.concurrent.ArrayBlockingQueue

internal val localRing: ThreadLocal<Ring> = ThreadLocal()

internal class KKUringThread() : Thread(), Closeable {

    init {
        name = "KKUringThread #" + hashCode()
    }

    internal val queue = ArrayBlockingQueue<Runnable>(1024)

    internal var ring: Ring? = null

    private var cqePtr = KKUnsafe.theUnsafe.allocateMemory(8)

    fun notify() {
        if (queue.isEmpty()) return
        // TODO send NOP to this.ring
    }

    override fun run() {
        val ring = Ring.alloc()
        this.ring = ring
        localRing.set(ring)
        try {
            while (true) {
                try {
                    val poll = queue.poll()
                    if (poll != null) {
                        poll.run()
                    } else {
                        handleRingEvent()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } finally {
            this.ring = null
            localRing.get().close()
            localRing.set(null)
        }
    }

    private fun handleRingEvent() {
        val wait = ILibUring.io_uring_wait_cqe(ring!!.ptr, cqePtr)
        if (wait != 0) {
            // TODO check -ETIME or error
        } else {
            resumeCqe(PtrHelper(cqePtr).pointedLong())
        }
    }

    private fun resumeCqe(cqe: Long) {
        if (cqe == 0L) {
            // TODO
        }
        val cqeObj = Cqe(cqe)
        val data = cqeObj.getUserData()
        val res = cqeObj.getRes()
        // TODO get continuation and resume data
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
