package com.kent.layouts

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.TextView

/**
 * Created by abduaziz on 2020-02-22 at 05:54.
 */

const val NO_GETTER = "No getter"

fun noGetter() = 0

var View.backgroundDrawable: Drawable?
    inline get() = background
    set(value) = setBackgroundDrawable(value)

var View.backgroundColorResource: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    set(colorId) = setBackgroundColor(context.resources.getColor(colorId))

var View.leftPadding: Int
    inline get() = paddingLeft
    set(value) = setPadding(value, paddingTop, paddingRight, paddingBottom)

var View.topPadding: Int
    inline get() = paddingTop
    set(value) = setPadding(paddingLeft, value, paddingRight, paddingBottom)

var View.rightPadding: Int
    inline get() = paddingRight
    set(value) = setPadding(paddingLeft, paddingTop, value, paddingBottom)

var View.bottomPadding: Int
    inline get() = paddingBottom
    set(value) = setPadding(paddingLeft, paddingTop, paddingRight, value)

var View.horizontalPadding: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    set(value) = setPadding(value, paddingTop, value, paddingBottom)

var View.verticalPadding: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    set(value) = setPadding(paddingLeft, value, paddingRight, value)

var View.padding: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    inline set(value) = setPadding(value, value, value, value)

fun View.setRippleEffect() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        setBackgroundResource(outValue.resourceId)
        isClickable = true
    }
}

fun View.setRippleEffectBorderless() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)
        setBackgroundResource(outValue.resourceId)
        isClickable = true
    }
}

var TextView.singleLine: Boolean
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = false
    set(v) = setSingleLine(v)

var TextView.lines: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = 0
    set(v) = setLines(v)

var TextView.allCaps: Boolean
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = false
    inline set(value) = setAllCaps(value)

var TextView.ems: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    inline set(value) = setEms(value)

inline var TextView.isSelectable: Boolean
    get() = isTextSelectable
    set(value) = setTextIsSelectable(value)

var TextView.textAppearance: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    set(value) = if (Build.VERSION.SDK_INT >= 23) setTextAppearance(value) else setTextAppearance(context, value)

var TextView.textSizeDimen: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    set(value) = setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(value))

var TextView.textColorResource: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR) get() = noGetter()
    set(colorId) = setTextColor(context.resources.getColor(colorId))

fun TextView.setStyle(context: Context, resId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        this.setTextAppearance(context, resId)
    } else {
        this.setTextAppearance(resId)
    }
}

// if text does not fit, it shortens the text and addCall "..." at the end
fun TextView.ellipsize(mLines: Int = 4) {
    maxLines = mLines
    ellipsize = TextUtils.TruncateAt.END
}

fun TextView.makeBold() {
    setTypeface(typeface, Typeface.BOLD)
}

fun TextView.makeItalic() {
    setTypeface(typeface, Typeface.ITALIC)
}

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.formatPhone() {
    val t = text
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        text = "+${PhoneNumberUtils.formatNumber(t.toString(), "UZ")}"
    } else {
        text = "+${PhoneNumberUtils.formatNumber(t.toString())}"
    }
    if (text.equals("+null")) text = t
}
