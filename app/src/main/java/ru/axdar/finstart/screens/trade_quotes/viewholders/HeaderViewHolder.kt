package ru.axdar.finstart.screens.trade_quotes.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.header_quote_base.view.*
import ru.axdar.finstart.R

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.header_quote_base, parent, false)
            return HeaderViewHolder(view)
        }
    }
    fun bind(title: String) {
        itemView.tv_header_title.text = title
    }
}