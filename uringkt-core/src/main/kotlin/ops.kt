package kkuring

import kkuring.runtime.localContext
import kotlinx.coroutines.suspendCancellableCoroutine
import uringkt.binding.ILibUring
import uringkt.binding.Ptr

public suspend fun Ring.accept(fd: Int, addr: Ptr, addrLen: Ptr, flag: Int, submit: Boolean = true): Int {
    return suspendCancellableCoroutine {
        val key = localContext.get().save(it)
        val sqe = getSqe()
        sqe.setUserData(key)
        ILibUring.io_uring_prep_accept(sqe.raw, fd, addr, addrLen, flag)
        if (submit) {
            submit()
        }
        it.invokeOnCancellation {
            cancel(key)
        }
    }
}
