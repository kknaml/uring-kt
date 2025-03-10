package kkuring


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

public class UringDispatcher : CoroutineDispatcher() {


    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        // TODO
        return false
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        // TODO
    }
}