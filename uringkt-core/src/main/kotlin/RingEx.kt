package kkuring

import uringkt.binding.ILibUring
import uringkt.binding.Ptr

public fun Ring.submitNop(data: Ptr) {
    val sqe = getSqe()
    sqe.setUserData(data)
    ILibUring.io_uring_prep_nop(sqe.raw)
    submit()
}