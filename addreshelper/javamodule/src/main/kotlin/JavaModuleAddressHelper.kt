package uringkt.addresshelper

import kkuring.AddressHelper
import uringkt.binding.Ptr
import java.nio.Buffer
import java.nio.ByteBuffer

class JavaModuleAddressHelper : AddressHelper {
    override fun getObject(address: Ptr): Any? {
        TODO("Not yet implemented")
    }

    override fun getAddress(obj: Any, offset: Long): Ptr {
        return theInternalUnsafe.getAddress(obj, offset)
    }

    override fun getAddress(buf: ByteBuffer): Ptr {
        if (buf is sun.nio.ch.DirectBuffer) {
            return buf.address()
        }
        return getBufferAddress(buf)
    }

    companion object {
        private val theInternalUnsafe: jdk.internal.misc.Unsafe = jdk.internal.misc.Unsafe.getUnsafe()

        private val bufferClass by lazy {
            Buffer::class.java
        }
        private val bufferAddressField by lazy {
            bufferClass.getDeclaredField("address").apply { isAccessible = true }
        }
        private fun getBufferAddress(buf: ByteBuffer): Ptr {
            return bufferAddressField.get(buf) as Ptr
        }
    }
}
