package ru.axdar.finstart.models

abstract class QuoteData {
    abstract var id: Long
    abstract val name: String
    abstract var datetime: String
    abstract var value: Float
    abstract var change: Float
}