package ru.axdar.finstart.models

//Котировки акций
data class QuoteShares(
    override var id: Long,
    override val name: String,
    override var datetime: String,
    override var value: Float,
    override var change: Float
) : QuoteData()