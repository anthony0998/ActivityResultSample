package com.pluu.sample.activityresult.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.gson.Gson

open class DefaultContract<T, R>(
    private val targetActivity: Class<*>,
    private val resultClass: Class<R>
) :
    ActivityResultContract<T, R>() {

    companion object {
        const val KEY = "DEFAULT_CONTRACT"
    }

    override fun createIntent(
        context: Context, input: T
    ): Intent {
        return Intent(context, targetActivity).apply {
            putExtra(KEY, Gson().toJson(input))
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): R? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            return Gson().fromJson(intent.getStringExtra(KEY), resultClass)
        } else {
            null
        }
    }

}