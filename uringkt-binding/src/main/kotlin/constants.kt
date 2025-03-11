package uringkt.binding

public const val ETIME: Int = 62

public const val IORING_TIMEOUT_ABS: Int = 1 shl 0
public const val IORING_TIMEOUT_UPDATE: Int = 1 shl 1
public const val IORING_TIMEOUT_BOOTTIME: Int = 1 shl 2
public const val IORING_TIMEOUT_REALTIME: Int = 1 shl 3
public const val IORING_LINK_TIMEOUT_UPDATE: Int = 1 shl 4
public const val IORING_TIMEOUT_ETIME_SUCCESS: Int = 1 shl 5
public const val IORING_TIMEOUT_MULTISHOT: Int = 1 shl 6
public const val IORING_TIMEOUT_CLOCK_MASK: Int = IORING_TIMEOUT_BOOTTIME or IORING_TIMEOUT_REALTIME
public const val IORING_TIMEOUT_UPDATE_MASK: Int = IORING_TIMEOUT_UPDATE or IORING_LINK_TIMEOUT_UPDATE
