package ru.axdar.finstart.screens.trade_quotes.adapters

import ru.axdar.finstart.models.QuoteData

sealed class QuoteItem {

    abstract val id: Long

    data class ItemData(val quoteData: QuoteData) : QuoteItem() {
        override val id: Long = quoteData.id
    }

    data class Header(val title: String) : QuoteItem() {
        override val id: Long = Long.MIN_VALUE
    }
}