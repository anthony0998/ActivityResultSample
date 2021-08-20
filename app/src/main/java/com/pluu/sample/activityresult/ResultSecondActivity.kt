package com.pluu.sample.activityresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.pluu.sample.activityresult.bean.Person

class ResultSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView {
            add(::LinearLayout) {
                orientation = LinearLayout.VERTICAL
                button("Call Finish ~ Result Ok") {
                    val result = Intent().apply {

                        // 默认创建方式看这里 ：default fetcher sample
//                        val list = mutableListOf(Person("test1", 15), Person("test2", 25))
//                        putExtra(DefaultContract.DEFAULT_KEY, Gson().toJson(list))

                        // Bundle的创建方式看这里 ： bundle fetcher sample
                        val bundle = Bundle()
                        bundle.putParcelable(KEY_PERSON, Person("bundle-test", 25))
                        putExtras(bundle)
                    }
                    setResult(Activity.RESULT_OK, result)
                    finish()
                }
            }
        }
        intent.extras?.getParcelable<Person>(KEY_PERSON)?.let {
            toast(Gson().toJson(it))
        }
    }

    companion object {
        const val PEOPLE_NAME = "PEOPLE_NAME"
        const val KEY_NAME = "key_name"
        const val KEY_PERSON = "key_person"
    }
}