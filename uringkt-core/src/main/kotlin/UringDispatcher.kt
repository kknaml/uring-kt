package kkuring


import kkuring.runtime.KKUringThreadPool
import kkuring.runtime.localContext
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@OptIn(InternalCoroutinesApi::class)
public class UringDispatcher : CoroutineDispatcher(), Delay {

    private val threadPool = KKUringThreadPool(1)

    init {
        threadPool.start()
    }

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return localContext.get() == null
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        threadPool.dispatch(block)
    }

    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        threadPool.addDelay(timeMillis, continuation)
    }
}