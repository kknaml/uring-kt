package kkuring

import uringkt.binding.ILibUring
import uringkt.binding.KKUnsafe
import uringkt.binding.Ptr
import java.io.Closeable
import java.nio.ByteBuffer

public class Ring private constructor(
    public val ptr: Ptr
) : Closeable {

    public fun getSqe(): Sqe {
        return Sqe(ILibUring.io_uring_get_sqe(ptr))
    }

    public fun submit() {
        ILibUring.io_uring_submit(ptr)
    }

    public fun waitCqe(cqePp: Ptr) {
        ILibUring.io_uring_wait_cqe(ptr, cqePp)
    }

    override fun close() {
        if (ptr != 0L) {
            ILibUring.io_uring_close_ring_fd(ptr)
            KKUnsafe.theUnsafe.freeMemory(ptr)
        }
    }

    public companion object {
        public fun alloc(): Ring {
            val addr = KKUnsafe.theUnsafe.allocateMemory(4096)
            return Ring(addr)
        }
    }
}