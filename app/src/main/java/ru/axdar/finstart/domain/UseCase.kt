package ru.axdar.finstart.domain

import kotlinx.coroutines.*
import ru.axdar.finstart.models.Response
import kotlin.coroutines.CoroutineContext

abstract class UseCase<out Type, in Params> {

    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main
    private var parentJob: Job = Job()

    abstract suspend fun run(params: Params): Response<Type>

    operator fun invoke(params: Params, onResult: (Response<Type>) -> Unit = {}) {
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            val result = withContext(backgroundContext) {
                run(params)
            }
            onResult(result)
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}