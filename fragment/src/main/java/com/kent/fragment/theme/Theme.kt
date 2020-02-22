package com.kent.fragment.theme

import com.kent.fragment.R

/**
 * Created by abduaziz on 2020-02-23 at 04:47.
 */

class Theme(
    val id: Int = THEME_CLASSIC,

    var colorPrimary: Int = R.color.colorPrimary,
    var colorPrimaryDark: Int = R.color.colorPrimaryDark,
    var colorAccent: Int = R.color.colorAccent,

    var colorIconActive: Int = R.color.colorIconActive,
    var colorIconInactive: Int = R.color.colorIconActive,

    var colorPrimaryText: Int = R.color.colorPrimaryText,
    var colorSecondaryText: Int = R.color.colorSecondaryText,

    var colorDivider: Int = R.color.colorDivider,
    var colorDividerLight: Int = R.color.colorDividerLight,

    var white: Int = R.color.white,
    var black: Int = R.color.black
) {
    companion object {
        val THEME_CLASSIC = 0
        val THEME_NIGHT = 1

        fun getTheme(id: Int): Theme {
            return when (id) {
                THEME_NIGHT -> night()
                else -> classic()
            }
        }

        fun classic() = Theme()

        fun night() = Theme(THEME_NIGHT).apply {
            colorPrimary = R.color.colorPrimary_NIGHT
            colorPrimaryDark = R.color.colorPrimaryDark_NIGHT
            colorAccent = R.color.colorAccent_NIGHT

            colorIconActive = R.color.colorIconActive_NIGHT
            colorIconInactive = R.color.colorIconInactive_NIGHT

            colorPrimaryText = R.color.colorPrimaryText_NIGHT
            colorSecondaryText = R.color.colorSecondaryText_NIGHT

            colorDivider = R.color.colorDivider_NIGHT
            colorDividerLight = R.color.colorDividerLight_NIGHT

            white = R.color.white_NIGHT
            black = R.color.black_NIGHT
        }
    }
}