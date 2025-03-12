package uringkt.addresshelper

import uringkt.binding.Ptr
import java.nio.ByteBuffer

internal object JNIBridge {

    init {
        System.loadLibrary("kkaddress")
    }

    @JvmStatic
    external fun getObjectAddress(obj: Any, offset: Long): Ptr

    @JvmStatic
    external fun getObjectFromAddress(ptr: Ptr): Any?

    @JvmStatic
    external fun getByteBufferAddress(buffer: ByteBuffer): Ptr
}
