package com.pluu.sample.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class BundleContract(private val key:String = DEFAULT_KEY, private val targetActivity: Class<Any>): DefaultContract<Bundle, Bundle>(key, targetActivity) {

    override fun createIntent(
        context: Context, input: Bundle
    ): Intent {
        return Intent(context, targetActivity).apply {
            putExtras(input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bundle? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            intent.getBundleExtra(key)
        } else {
            null
        }
    }
}