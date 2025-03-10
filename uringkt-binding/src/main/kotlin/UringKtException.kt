package uringkt.binding

public class UringKtException(
    msg: String,
    cause: Throwable? = null
) : RuntimeException(msg, cause)
