package com.pluu.sample.activityresult.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract

class BundleContract(private val targetActivity: Class<*>) :
    ActivityResultContract<Bundle, Bundle>() {

    override fun createIntent(
        context: Context, input: Bundle
    ): Intent {
        return Intent(context, targetActivity).apply {
            putExtras(input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bundle? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            intent.extras
        } else {
            null
        }
    }
}