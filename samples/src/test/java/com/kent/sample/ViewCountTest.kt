package com.kent.sample

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.test.core.app.ApplicationProvider
import com.kent.layouts.matchParent
import com.kent.layouts.textView
import com.kent.layouts.viewgroup.verticalLayout
import com.kent.layouts.wrapContent
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.random.Random


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ViewCountTest {

    @Test
    fun addView_viewCount() {
        val viewCount = Random.nextInt(0, 10000)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val r = context.verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent,matchParent)

            for (i in 0 until viewCount) {
                textView {
                    layoutParams = LinearLayout.LayoutParams(
                        wrapContent,
                        wrapContent
                    )
                    text = "Text $i"
                }
            }
        }
        assertEquals(viewCount, r.childCount)
    }
}