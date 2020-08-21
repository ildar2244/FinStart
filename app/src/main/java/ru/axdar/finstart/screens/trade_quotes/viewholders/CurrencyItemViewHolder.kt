package ru.axdar.finstart.screens.trade_quotes.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quote_index.view.*
import ru.axdar.finstart.R
import ru.axdar.finstart.models.QuoteCurrency

class CurrencyItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): CurrencyItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_quote_index, parent, false)
            return CurrencyItemViewHolder(view)
        }
    }

    fun bind(quote: QuoteCurrency) {
        with(itemView) {
            tv_quote_index_name.text = quote.name
            tv_quote_index_time_update.text = quote.datetime
            tv_quote_index_value.text = quote.value.toString()
            tv_quote_index_change.text = quote.change.toString()

            /*if (quote.change > 0) {
                tv_quote_index_change.setTextColor(Color.GREEN)
            } else {
                tv_quote_index_change.setTextColor(Color.RED)
            }*/
        }
    }
}