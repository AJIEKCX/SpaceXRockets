package ru.alexpanov.core.extension

import kotlin.coroutines.cancellation.CancellationException

inline fun <R> runCatchingCancellable(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        if (e is CancellationException) {
            throw e
        }
        Result.failure(e)
    }
}