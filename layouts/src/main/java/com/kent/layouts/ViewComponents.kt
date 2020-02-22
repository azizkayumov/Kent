package com.kent.layouts

import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ProgressBar
import android.widget.RadioGroup
import androidx.appcompat.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by abduaziz on 2020-02-22 at 01:13.
 *
 * Views are converted to DSL format, example:
 * context.verticalLayout {
 *      layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
 *
 *      textView {
 *          text = "Hello, World!"
 *      }
 * }
 *
 * Converted functions are in the order of material design components
 * See https://material.io/components/
 */

/*
* Buttons
* - Regular button
* - Floating action buttons
* */
fun ViewGroup.button(init: AppCompatButton.() -> Unit = {}): AppCompatButton {
    return AppCompatButton(context).apply {
        init()
        addView(this)
    }
}

fun ViewGroup.floatingActionButton(init: FloatingActionButton.() -> Unit = {}): FloatingActionButton {
    return FloatingActionButton(context).apply {
        init()
        addView(this)
    }
}

/*
* Cards
* */
fun ViewGroup.cardView(init: CardView.() -> Unit = {}): CardView {
    return CardView(context).apply {
        init()
        addView(this)
    }
}


/*
* Chips
* Chips group
* */
fun ViewGroup.chip(init: Chip.() -> Unit = {}): Chip {
    return Chip(context).apply {
        init()
        addView(this)
    }
}

fun ViewGroup.chipGroup(init: ChipGroup.() -> Unit = {}): ChipGroup {
    return ChipGroup(context).apply {
        init()
        addView(this)
    }
}

/*
* Lists:
* - RecyclerView
* - ViewPager2 (based on RecyclerView)
* */

inline fun ViewGroup.recyclerView(init: RecyclerView.() -> Unit = {}): RecyclerView {
    val r = RecyclerView(context).apply(init)
    addView(r)
    return r
}

inline fun ViewGroup.viewPager(init: ViewPager2.() -> Unit = {}): ViewPager2 {
    val v = ViewPager2(context).apply(init)
    addView(v)
    return v
}

/*
* Navigation drawers
* - Modal drawer (standard with gravity = left)
* - Bottom drawer (used with bottom app bar)
* */
inline fun ViewGroup.navigationView(init: NavigationView.() -> Unit = {}): NavigationView {
    val n = NavigationView(context).apply(init)
    addView(n)
    return n
}

inline fun ViewGroup.bottomNavigation(init: BottomNavigationView.() -> Unit = {}): BottomNavigationView {
    val b = BottomNavigationView(context).apply(init)
    addView(b)
    return b
}

/*
* Date picker
*/
inline fun ViewGroup.datePicker(init: DatePicker.() -> Unit = {}): DatePicker {
    val d = DatePicker(context).apply(init)
    addView(d)
    return d
}

/*
* Progress bars:
* - Linear progress
* - Circular progress
* Both of them are just styled versions of regular ProgressBar
* */
inline fun ViewGroup.linearProgress(init: ProgressBar.() -> Unit = {}): ProgressBar {
    val p = ProgressBar(context, null, android.R.style.Widget_DeviceDefault_Light_ProgressBar_Horizontal).apply(init)
    addView(p)
    return p
}

inline fun ViewGroup.circularProgress(init: ProgressBar.() -> Unit = {}): ProgressBar {
    val p = ProgressBar(context).apply(init)
    addView(p)
    return p
}

/*
* Selection controls
* - Checkboxes
* - Switches
* - Radio buttons
* */
inline fun ViewGroup.checkBox(init: AppCompatCheckBox.() -> Unit = {}): AppCompatCheckBox {
    val a = AppCompatCheckBox(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.switch(init: SwitchMaterial.() -> Unit = {}): SwitchMaterial {
    val s = SwitchMaterial(context).apply(init)
    addView(s)
    return s
}

inline fun ViewGroup.radioButton(init: AppCompatRadioButton.() -> Unit = {}): AppCompatRadioButton {
    val a = AppCompatRadioButton(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.radioGroup(init: RadioGroup.() -> Unit = {}): RadioGroup {
    val r = RadioGroup(context).apply(init)
    addView(r)
    return r
}

/*
* Sliders - seek bars
* */
inline fun ViewGroup.seekBar(init: AppCompatSeekBar.() -> Unit = {}): AppCompatSeekBar {
    val a = AppCompatSeekBar(context).apply(init)
    addView(a)
    return a
}

/*
* Text, text input and text fields
* - TextView
* - EditText
* - TextFields
* */
inline fun ViewGroup.textView(init: AppCompatTextView.() -> Unit = {}): AppCompatTextView {
    val a = AppCompatTextView(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.editText(init: AppCompatEditText.() -> Unit = {}): AppCompatEditText {
    val a = AppCompatEditText(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.textField(init: TextInputEditText.() -> Unit = {}): TextInputEditText {
    val r = TextInputEditText(context).apply(init)
    addView(r)
    return r
}

/*
* AppCompatImageView
* */
inline fun ViewGroup.imageView(init: AppCompatImageView.() -> Unit = {}): AppCompatImageView {
    val a = AppCompatImageView(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.ratingBar(init: AppCompatRatingBar.() -> Unit = {}): AppCompatRatingBar {
    val r = AppCompatRatingBar(context)
    addView(r)
    return r
}
