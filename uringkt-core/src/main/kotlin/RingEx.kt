package kkuring

import uringkt.binding.ILibUring
import uringkt.binding.Ptr

public fun Ring.submitNop(data: Ptr) {
    val sqe = getSqe()
    sqe.setUserData(data)
    ILibUring.io_uring_prep_nop(sqe.raw)
    submit()
}

public fun Ring.cancel(data: Ptr, flags: Int = 0) {
    val sqe = getSqe()
    sqe.setUserData(-10086)
    ILibUring.io_uring_prep_cancel(sqe.raw, data, flags)
    submit()
}