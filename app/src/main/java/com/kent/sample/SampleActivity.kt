package com.kent.sample

import android.os.Bundle
import com.kent.layouts.BaseActivity

class SampleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(MainFragment())
    }
}
