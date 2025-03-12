package kkuring

import sun.misc.Unsafe
import uringkt.binding.Ptr
import java.nio.ByteBuffer
import java.util.ServiceLoader

public interface AddressHelper {
    public fun getObject(address: Ptr): Any?
    public fun getAddress(obj: Any, offset: Long): Ptr
    public fun getAddress(byteArray: ByteArray): Ptr {
        return getAddress(byteArray, Unsafe.ARRAY_BYTE_BASE_OFFSET.toLong())
    }
    public fun getAddress(shortArray: ShortArray): Ptr {
        return getAddress(shortArray, Unsafe.ARRAY_SHORT_BASE_OFFSET.toLong())
    }
    public fun getAddress(intArray: IntArray): Ptr {
        return getAddress(intArray, Unsafe.ARRAY_INT_BASE_OFFSET.toLong())
    }
    public fun getAddress(longArray: LongArray): Ptr {
        return getAddress(longArray, Unsafe.ARRAY_LONG_BASE_OFFSET.toLong())
    }
    public fun getAddress(charArray: CharArray): Ptr {
        return getAddress(charArray, Unsafe.ARRAY_CHAR_BASE_OFFSET.toLong())
    }
    public fun getAddress(buf: ByteBuffer): Ptr

    public companion object : AddressHelper by ServiceLoader.load(AddressHelper::class.java).first()
}
