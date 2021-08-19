package com.pluu.sample.activityresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.pluu.sample.activityresult.bean.Person
import com.pluu.sample.activityresult.result.DefaultContract

class ResultSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView {
            add(::LinearLayout) {
                orientation = LinearLayout.VERTICAL
                button("Call Finish ~ Result Ok") {
                    val result = Intent().apply {
                        putExtra(DefaultContract.DEFAULT_KEY, Gson().toJson(Person("default-test", 15)))
//                        val bundle = Bundle()
//                        bundle.putParcelable(KEY_PERSON, Person("bundle-test", 25))
//                        putExtra(BundleContract.KEY, bundle)
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