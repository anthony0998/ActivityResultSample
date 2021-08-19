package com.pluu.sample.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.pluu.sample.activityresult.ResultSecondActivity.Companion.KEY_NAME
import com.pluu.sample.activityresult.ResultSecondActivity.Companion.KEY_PERSON
import kotlinx.coroutines.Dispatchers
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

    class Person() : Parcelable {
        constructor(parcel: Parcel) : this() {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {

        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Person> {
            override fun createFromParcel(parcel: Parcel): Person {
                return Person(parcel)
            }

            override fun newArray(size: Int): Array<Person?> {
                return arrayOfNulls(size)
            }
        }

    }
    private val fetch = Fetcher(this, contract)
    private val fetch2 = Fetcher(this, DefaultContract<String, Person>(targetActivity = ResultSecondActivity.javaClass))

    private val fetch3 = Fetcher(this, BundleContract(targetActivity = ResultSecondActivity.javaClass))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView {
            add(::LinearLayout) {

                button("Show second Activity (Custom Result)") {
                    GlobalScope.launch(Dispatchers.Main) {
                        val inputBundle = Bundle()
                        inputBundle.putString(KEY_NAME, "abc")
                        inputBundle.putParcelable(KEY_PERSON, Person())
                        val editedName = fetch3(inputBundle)
                        val name = editedName.getString(KEY_NAME)
                    }
                }
            }
        }
    }
}
