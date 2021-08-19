package com.pluu.sample.activityresult.result

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class Fetcher<T, R>(activity: ComponentActivity, contract: ActivityResultContract<in T, out R>) {

    private var continuation: Continuation<R?>? = null

    private val editNameLauncher = activity.registerForActivityResult(contract) { result ->
        continuation?.resumeWith(Result.success(result))
    }

    suspend operator fun invoke(name: T) = suspendCoroutine<R?> { continuationIn ->
        this.continuation = continuationIn
        editNameLauncher.launch(name)
    }

}