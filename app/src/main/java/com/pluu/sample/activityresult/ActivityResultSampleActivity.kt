package com.pluu.sample.activityresult

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.pluu.sample.activityresult.bean.Person
import com.pluu.sample.activityresult.result.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityResultSampleActivity : AppCompatActivity() {

    private val fetch = createDefaultFetcher<String, Person, ResultSecondActivity>()

    private val bundleFetcher = Fetcher(
        this,
        BundleContract(targetActivity = ResultSecondActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView {
            add(::LinearLayout) {

                button("Show second Activity (Custom Result)") {
                    GlobalScope.launch(Dispatchers.Main) {
                        // default fetcher
                        fetch("hello world!")?.let {
                            toast(Gson().toJson(it))
                        }
                        //bundle fetcher
//                        val inputBundle = Bundle()
//                        inputBundle.putString(KEY_NAME, "abc")
//                        inputBundle.putParcelable(
//                            KEY_PERSON,
//                            Person("张三", 15)
//                        )
//                        bundleFetcher(inputBundle)?.let {
//                            val person: Person? = it.getParcelable(KEY_PERSON)
//                            toast(Gson().toJson(person))
//                        }
                    }
                }
            }
        }
    }
}
