package kkuring.nativetype

import uringkt.binding.*

@JvmInline
public value class KernelTimespec private constructor(
    public val ptrHelper: PtrHelper
) {

    public fun free() {
        KKUnsafe.theUnsafe.freeMemory(ptrHelper.raw)
    }

    public fun getSec(): Long {
        return ptrHelper.pointedLong()
    }

    public fun getNsec(): Long {
        return ptrHelper[8].pointedLong()
    }

    public fun setSec(sec: Long) {
        ptrHelper.putLong(sec)
    }

    public fun setNsec(nsec: Long) {
        ptrHelper[8].putLong(nsec)
    }

    public companion object {

        /**
         * It's caller's responsibility to free thr ptr
         */
        public fun alloc(): KernelTimespec {
            val address = KKUnsafe.theUnsafe.allocateMemory(64 * 2)
            return KernelTimespec(PtrHelper(address))
        }
    }
}