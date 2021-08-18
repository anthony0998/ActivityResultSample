package com.pluu.sample.activityresult

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class Fetcher(activity: ComponentActivity, contract: ActivityResultContract<String?, String?>) {
    companion object {
        const val KEY = "edit_name"
    }
    private var continuation: Continuation<String?>? = null


    private val editNameLauncher = activity.registerForActivityResult(contract) { result ->
            continuation?.resumeWith(Result.success(result))
        }

    suspend operator fun invoke(name: String?) = suspendCoroutine<String?> { continuationIn ->
        this.continuation = continuationIn
        editNameLauncher.launch(name)
    }
}