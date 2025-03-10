@file:Suppress("Nothing_To_Inline")

package uringkt.binding

@JvmInline
public value class PtrHelper(
    public val raw: Ptr
)

public inline operator fun PtrHelper.plus(other: PtrHelper): PtrHelper {
    return PtrHelper(raw + other.raw)
}

public inline operator fun PtrHelper.minus(other: PtrHelper): PtrHelper {
    return PtrHelper(raw - other.raw)
}

public inline operator fun PtrHelper.plus(other: Ptr): PtrHelper {
    return PtrHelper(raw + other)
}

public inline operator fun PtrHelper.minus(other: Ptr): PtrHelper {
    return PtrHelper(raw - other)
}

public inline operator fun PtrHelper.get(index: Int): PtrHelper {
    return PtrHelper(raw + index)
}

public inline fun PtrHelper.pointedByte(): Byte {
    return KKUnsafe.theUnsafe.getByte(raw)
}

public inline fun PtrHelper.pointedInt(): Int {
    return KKUnsafe.theUnsafe.getInt(raw)
}

public inline fun PtrHelper.pointedLong(): Long {
    return KKUnsafe.theUnsafe.getLong(raw)
}

public inline fun PtrHelper.pointedFloat(): Float {
    return KKUnsafe.theUnsafe.getFloat(raw)
}

public inline fun PtrHelper.pointedDouble(): Double {
    return KKUnsafe.theUnsafe.getDouble(raw)
}

public inline fun PtrHelper.putByte(value: Byte) {
    KKUnsafe.theUnsafe.putByte(raw, value)
}

public inline fun PtrHelper.putInt(value: Int) {
    KKUnsafe.theUnsafe.putInt(raw, value)
}

public inline fun PtrHelper.putLong(value: Long) {
    KKUnsafe.theUnsafe.putLong(raw, value)
}

public inline fun PtrHelper.putFloat(value: Float) {
    KKUnsafe.theUnsafe.putFloat(raw, value)
}

public inline fun PtrHelper.putDouble(value: Double) {
    KKUnsafe.theUnsafe.putDouble(raw, value)
}

public inline fun PtrHelper.isNull(): Boolean {
    return raw == 0L
}


public inline fun PtrHelper.toHexString(): String {
    return "0x${raw.toString(16)}"
}