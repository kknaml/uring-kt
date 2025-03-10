package uringkt.binding

import uringkt.binding.internal.instance

// liburing.h
@Suppress("FunctionName")
public interface ILibUring {
    public fun io_uring_get_probe_ring(ring: Ptr): Ptr
    public fun io_uring_get_probe(): Ptr
    public fun io_uring_free_probe(probe: Ptr)
    public fun io_uring_opcode_supported(probe: Ptr, op: Int): Int
    public fun io_uring_queue_init_mem(entries: Int, ring: Ptr, ioUringParams: Ptr, buf: Ptr, bufSize: Long): Int
    public fun io_uring_queue_init_params(entries: Int, ring: Ptr, params: Ptr): Int
    public fun io_uring_queue_init(entries: Int, ring: Ptr, flags: Int): Int
    public fun io_uring_queue_mmap(fd: Int, params: Ptr, ring: Ptr): Int
    public fun io_uring_ring_dontfork(ring: Ptr): Int
    public fun io_uring_queue_exit(ring: Ptr)
    public fun io_uring_peek_batch_cqe(ring: Ptr, cqes: Ptr, count: Int): Int
    public fun io_uring_wait_cqes(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, sigmask: Ptr): Int
    public fun io_uring_wait_cqes_min_timeout(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, minTsUsec: Int, sigmask: Ptr): Int
    public fun io_uring_wait_cqe_timeout(ring: Ptr, cqePtr: Ptr, ts: Ptr): Int
    public fun io_uring_submit(ring: Ptr): Int
    public fun io_uring_submit_and_wait(ring: Ptr, waitNr: Int): Int
    public fun io_uring_submit_and_wait_timeout(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, sigmask: Ptr): Int
    public fun io_uring_submit_and_wait_min_timeout(ring: Ptr, cqePtr: Ptr, waitNr: Int, ts: Ptr, minWait: Int, sigmask: Ptr): Int
    public fun io_uring_submit_and_wait_reg(ring: Ptr, cqePtr: Ptr, waitNr: Int, regIndex: Int): Int
    public fun io_uring_register_wait_reg(ring: Ptr, reg: Ptr, nr: Int): Int
    public fun io_uring_resize_rings(ring: Ptr, params: Ptr): Int
    public fun io_uring_clone_buffers_offset(dst: Ptr, src: Ptr, dstOff: Int, srcOff: Int, nr: Int, flags: Int): Int
    public fun io_uring_clone_buffers(dst: Ptr, src: Ptr): Int
    public fun io_uring_register_buffers(ring: Ptr, iovecs: Ptr, nrIovecs: Int): Int
    public fun io_uring_register_buffers_tags(ring: Ptr, iovecs: Ptr, tags: Ptr, nr: Int): Int
    public fun io_uring_register_buffers_sparse(ring: Ptr, nr: Int): Int
    public fun io_uring_register_buffers_update_tag(ring: Ptr, off: Int, iovecs: Ptr, tags: Ptr, nr: Int): Int
    public fun io_uring_unregister_buffers(ring: Ptr): Int
    public fun io_uring_register_files(ring: Ptr, files: Ptr, nrFiles: Int): Int
    public fun io_uring_register_files_tags(ring: Ptr, files: Ptr, tags: Ptr, nr: Int): Int
    public fun io_uring_register_files_sparse(ring: Ptr, nr: Int): Int
    public fun io_uring_register_files_update_tag(ring: Ptr, off: Int, files: Ptr, tags: Ptr, nrFiles: Int): Int
    public fun io_uring_unregister_files(ring: Ptr): Int
    public fun io_uring_register_files_update(ring: Ptr, off: Int, files: Ptr, nrFiles: Int): Int
    public fun io_uring_register_eventfd(ring: Ptr, fd: Int): Int
    public fun io_uring_register_eventfd_async(ring: Ptr, fd: Int): Int
    public fun io_uring_unregister_eventfd(ring: Ptr): Int
    public fun io_uring_register_probe(ring: Ptr, probe: Ptr, nr: Int): Int
    public fun io_uring_register_personality(ring: Ptr): Int
    public fun io_uring_unregister_personality(ring: Ptr, id: Int): Int
    public fun io_uring_register_restrictions(ring: Ptr, res: Ptr, nrRes: Int): Int
    public fun io_uring_enable_rings(ring: Ptr): Int
    public fun __io_uring_sqring_wait(ring: Ptr): Int
    public fun io_uring_register_iowq_aff(ring: Ptr, cpusz: Long, mask: Ptr): Int
    public fun io_uring_unregister_iowq_aff(ring: Ptr): Int
    public fun io_uring_register_iowq_max_workers(ring: Ptr, values: Ptr): Int
    public fun io_uring_register_ring_fd(ring: Ptr): Int
    public fun io_uring_unregister_ring_fd(ring: Ptr): Int
    public fun io_uring_close_ring_fd(ring: Ptr): Int
    public fun io_uring_register_buf_ring(ring: Ptr, reg: Ptr, flags: Int): Int
    public fun io_uring_unregister_buf_ring(ring: Ptr, bgid: Int): Int
    public fun io_uring_buf_ring_head(ring: Ptr, bufGroup: Int, head: Ptr): Int
    public fun io_uring_register_sync_cancel(ring: Ptr, reg: Ptr): Int
    public fun io_uring_register_file_alloc_range(ring: Ptr, off: Int, len: Int): Int
    public fun io_uring_register_napi(ring: Ptr, napi: Ptr): Int
    public fun io_uring_unregister_napi(ring: Ptr, napi: Ptr): Int
    public fun io_uring_register_ifq(ring: Ptr, reg: Ptr): Int
    public fun io_uring_register_clock(ring: Ptr, arg: Ptr): Int
    public fun io_uring_get_events(ring: Ptr): Int
    public fun io_uring_submit_and_get_events(ring: Ptr): Int
    public fun io_uring_enter(fd: Int, toSubmit: Int, minComplete: Int, flags: Int, sig: Ptr): Int
    public fun io_uring_enter2(fd: Int, toSubmit: Int, minComplete: Int, flags: Int, sig: Ptr, sz: Long): Int
    public fun io_uring_setup(entries: Int, params: Ptr): Int
    public fun io_uring_register(fd: Int, opcode: Int, arg: Ptr, nrArgs: Int): Int
    public fun io_uring_register_region(ring: Ptr, reg: Ptr): Int
    public fun io_uring_setup_buf_ring(ring: Ptr, nentries: Int, bgid: Int, flags: Int, err: Ptr): Ptr
    public fun io_uring_free_buf_ring(ring: Ptr, br: Ptr, nentries: Int, bgid: Int): Int
    public fun __io_uring_get_cqe(ring: Ptr, cqePtr: Ptr, submit: Int, waitNr: Int, sigmask: Ptr): Int
    public fun io_uring_mlock_size(entries: Int, flags: Int): Long
    public fun io_uring_mlock_size_params(entries: Int, params: Ptr): Long
    public fun io_uring_major_version(): Int
    public fun io_uring_minor_version(): Int
    public fun io_uring_check_version(major: Int, minor: Int): Boolean

