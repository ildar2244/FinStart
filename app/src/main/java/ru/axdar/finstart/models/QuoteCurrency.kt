package ru.axdar.finstart.models

data class QuoteCurrency(
    override var id: Long = 0L,
    override val name: String,
    override var datetime: String,
    override var value: Float,
    override var change: Float,
    var source: String = ""
) : QuoteData()