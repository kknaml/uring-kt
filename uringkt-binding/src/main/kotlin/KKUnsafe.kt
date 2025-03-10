package uringkt.binding

import sun.misc.Unsafe

public object KKUnsafe {

    @JvmStatic
    public val theUnsafe: Unsafe = getUnsafe()

    private fun getUnsafe(): Unsafe {
        val f = Unsafe::class.java.getDeclaredField("theUnsafe")
        f.isAccessible = true
        return f.get(null) as Unsafe
    }
}
