package com.pluu.sample.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityResultSampleActivity : AppCompatActivity() {

    private val contract = object : ActivityResultContract<String?, String?>() {
        override fun createIntent(
            context: Context, input: String?
        ): Intent {
            return Intent(context, ResultSecondActivity::class.java).apply {
                putExtra(Fetcher.KEY, input)
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String? {
            return if (resultCode == Activity.RESULT_OK && intent != null) {
                intent.getStringExtra(Fetcher.KEY)
            } else {
                null
            }
        }
    }

    private val fetch = Fetcher(this, contract)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView {
            add(::LinearLayout) {

                button("Show second Activity (Custom Result)") {
                    setOnClickListener {
                        GlobalScope.launch {
                            val editedName = fetch("Andy")
                            editedName?.let {
                                toast(editedName)
                            }
                        }
                    }
                }
            }
        }
    }
}
