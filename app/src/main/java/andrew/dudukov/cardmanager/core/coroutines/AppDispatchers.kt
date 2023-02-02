package andrew.dudukov.cardmanager.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

class AppDispatchersImpl : AppDispatchers {
    override val default = Dispatchers.Default
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val mainImmediate = Dispatchers.Main.immediate
    override val unconfined = Dispatchers.Unconfined
}
