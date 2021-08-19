package com.pluu.sample.activityresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pluu.sample.activityresult.Fetcher.Companion.KEY

class ResultSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView {
            add(::LinearLayout) {
                orientation = LinearLayout.VERTICAL
                button("Call Finish ~ Result Ok") {
                    val result = Intent().apply {
//                        putExtras(
//                            bundleOf(
//                                key_string_case to "ABCD",
//                                key_int_case to 1
//                            )
//                        )
                        putExtra(PEOPLE_NAME, "Quncy")
                    }
                    setResult(Activity.RESULT_OK, result)
                    finish()
                }
            }
        }
    }

    companion object {
        const val PEOPLE_NAME = "PEOPLE_NAME"
        const val KEY_NAME = "key_name"
        const val KEY_PERSON = "key_person"
    }
}