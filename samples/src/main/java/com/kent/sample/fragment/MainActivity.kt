package com.kent.sample.fragment

import android.os.Bundle
import com.kent.fragment.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(MainFragment())
    }
}
