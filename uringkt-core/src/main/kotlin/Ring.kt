package kkuring

import uringkt.binding.ILibUring
import uringkt.binding.KKUnsafe
import uringkt.binding.Ptr
import java.io.Closeable

public class Ring private constructor(
    public val ptr: Ptr
) : Closeable {

    public fun getSqe(): Sqe {
        return Sqe(ILibUring.io_uring_get_sqe(ptr))
    }

    override fun close() {
        if (ptr != 0L) {
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