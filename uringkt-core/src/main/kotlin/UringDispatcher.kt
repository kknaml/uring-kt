package kkuring


import kkuring.runtime.KKUringThreadPool
import kkuring.runtime.localContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

public class UringDispatcher : CoroutineDispatcher() {

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
}