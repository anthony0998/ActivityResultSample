package com.pluu.sample.activityresult

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.pluu.sample.activityresult.ResultSecondActivity.Companion.KEY_NAME
import com.pluu.sample.activityresult.ResultSecondActivity.Companion.KEY_PERSON
import com.pluu.sample.activityresult.bean.Person
import com.pluu.sample.activityresult.result.createBundleFetcher
import com.pluu.sample.activityresult.result.createDefaultFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityResultSampleActivity : AppCompatActivity() {

    private val default = createDefaultFetcher<String, List<Person>, ResultSecondActivity>()

    private val bundle = createBundleFetcher<ResultSecondActivity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView {
            add(::LinearLayout) {

                button("Show second Activity (Custom Result)") {
                    GlobalScope.launch(Dispatchers.Main) {
                        // 默认创建方式看这里 ：default fetcher sample
//                        default("hello world!")?.let {
//                            toast(Gson().toJson(it))
//                        }

                        // Bundle的创建方式看这里 ： bundle fetcher sample
                        val inputBundle = Bundle()
                        inputBundle.putString(KEY_NAME, "abc")
                        inputBundle.putParcelable(
                            KEY_PERSON,
                            Person("张三", 15)
                        )
                        bundle(inputBundle)?.let {
                            val person: Person? = it.getParcelable(KEY_PERSON)
                            toast(Gson().toJson(person))
                        }
                    }
                }
            }
        }
    }
}
