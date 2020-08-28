package ru.axdar.finstart.screens.trade_quotes.adapters

import androidx.recyclerview.widget.DiffUtil

class QuoteDiffCallback : DiffUtil.ItemCallback<QuoteItem>() {
    override fun areItemsTheSame(oldItem: QuoteItem, newItem: QuoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: QuoteItem, newItem: QuoteItem): Boolean {
        return oldItem == newItem
    }
}