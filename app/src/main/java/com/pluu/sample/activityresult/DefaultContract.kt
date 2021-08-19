package com.pluu.sample.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

open class DefaultContract<T, R>(private val key:String = DEFAULT_KEY, private val targetActivity: Class<Any>) : ActivityResultContract<T, R>() {
    companion object {
        const val DEFAULT_KEY = "DefaultContract"
    }
    override fun createIntent(
        context: Context, input: T
    ): Intent {
        return Intent(context, targetActivity).apply {
            putExtra(key, input.toString())
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): R? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            val resultJson = intent.getStringExtra(key)
            toJson(resultJson)
        } else {
            null
        }
    }
}