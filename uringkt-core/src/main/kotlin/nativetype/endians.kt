package kkuring.nativetype

public fun Int.swapEndian(): Int {
    return ((this and 0xFF) shl 24) or
            ((this and 0xFF00) shl 8) or
            ((this and 0xFF0000) ushr 8) or
            ((this and 0xFF000000.toInt()) ushr 24)
}

public fun Long.swapEndian(): Long {
    return ((this and 0xFF) shl 56) or
            ((this and 0xFF00) shl 40) or
            ((this and 0xFF0000) shl 24) or
            ((this and 0xFF000000) shl 8) or
            ((this and 0xFF00000000) ushr 8) or
            ((this and 0xFF0000000000) ushr 24) or
            ((this and 0xFF000000000000) ushr 40) or
            ((this and 0xFF00000000000000UL.toLong()) ushr 56)
}