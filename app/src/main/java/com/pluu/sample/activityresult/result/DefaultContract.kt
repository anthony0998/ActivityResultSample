package com.pluu.sample.activityresult.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.gson.Gson
import com.pluu.sample.activityresult.result.DefaultContract.Companion.DEFAULT_KEY

inline fun <reified T, reified R> createDefaultContract(
    targetActivity: Class<*>,
    key: String = DEFAULT_KEY
): DefaultContract<T, R> =
    DefaultContract(targetActivity, R::class.java, key)

open class DefaultContract<T, R>(
    private val targetActivity: Class<*>,
    private val resultClass: Class<R>,
    private val key: String = DEFAULT_KEY
) :
    ActivityResultContract<T, R>() {

    companion object {
        const val DEFAULT_KEY = "DEFAULT_KEY"
    }

    override fun createIntent(
        context: Context, input: T
    ): Intent {
        return Intent(context, targetActivity).apply {
            putExtra(key, Gson().toJson(input))
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): R? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            return Gson().fromJson(intent.getStringExtra(key), resultClass)
        } else {
            null
        }
    }

}