    public fun io_uring_wait_cqe_nr(ring: Ptr, cqePtr: Ptr, waitNr: Int): Int
    public fun io_uring_peek_cqe(ring: Ptr, cqePtr: Ptr): Int
    public fun io_uring_wait_cqe(ring: Ptr, cqePtr: Ptr): Int
    public fun io_uring_cq_advance(ring: Ptr, nr: Int)
    public fun io_uring_cqe_seen(ring: Ptr, cqe: Ptr)
    public fun io_uring_sqe_set_data(sqe: Ptr, data: Ptr)
    public fun io_uring_cqe_get_data(cqe: Ptr): Ptr
    public fun io_uring_sqe_set_flags(sqe: Ptr, flags: Int)
    public fun io_uring_sqe_set_buf_group(sqe: Ptr, bgid: Int)
    public fun io_uring_initialize_sqe(sqe: Ptr)
    public fun io_uring_prep_rw(op: Int, sqe: Ptr, fd: Int, addr: Ptr, len: Int, offset: Long)
    public fun io_uring_prep_nop(sqe: Ptr)
    public fun io_uring_prep_readv(sqe: Ptr, fd: Int, iovecs: Ptr, nrVecs: Int, offset: Long)
    public fun io_uring_prep_writev(sqe: Ptr, fd: Int, iovecs: Ptr, nrVecs: Int, offset: Long)
    public fun io_uring_prep_poll_add(sqe: Ptr, fd: Int, pollMask: Int)
    public fun io_uring_prep_poll_remove(sqe: Ptr, userData: Long)
    public fun io_uring_prep_timeout(sqe: Ptr, ts: Ptr, count: Int, flags: Int)
    public fun io_uring_prep_accept(sqe: Ptr, fd: Int, addr: Ptr, addrlen: Ptr, flags: Int)
    public fun io_uring_prep_connect(sqe: Ptr, fd: Int, addr: Ptr, addrlen: Int)
    public fun io_uring_prep_close(sqe: Ptr, fd: Int)
    public fun io_uring_prep_fsync(sqe: Ptr, fd: Int, fsyncFlags: Int)
    public fun io_uring_prep_recv(sqe: Ptr, fd: Int, buf: Ptr, len: Int, flags: Int)
    public fun io_uring_prep_send(sqe: Ptr, fd: Int, buf: Ptr, len: Int, flags: Int)
    public fun io_uring_prep_openat(sqe: Ptr, dfd: Int, path: Ptr, flags: Int, mode: Int)
    public fun io_uring_prep_statx(sqe: Ptr, dfd: Int, path: Ptr, flags: Int, mask: Int, statxbuf: Ptr)
    public fun io_uring_prep_fallocate(sqe: Ptr, fd: Int, mode: Int, offset: Long, len: Long)
    public fun io_uring_prep_provide_buffers(sqe: Ptr, addr: Ptr, len: Int, nr: Int, bgid: Int, bid: Int)
    public fun io_uring_prep_remove_buffers(sqe: Ptr, nr: Int, bgid: Int)
    public fun io_uring_prep_shutdown(sqe: Ptr, fd: Int, how: Int)
    public fun io_uring_prep_unlinkat(sqe: Ptr, dfd: Int, path: Ptr, flags: Int)
    public fun io_uring_prep_renameat(sqe: Ptr, olddfd: Int, oldpath: Ptr, newdfd: Int, newpath: Ptr, flags: Int)
    public fun io_uring_prep_socket(sqe: Ptr, domain: Int, type: Int, protocol: Int, flags: Int)
    public fun io_uring_prep_msg_ring(sqe: Ptr, fd: Int, len: Int, data: Long, flags: Int)
    public fun io_uring_prep_cmd_sock(sqe: Ptr, cmdOp: Int, fd: Int, level: Int, optname: Int, optval: Ptr, optlen: Int)
    public fun io_uring_prep_futex_wait(sqe: Ptr, futex: Ptr, _val: Long, mask: Long, futexFlags: Int, flags: Int)
    public fun io_uring_prep_futex_wake(sqe: Ptr, futex: Ptr, _val: Long, mask: Long, futexFlags: Int, flags: Int)
    public fun io_uring_prep_futex_waitv(sqe: Ptr, futex: Ptr, nrFutex: Int, flags: Int)
    public fun io_uring_prep_fixed_fd_install(sqe: Ptr, fd: Int, flags: Int)
    public fun io_uring_prep_ftruncate(sqe: Ptr, fd: Int, len: Long)
    public fun io_uring_prep_cmd_discard(sqe: Ptr, fd: Int, offset: Long, nbytes: Long)
    public fun io_uring_prep_waitid(sqe: Ptr, idtype: Int, id: Int, infop: Ptr, options: Int, flags: Int)

    public fun io_uring_get_sqe(ring: Ptr): Ptr

    public fun loadTime(): Long

    public companion object : ILibUring by instance
}
