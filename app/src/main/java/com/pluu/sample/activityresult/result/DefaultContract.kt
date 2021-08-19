package com.pluu.sample.activityresult.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class DefaultContract<I, O>(private val targetActivity: Class<*>) :
    ActivityResultContract<I, O>() {

    companion object {
        const val KEY = "DEFAULT_CONTRACT"
    }

    override fun createIntent(
        context: Context, input: I
    ): Intent {
        return Intent(context, targetActivity).apply {
            putExtra(KEY, Gson().toJson(input))
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): O? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            return Gson().fromJson(intent.getStringExtra(KEY), object : TypeToken<O>() {}.type)
        } else {
            null
        }
    }

    inline fun <reified T> type() = object : TypeToken<T>() {}.type

}