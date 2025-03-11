package uringkt.binding.internal

import uringkt.binding.ILibUring
import uringkt.binding.Ptr
import java.lang.foreign.FunctionDescriptor
import java.lang.invoke.MethodHandles
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

private fun init() {
    val property = System.getProperty("uring_path")

    try {
        if (property != null) {
            System.load(property)
        } else {
            System.loadLibrary("uring-ffi")
        }
    } catch (e: Throwable) {
        throw RuntimeException("Failed to load liburng library", e)
    }

}

@Suppress("UNUSED")
private val _clinit = init()

internal val instance: ILibUring = bind()

private var _instance: ILibUring? = null

private var isBinded = false

@Suppress("LocalVariableName")
@Synchronized
private fun bind(): ILibUring {
    if (isBinded) {
        return _instance!!
    }
    val start = System.currentTimeMillis()

    val linker = Linker.nativeLinker()
    val symbolLookup = SymbolLookup.loaderLookup()

    val io_uring_get_probe_ring = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_get_probe_ring",
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_LONG,
    )
    val io_uring_get_probe = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_get_probe",
        ValueLayout.JAVA_LONG
    )
    val io_uring_free_probe = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_free_probe",
        ValueLayout.JAVA_LONG
    )
    val io_uring_opcode_supported = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_opcode_supported",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_queue_init_mem = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_queue_init_mem",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_queue_init_params = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_queue_init_params",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_queue_init = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_queue_init",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_queue_mmap = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_queue_mmap",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_ring_dontfork = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_ring_dontfork",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_queue_exit = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_queue_exit",
        ValueLayout.JAVA_LONG
    )
    val io_uring_peek_batch_cqe = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_peek_batch_cqe",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_wait_cqes = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_wait_cqes",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_wait_cqes_min_timeout = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_wait_cqes_min_timeout",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_wait_cqe_timeout = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_wait_cqe_timeout",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG,
    )
    val io_uring_submit = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_submit",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_submit_and_wait = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_submit_and_wait",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_submit_and_wait_timeout = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_submit_and_wait_timeout",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_submit_and_wait_min_timeout = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_submit_and_wait_min_timeout",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_submit_and_wait_reg = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_submit_and_wait_reg",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT
    )
    val io_uring_register_wait_reg = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_wait_reg",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_resize_rings = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_resize_rings",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_clone_buffers_offset = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_clone_buffers_offset",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_clone_buffers = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_clone_buffers",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_register_buffers = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_buffers",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_buffers_tags = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_buffers_tags",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_buffers_sparse = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_buffers_sparse",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_buffers_update_tag = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_buffers_update_tag",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_unregister_buffers = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_buffers",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_register_files = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_files",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_files_tags = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_files_tags",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_files_sparse = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_files_sparse",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_files_update_tag = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_files_update_tag",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_unregister_files = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_files",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_register_files_update = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_files_update",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_eventfd = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_eventfd",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_eventfd_async = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_eventfd_async",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_unregister_eventfd = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_eventfd",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_register_probe = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_probe",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_personality = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_personality",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_unregister_personality = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_personality",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_restrictions = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_restrictions",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_enable_rings = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_enable_rings",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val __io_uring_sqring_wait = loadUringNativeFunction(
        linker, symbolLookup, "__io_uring_sqring_wait",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_register_iowq_aff = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_iowq_aff",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_unregister_iowq_aff = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_iowq_aff",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_register_iowq_max_workers = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_iowq_max_workers",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_register_ring_fd = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_ring_fd",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_unregister_ring_fd = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_ring_fd",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_close_ring_fd = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_close_ring_fd",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_register_buf_ring = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_buf_ring",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_unregister_buf_ring = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_buf_ring",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_buf_ring_head = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_buf_ring_head",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_register_sync_cancel = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_sync_cancel",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_register_file_alloc_range = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_file_alloc_range",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_register_napi = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_napi",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_unregister_napi = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_unregister_napi",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_register_ifq = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_ifq",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_register_clock = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_clock",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_get_events = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_get_events",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_submit_and_get_events = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_submit_and_get_events",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG
    )
    val io_uring_enter = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_enter",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_enter2 = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_enter2",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_setup = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_setup",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_register = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_register_region = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_register_region",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_setup_buf_ring = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_setup_buf_ring",
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_free_buf_ring = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_free_buf_ring",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val __io_uring_get_cqe = loadUringNativeFunction(
        linker, symbolLookup, "__io_uring_get_cqe",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_mlock_size = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_mlock_size",
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_mlock_size_params = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_mlock_size_params",
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_major_version = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_major_version",
        ValueLayout.JAVA_INT
    )
    val io_uring_minor_version = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_minor_version",
        ValueLayout.JAVA_INT
    )
    val io_uring_check_version = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_check_version",
        ValueLayout.JAVA_BOOLEAN,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )

    val io_uring_wait_cqe_nr = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_wait_cqe_nr",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_peek_cqe = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_peek_cqe",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_wait_cqe = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_wait_cqe",
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_cq_advance = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_cq_advance",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_cqe_seen = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_cqe_seen",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_sqe_set_data = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_sqe_set_data",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_cqe_get_data = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_cqe_get_data",
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_LONG
    )
    val io_uring_sqe_set_flags = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_sqe_set_flags",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_sqe_set_buf_group = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_sqe_set_buf_group",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_initialize_sqe = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_initialize_sqe",
        ValueLayout.JAVA_LONG
    )
    val io_uring_prep_rw = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_rw",
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_nop = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_nop",
        ValueLayout.JAVA_LONG
    )
    val io_uring_prep_readv = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_readv",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_writev = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_writev",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_poll_add = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_poll_add",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_poll_remove = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_poll_remove",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_timeout = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_timeout",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_accept = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_accept",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_prep_connect = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_connect",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_prep_close = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_close",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_prep_fsync = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_fsync",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_recv = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_recv",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_send = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_send",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_openat = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_openat",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_statx = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_statx",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_fallocate = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_fallocate",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_provide_buffers = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_provide_buffers",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_remove_buffers = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_remove_buffers",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_shutdown = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_shutdown",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_unlinkat = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_unlinkat",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_prep_renameat = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_renameat",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_prep_socket = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_socket",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_msg_ring = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_msg_ring",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_prep_cmd_sock = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_cmd_sock",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )
    val io_uring_prep_futex_wait = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_futex_wait",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_futex_wake = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_futex_wake",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_futex_waitv = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_futex_waitv",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_fixed_fd_install = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_fixed_fd_install",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_prep_ftruncate = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_ftruncate",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_cmd_discard = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_cmd_discard",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_waitid = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_waitid",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
    )
    val io_uring_get_sqe = loadUringNativeFunction(
        linker, symbolLookup, "io_uring_get_sqe",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_read = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_read",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_write = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_write",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG
    )
    val io_uring_prep_cancel = loadVoidUringNativeFunction(
        linker, symbolLookup, "io_uring_prep_cancel",
        ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT
    )

    val spent = System.currentTimeMillis() - start
    val result = object : ILibUring {
        // NPE when function not found

        override fun loadTime(): Long {
            return spent
        }

        override fun io_uring_get_probe_ring(ring: Ptr): Ptr {
            return io_uring_get_probe_ring!!.invokeExact(ring) as Ptr
        }

        override fun io_uring_get_probe(): Ptr {
            return io_uring_get_probe!!.invokeExact() as Ptr
        }

        override fun io_uring_free_probe(probe: Ptr) {
            io_uring_free_probe!!.invokeExact(probe)
        }

        override fun io_uring_opcode_supported(probe: Ptr, op: Int): Int {
            return io_uring_opcode_supported!!.invokeExact(probe, op) as Int
        }

        override fun io_uring_queue_init_mem(entries: Int, ring: Ptr, ioUringParams: Ptr, buf: Ptr, bufSize: Long): Int {
            return io_uring_queue_init_mem!!.invokeExact(entries, ring, ioUringParams, buf, bufSize) as Int
        }

        override fun io_uring_queue_init_params(entries: Int, ring: Ptr, params: Ptr): Int {
            return io_uring_queue_init_params!!.invokeExact(entries, ring, params) as Int
        }

        override fun io_uring_queue_init(entries: Int, ring: Ptr, flags: Int): Int {
            return io_uring_queue_init!!.invokeExact(entries, ring, flags) as Int
        }

        override fun io_uring_queue_mmap(fd: Int, params: Ptr, ring: Ptr): Int {
            return io_uring_queue_mmap!!.invokeExact(fd, params, ring) as Int
        }

        override fun io_uring_ring_dontfork(ring: Ptr): Int {
            return io_uring_ring_dontfork!!.invokeExact(ring) as Int
        }

        override fun io_uring_queue_exit(ring: Ptr) {
            io_uring_queue_exit!!.invokeExact(ring)
        }

        override fun io_uring_peek_batch_cqe(ring: Ptr, cqes: Ptr, count: Int): Int {
            return io_uring_peek_batch_cqe!!.invokeExact(ring, cqes, count) as Int
        }

        override fun io_uring_wait_cqes(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, sigmask: Ptr): Int {
            return io_uring_wait_cqes!!.invokeExact(ring, cqePtr, waitNr, ts, sigmask) as Int
        }

        override fun io_uring_wait_cqes_min_timeout(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, minTsUsec: Int, sigmask: Ptr): Int {
            return io_uring_wait_cqes_min_timeout!!.invokeExact(ring, cqePtr, waitNr, ts, minTsUsec, sigmask) as Int
        }

        override fun io_uring_wait_cqe_timeout(ring: Ptr, cqePtr: Ptr, ts: Ptr): Int {
            return io_uring_wait_cqe_timeout!!.invokeExact(ring, cqePtr, ts) as Int
        }

        override fun io_uring_submit(ring: Ptr): Int {
            return io_uring_submit!!.invokeExact(ring) as Int
        }

        override fun io_uring_submit_and_wait(ring: Ptr, waitNr: Int): Int {
            return io_uring_submit_and_wait!!.invokeExact(ring, waitNr) as Int
        }

        override fun io_uring_submit_and_wait_timeout(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, sigmask: Ptr): Int {
            return io_uring_submit_and_wait_timeout!!.invokeExact(ring, cqePtr, waitNr, ts, sigmask) as Int
        }

        override fun io_uring_submit_and_wait_min_timeout(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, minWait: Int, sigmask: Ptr): Int {
            return io_uring_submit_and_wait_min_timeout!!.invokeExact(ring, cqePtr, waitNr, ts, minWait, sigmask) as Int
        }

        override fun io_uring_submit_and_wait_reg(ring: Ptr, cqePtr: Ptr, waitNr: Int, regIndex: Int): Int {
            return io_uring_submit_and_wait_reg!!.invokeExact(ring, cqePtr, waitNr, regIndex) as Int
        }

        override fun io_uring_register_wait_reg(ring: Ptr, reg: Ptr, nr: Int): Int {
            return io_uring_register_wait_reg!!.invokeExact(ring, reg, nr) as Int
        }

        override fun io_uring_resize_rings(ring: Ptr, params: Ptr): Int {
            return io_uring_resize_rings!!.invokeExact(ring, params) as Int
        }

        override fun io_uring_clone_buffers_offset(dst: Ptr, src: Ptr, dstOff: Int, srcOff: Int, nr: Int, flags: Int): Int {
            return io_uring_clone_buffers_offset!!.invokeExact(dst, src, dstOff, srcOff, nr, flags) as Int
        }

        override fun io_uring_clone_buffers(dst: Ptr, src: Ptr): Int {
            return io_uring_clone_buffers!!.invokeExact(dst, src) as Int
        }

        override fun io_uring_register_buffers(ring: Ptr, iovecs: Ptr, nrIovecs: Int): Int {
            return io_uring_register_buffers!!.invokeExact(ring, iovecs, nrIovecs) as Int
        }

        override fun io_uring_register_buffers_tags(ring: Ptr, iovecs: Ptr, tags: Ptr, nr: Int): Int {
            return io_uring_register_buffers_tags!!.invokeExact(ring, iovecs, tags, nr) as Int
        }

        override fun io_uring_register_buffers_sparse(ring: Ptr, nr: Int): Int {
            return io_uring_register_buffers_sparse!!.invokeExact(ring, nr) as Int
        }

        override fun io_uring_register_buffers_update_tag(ring: Ptr, off: Int, iovecs: Ptr, tags: Ptr, nr: Int): Int {
            return io_uring_register_buffers_update_tag!!.invokeExact(ring, off, iovecs, tags, nr) as Int
        }

        override fun io_uring_unregister_buffers(ring: Ptr): Int {
            return io_uring_unregister_buffers!!.invokeExact(ring) as Int
        }

        override fun io_uring_register_files(ring: Ptr, files: Ptr, nrFiles: Int): Int {
            return io_uring_register_files!!.invokeExact(ring, files, nrFiles) as Int
        }

        override fun io_uring_register_files_tags(ring: Ptr, files: Ptr, tags: Ptr, nr: Int): Int {
            return io_uring_register_files_tags!!.invokeExact(ring, files, tags, nr) as Int
        }

        override fun io_uring_register_files_sparse(ring: Ptr, nr: Int): Int {
            return io_uring_register_files_sparse!!.invokeExact(ring, nr) as Int
        }

        override fun io_uring_register_files_update_tag(ring: Ptr, off: Int, files: Ptr, tags: Ptr, nrFiles: Int): Int {
            return io_uring_register_files_update_tag!!.invokeExact(ring, off, files, tags, nrFiles) as Int
        }

        override fun io_uring_unregister_files(ring: Ptr): Int {
            return io_uring_unregister_files!!.invokeExact(ring) as Int
        }

        override fun io_uring_register_files_update(ring: Ptr, off: Int, files: Ptr, nrFiles: Int): Int {
            return io_uring_register_files_update!!.invokeExact(ring, off, files, nrFiles) as Int
        }

        override fun io_uring_register_eventfd(ring: Ptr, fd: Int): Int {
            return io_uring_register_eventfd!!.invokeExact(ring, fd) as Int
        }

        override fun io_uring_register_eventfd_async(ring: Ptr, fd: Int): Int {
            return io_uring_register_eventfd_async!!.invokeExact(ring, fd) as Int
        }

        override fun io_uring_unregister_eventfd(ring: Ptr): Int {
            return io_uring_unregister_eventfd!!.invokeExact(ring) as Int
        }

        override fun io_uring_register_probe(ring: Ptr, probe: Ptr, nr: Int): Int {
            return io_uring_register_probe!!.invokeExact(ring, probe, nr) as Int
        }

        override fun io_uring_register_personality(ring: Ptr): Int {
            return io_uring_register_personality!!.invokeExact(ring) as Int
        }

        override fun io_uring_unregister_personality(ring: Ptr, id: Int): Int {
            return io_uring_unregister_personality!!.invokeExact(ring, id) as Int
        }

        override fun io_uring_register_restrictions(ring: Ptr, res: Ptr, nrRes: Int): Int {
            return io_uring_register_restrictions!!.invokeExact(ring, res, nrRes) as Int
        }

        override fun io_uring_enable_rings(ring: Ptr): Int {
            return io_uring_enable_rings!!.invokeExact(ring) as Int
        }

        override fun __io_uring_sqring_wait(ring: Ptr): Int {
            return __io_uring_sqring_wait!!.invokeExact(ring) as Int
        }

        override fun io_uring_register_iowq_aff(ring: Ptr, cpusz: Long, mask: Ptr): Int {
            return io_uring_register_iowq_aff!!.invokeExact(ring, cpusz, mask) as Int
        }

        override fun io_uring_unregister_iowq_aff(ring: Ptr): Int {
            return io_uring_unregister_iowq_aff!!.invokeExact(ring) as Int
        }

        override fun io_uring_register_iowq_max_workers(ring: Ptr, values: Ptr): Int {
            return io_uring_register_iowq_max_workers!!.invokeExact(ring, values) as Int
        }

        override fun io_uring_register_ring_fd(ring: Ptr): Int {
            return io_uring_register_ring_fd!!.invokeExact(ring) as Int
        }

        override fun io_uring_unregister_ring_fd(ring: Ptr): Int {
            return io_uring_unregister_ring_fd!!.invokeExact(ring) as Int
        }

        override fun io_uring_close_ring_fd(ring: Ptr): Int {
            return io_uring_close_ring_fd!!.invokeExact(ring) as Int
        }

        override fun io_uring_register_buf_ring(ring: Ptr, reg: Ptr, flags: Int): Int {
            return io_uring_register_buf_ring!!.invokeExact(ring, reg, flags) as Int
        }

        override fun io_uring_unregister_buf_ring(ring: Ptr, bgid: Int): Int {
            return io_uring_unregister_buf_ring!!.invokeExact(ring, bgid) as Int
        }

        override fun io_uring_buf_ring_head(ring: Ptr, bufGroup: Int, head: Ptr): Int {
            return io_uring_buf_ring_head!!.invokeExact(ring, bufGroup, head) as Int
        }

        override fun io_uring_register_sync_cancel(ring: Ptr, reg: Ptr): Int {
            return io_uring_register_sync_cancel!!.invokeExact(ring, reg) as Int
        }

        override fun io_uring_register_file_alloc_range(ring: Ptr, off: Int, len: Int): Int {
            return io_uring_register_file_alloc_range!!.invokeExact(ring, off, len) as Int
        }

        override fun io_uring_register_napi(ring: Ptr, napi: Ptr): Int {
            return io_uring_register_napi!!.invokeExact(ring, napi) as Int
        }

        override fun io_uring_unregister_napi(ring: Ptr, napi: Ptr): Int {
            return io_uring_unregister_napi!!.invokeExact(ring, napi) as Int
        }

        override fun io_uring_register_ifq(ring: Ptr, reg: Ptr): Int {
            return io_uring_register_ifq!!.invokeExact(ring, reg) as Int
        }

        override fun io_uring_register_clock(ring: Ptr, arg: Ptr): Int {
            return io_uring_register_clock!!.invokeExact(ring, arg) as Int
        }

        override fun io_uring_get_events(ring: Ptr): Int {
            return io_uring_get_events!!.invokeExact(ring) as Int
        }

        override fun io_uring_submit_and_get_events(ring: Ptr): Int {
            return io_uring_submit_and_get_events!!.invokeExact(ring) as Int
        }

        override fun io_uring_enter(fd: Int, toSubmit: Int, minComplete: Int, flags: Int, sig: Ptr): Int {
            return io_uring_enter!!.invokeExact(fd, toSubmit, minComplete, flags, sig) as Int
        }

        override fun io_uring_enter2(fd: Int, toSubmit: Int, minComplete: Int, flags: Int, sig: Ptr, sz: Long): Int {
            return io_uring_enter2!!.invokeExact(fd, toSubmit, minComplete, flags, sig, sz) as Int
        }

        override fun io_uring_setup(entries: Int, params: Ptr): Int {
            return io_uring_setup!!.invokeExact(entries, params) as Int
        }

        override fun io_uring_register(fd: Int, opcode: Int, arg: Ptr, nrArgs: Int): Int {
            return io_uring_register!!.invokeExact(fd, opcode, arg, nrArgs) as Int
        }

        override fun io_uring_register_region(ring: Ptr, reg: Ptr): Int {
            return io_uring_register_region!!.invokeExact(ring, reg) as Int
        }

        override fun io_uring_setup_buf_ring(ring: Ptr, nentries: Int, bgid: Int, flags: Int, err: Ptr): Ptr {
            return io_uring_setup_buf_ring!!.invokeExact(ring, nentries, bgid, flags, err) as Ptr
        }

        override fun io_uring_free_buf_ring(ring: Ptr, br: Ptr, nentries: Int, bgid: Int): Int {
            return io_uring_free_buf_ring!!.invokeExact(ring, br, nentries, bgid) as Int
        }

        override fun __io_uring_get_cqe(ring: Ptr, cqePtr: Ptr, submit: Int, waitNr: Int, sigmask: Ptr): Int {
            return __io_uring_get_cqe!!.invokeExact(ring, cqePtr, submit, waitNr, sigmask) as Int
        }

        override fun io_uring_mlock_size(entries: Int, flags: Int): Long {
            return io_uring_mlock_size!!.invokeExact(entries, flags) as Long
        }

        override fun io_uring_mlock_size_params(entries: Int, params: Ptr): Long {
            return io_uring_mlock_size_params!!.invokeExact(entries, params) as Long
        }

        override fun io_uring_major_version(): Int {
            return io_uring_major_version!!.invokeExact() as Int
        }

        override fun io_uring_minor_version(): Int {
            return io_uring_minor_version!!.invokeExact() as Int
        }

        override fun io_uring_check_version(major: Int, minor: Int): Boolean {
            return io_uring_check_version!!.invokeExact(major, minor) as Boolean
        }

        override fun io_uring_wait_cqe_nr(ring: Ptr, cqePtr: Ptr, waitNr: Int): Int {
            return io_uring_wait_cqe_nr!!.invokeExact(ring, cqePtr, waitNr) as Int
        }

        override fun io_uring_peek_cqe(ring: Ptr, cqePtr: Ptr): Int {
            return io_uring_peek_cqe!!.invokeExact(ring, cqePtr) as Int
        }

        override fun io_uring_wait_cqe(ring: Ptr, cqePtr: Ptr): Int {
            return io_uring_wait_cqe!!.invokeExact(ring, cqePtr) as Int
        }

        override fun io_uring_cq_advance(ring: Ptr, nr: Int) {
            io_uring_cq_advance!!.invokeExact(ring, nr)
        }

        override fun io_uring_cqe_seen(ring: Ptr, cqe: Ptr) {
            io_uring_cqe_seen!!.invokeExact(ring, cqe)
        }

        override fun io_uring_sqe_set_data(sqe: Ptr, data: Ptr) {
            io_uring_sqe_set_data!!.invokeExact(sqe, data)
        }

        override fun io_uring_cqe_get_data(cqe: Ptr): Ptr {
            return io_uring_cqe_get_data!!.invokeExact(cqe) as Ptr
        }

        override fun io_uring_sqe_set_flags(sqe: Ptr, flags: Int) {
            io_uring_sqe_set_flags!!.invokeExact(sqe, flags)
        }

        override fun io_uring_sqe_set_buf_group(sqe: Ptr, bgid: Int) {
            io_uring_sqe_set_buf_group!!.invokeExact(sqe, bgid)
        }

        override fun io_uring_initialize_sqe(sqe: Ptr) {
            io_uring_initialize_sqe!!.invokeExact(sqe)
        }

        override fun io_uring_prep_rw(op: Int, sqe: Ptr, fd: Int, addr: Ptr, len: Int, offset: Long) {
            io_uring_prep_rw!!.invokeExact(op, sqe, fd, addr, len, offset)
        }

        override fun io_uring_prep_nop(sqe: Ptr) {
            io_uring_prep_nop!!.invokeExact(sqe)
        }

        override fun io_uring_prep_readv(sqe: Ptr, fd: Int, iovecs: Ptr, nrVecs: Int, offset: Long) {
            io_uring_prep_readv!!.invokeExact(sqe, fd, iovecs, nrVecs, offset)
        }

        override fun io_uring_prep_writev(sqe: Ptr, fd: Int, iovecs: Ptr, nrVecs: Int, offset: Long) {
            io_uring_prep_writev!!.invokeExact(sqe, fd, iovecs, nrVecs, offset)
        }

        override fun io_uring_prep_poll_add(sqe: Ptr, fd: Int, pollMask: Int) {
            io_uring_prep_poll_add!!.invokeExact(sqe, fd, pollMask)
        }

        override fun io_uring_prep_poll_remove(sqe: Ptr, userData: Long) {
            io_uring_prep_poll_remove!!.invokeExact(sqe, userData)
        }

        override fun io_uring_prep_timeout(sqe: Ptr, ts: Ptr, count: Int, flags: Int) {
            io_uring_prep_timeout!!.invokeExact(sqe, ts, count, flags)
        }

        override fun io_uring_prep_read(sqe: Ptr, fd: Int, buf: Ptr, nbytes: Int, offset: Long) {
            io_uring_prep_read!!.invokeExact(sqe, fd, buf, nbytes, offset)
        }

        override fun io_uring_prep_write(sqe: Ptr, fd: Int, buf: Ptr, nbytes: Int, offset: Long) {
            io_uring_prep_write!!.invokeExact(sqe, fd, buf, nbytes, offset)
        }

        override fun io_uring_prep_accept(sqe: Ptr, fd: Int, addr: Ptr, addrlen: Ptr, flags: Int) {
            io_uring_prep_accept!!.invokeExact(sqe, fd, addr, addr, flags)
        }

        override fun io_uring_prep_connect(sqe: Ptr, fd: Int, addr: Ptr, addrlen: Int) {
            io_uring_prep_connect!!.invokeExact(sqe, fd, addr, addrlen)
        }

        override fun io_uring_prep_close(sqe: Ptr, fd: Int) {
            io_uring_prep_close!!.invokeExact(sqe, fd)
        }

        override fun io_uring_prep_fsync(sqe: Ptr, fd: Int, fsyncFlags: Int) {
            io_uring_prep_fsync!!.invokeExact(sqe, fd, fsyncFlags)
        }

        override fun io_uring_prep_recv(sqe: Ptr, fd: Int, buf: Ptr, len: Int, flags: Int) {
            io_uring_prep_recv!!.invokeExact(sqe, fd, buf, len, flags)
        }

        override fun io_uring_prep_send(sqe: Ptr, fd: Int, buf: Ptr, len: Int, flags: Int) {
            io_uring_prep_send!!.invokeExact(sqe, fd, buf, len, flags)
        }

        override fun io_uring_prep_openat(sqe: Ptr, dfd: Int, path: Ptr, flags: Int, mode: Int) {
            io_uring_prep_openat!!.invokeExact(sqe, dfd, path, flags)
        }

        override fun io_uring_prep_statx(sqe: Ptr, dfd: Int, path: Ptr, flags: Int, mask: Int, statxbuf: Ptr) {
            io_uring_prep_statx!!.invokeExact(sqe, dfd, path, flags)
        }

        override fun io_uring_prep_fallocate(sqe: Ptr, fd: Int, mode: Int, offset: Long, len: Long) {
            io_uring_prep_fallocate!!.invokeExact(sqe, fd, mode, offset, len)
        }

        override fun io_uring_prep_provide_buffers(sqe: Ptr, addr: Ptr, len: Int, nr: Int, bgid: Int, bid: Int) {
            io_uring_prep_provide_buffers!!.invokeExact(sqe, addr, len, nr, bgid, bid)
        }

        override fun io_uring_prep_remove_buffers(sqe: Ptr, nr: Int, bgid: Int) {
            io_uring_prep_remove_buffers!!.invokeExact(sqe, nr, bgid)
        }

        override fun io_uring_prep_shutdown(sqe: Ptr, fd: Int, how: Int) {
            io_uring_prep_shutdown!!.invokeExact(sqe, fd, how)
        }

        override fun io_uring_prep_unlinkat(sqe: Ptr, dfd: Int, path: Ptr, flags: Int) {
            io_uring_prep_unlinkat!!.invokeExact(sqe, dfd, path, flags)
        }

        override fun io_uring_prep_renameat(
            sqe: Ptr,
            olddfd: Int,
            oldpath: Ptr,
            newdfd: Int,
            newpath: Ptr,
            flags: Int
        ) {
            io_uring_prep_renameat!!.invokeExact(sqe, olddfd, oldpath, newdfd, newpath, flags)
        }

        override fun io_uring_prep_socket(sqe: Ptr, domain: Int, type: Int, protocol: Int, flags: Int) {
            io_uring_prep_socket!!.invokeExact(sqe, domain, type, protocol, flags)
        }

        override fun io_uring_prep_msg_ring(sqe: Ptr, fd: Int, len: Int, data: Long, flags: Int) {
            io_uring_prep_msg_ring!!.invokeExact(sqe, fd, len, data, flags)
        }

        override fun io_uring_prep_cmd_sock(
            sqe: Ptr,
            cmdOp: Int,
            fd: Int,
            level: Int,
            optname: Int,
            optval: Ptr,
            optlen: Int
        ) {
            io_uring_prep_cmd_sock!!.invokeExact(sqe, cmdOp, fd, level, optname, optval, optlen)
        }

        override fun io_uring_prep_futex_wait(
            sqe: Ptr,
            futex: Ptr,
            _val: Long,
            mask: Long,
            futexFlags: Int,
            flags: Int
        ) {
            io_uring_prep_futex_wait!!.invokeExact(sqe, futex, _val, mask,futexFlags, flags)
        }

        override fun io_uring_prep_futex_wake(
            sqe: Ptr,
            futex: Ptr,
            _val: Long,
            mask: Long,
            futexFlags: Int,
            flags: Int
        ) {
            io_uring_prep_futex_wake!!.invokeExact(sqe, futex, _val, mask, futexFlags, flags)
        }

        override fun io_uring_prep_futex_waitv(sqe: Ptr, futex: Ptr, nrFutex: Int, flags: Int) {
            io_uring_prep_futex_waitv!!.invokeExact(sqe, futex, nrFutex, flags)
        }

        override fun io_uring_prep_fixed_fd_install(sqe: Ptr, fd: Int, flags: Int) {
            io_uring_prep_fixed_fd_install!!.invokeExact(sqe, fd, flags)
        }

        override fun io_uring_prep_ftruncate(sqe: Ptr, fd: Int, len: Long) {
            io_uring_prep_ftruncate!!.invokeExact(sqe, fd, len)
        }

        override fun io_uring_prep_cmd_discard(sqe: Ptr, fd: Int, offset: Long, nbytes: Long) {
            io_uring_prep_cmd_discard!!.invokeExact(sqe, fd, offset, nbytes)
        }

        override fun io_uring_prep_waitid(sqe: Ptr, idtype: Int, id: Int, infop: Ptr, options: Int, flags: Int) {
            io_uring_prep_waitid!!.invokeExact(sqe, idtype, id, infop, options, flags)
        }

        override fun io_uring_get_sqe(ring: Ptr): Ptr {
            return io_uring_get_sqe!!.invokeExact(ring) as Ptr
        }

        override fun io_uring_prep_cancel(sqe: Ptr, userData: Ptr, flags: Int) {
            TODO("Not yet implemented")
        }
    }
    _instance = result
    isBinded = true
    return result
}

private fun loadUringNativeFunction(
    linker: Linker,
    lookup: SymbolLookup,
    name: String,
    retType: MemoryLayout,
    vararg argsType: MemoryLayout
) : MethodHandle? {
    val ms = lookup.find(name)
    if (ms.isEmpty) return null
    return linker.downcallHandle(
        ms.get(),
        FunctionDescriptor.of(retType, *argsType)
    )
}

private fun loadVoidUringNativeFunction(
    linker: Linker,
    lookup: SymbolLookup,
    name: String,
    vararg argsType: MemoryLayout
) : MethodHandle? {
    val ms = lookup.find(name)
    if (ms.isEmpty) return null
    return linker.downcallHandle(
        ms.get(),
        FunctionDescriptor.ofVoid(*argsType)
    )
}