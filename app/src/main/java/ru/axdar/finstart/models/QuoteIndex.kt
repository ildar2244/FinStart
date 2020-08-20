package ru.axdar.finstart.models

data class QuoteIndex(
    var indexId: Long = 0L,
    val name: String,
    var datetime: String,
    var value: Float,
    var change: Float
)