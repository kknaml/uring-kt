package kkuring

import uringkt.binding.ILibUring
import uringkt.binding.Ptr

@JvmInline
public value class Sqe(
    public val raw: Ptr
) {

    public fun setUserData(data: Ptr) {
        ILibUring.io_uring_sqe_set_data(raw, data)
    }
}