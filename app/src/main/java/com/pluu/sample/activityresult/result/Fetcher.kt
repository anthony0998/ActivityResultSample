package com.pluu.sample.activityresult.result

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class Fetcher<I, O>(activity: ComponentActivity, contract: ActivityResultContract<in I, out O>) {

    private var continuation: Continuation<O?>? = null

    private val editNameLauncher = activity.registerForActivityResult(contract) { result ->
        continuation?.resumeWith(Result.success(result))
    }

    suspend operator fun invoke(name: I) = suspendCoroutine<O?> { continuationIn ->
        this.continuation = continuationIn
        editNameLauncher.launch(name)
    }

}