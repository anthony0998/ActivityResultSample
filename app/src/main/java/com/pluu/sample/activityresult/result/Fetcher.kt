package com.pluu.sample.activityresult.result

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContract
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

/**
 * Create a default fetcher
 *
 * @param T Class of input param
 * @param R Class of result object
 * @param V Class of target Activity which will redirect to
 * @param key key of the param we pass out and receive, default value is DefaultContract.DEFAULT_KEY
 */
inline fun <reified T, reified R, reified V> ActivityResultCaller.createDefaultFetcher(
    key: String = DefaultContract.DEFAULT_KEY
): Fetcher<T, R> =
    Fetcher(
        this,
        createDefaultContract(V::class.java, key)
    )

class Fetcher<T, R>(
    activityResultCaller: ActivityResultCaller,
    contract: ActivityResultContract<in T, out R>
) {

    private var continuation: Continuation<R?>? = null

    private val editNameLauncher =
        activityResultCaller.registerForActivityResult(contract) { result ->
            continuation?.resumeWith(Result.success(result))
        }

    suspend operator fun invoke(name: T) = suspendCoroutine<R?> { continuationIn ->
        this.continuation = continuationIn
        editNameLauncher.launch(name)
    }
}