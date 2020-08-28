package ru.axdar.finstart.models

//временно общий для всего
data class QuoteTicker(
    override var id: Long,
    override val name: String,
    override var datetime: String,
    override var value: Float,
    override var change: Float
) : QuoteData()