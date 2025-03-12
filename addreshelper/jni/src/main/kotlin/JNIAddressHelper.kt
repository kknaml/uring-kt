package uringkt.addresshelper

import kkuring.AddressHelper
import uringkt.binding.Ptr
import java.nio.ByteBuffer


class JNIAddressHelper : AddressHelper {
    override fun getObject(address: Ptr): Any? {
        return JNIBridge.getObjectFromAddress(address)
    }

    override fun getAddress(obj: Any, offset: Long): Ptr {
        return JNIBridge.getObjectAddress(obj, offset)
    }

    override fun getAddress(buf: ByteBuffer): Ptr {
        return JNIBridge.getByteBufferAddress(buf)
    }
}
