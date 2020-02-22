package com.kent.layouts.menu

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kent.layouts.*

/**
 * Created by abduaziz on 2020-02-22 at 04:29.
 */

internal class MenuAdapter(var menu: Menu) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    val TV_MENU_ID = 1995

    var onMenuClickListener: OnMenuClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val frame = FrameLayout(parent.context)
        frame.layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)

        val tv = TextView(parent.context)
        tv.id = TV_MENU_ID
        tv.layoutParams = FrameLayout.LayoutParams(matchParent, tv.dip(48))
        tv.setPadding(tv.dip(16), 0, tv.dip(16), 0)
        tv.gravity = Gravity.CENTER_VERTICAL
        tv.setStyle(parent.context, android.R.style.TextAppearance_DeviceDefault_Widget_TextView_PopupMenu)
        tv.textColorResource = R.color.colorPrimaryText
        tv.ellipsize

        frame.addView(tv)
        return MenuViewHolder(frame)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menu.getMenu(position)
        menuItem?.let {
            holder.tv.text = it.text
        }
    }

    override fun getItemCount(): Int {
        return menu.size()
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(TV_MENU_ID)

        init {
            itemView.setOnClickListener {
                menu.getMenu(adapterPosition)?.let {
                    onMenuClickListener?.onMenuClick(it)
                }
            }
            itemView.setRippleEffect()
        }

    }
}