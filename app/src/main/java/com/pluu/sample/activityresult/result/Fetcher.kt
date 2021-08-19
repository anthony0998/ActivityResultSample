package com.pluu.sample.activityresult.result

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContract
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

inline fun <reified T, reified R, reified V> ActivityResultCaller.createDefaultFetcher(): Fetcher<T, R> =
    Fetcher(
        this,
        createDefaultContract(V::class.java)
    )

class Fetcher<T, R>(activityResultCaller: ActivityResultCaller, contract: ActivityResultContract<in T, out R>) {

    private var continuation: Continuation<R?>? = null

    private val editNameLauncher = activityResultCaller.registerForActivityResult(contract) { result ->
        continuation?.resumeWith(Result.success(result))
    }

    suspend operator fun invoke(name: T) = suspendCoroutine<R?> { continuationIn ->
        this.continuation = continuationIn
        editNameLauncher.launch(name)
    }
}