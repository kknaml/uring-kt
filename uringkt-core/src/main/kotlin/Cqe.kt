package kkuring

import uringkt.binding.*

/**
 * struct io_uring_cqe {
 * 	__u64	user_data;	/* sqe->user_data value passed back */
 * 	__s32	res;		/* result code for this event */
 * 	__u32	flags;
 *
 * 	/*
 * 	 * If the ring is initialized with IORING_SETUP_CQE32, then this field
 * 	 * contains 16-bytes of padding, doubling the size of the CQE.
 * 	 */
 * 	__u64 big_cqe[];
 * };
 */
@JvmInline
public value class Cqe private constructor(
    public val ptrHelper: PtrHelper
) {
    public fun getUserData(): Ptr {
        return ILibUring.io_uring_cqe_get_data(ptrHelper.raw)
    }

    public fun getRes(): Int {
        return ptrHelper[8].pointedInt()
    }

    public fun getFlags(): Int {
        return ptrHelper[12].pointedInt()
    }

    public companion object {
        public operator fun invoke(raw: Ptr): Cqe {
            return Cqe(PtrHelper(raw))
        }
    }
}