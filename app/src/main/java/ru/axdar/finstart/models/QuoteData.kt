package ru.axdar.finstart.models

//акции - shares
//фьючерсы - futures
//товары - commodities (brent, gold итп)
//ETFs
//crypto currencies

abstract class QuoteData {
    abstract var id: Long
    abstract val name: String
    abstract var datetime: String
    abstract var value: Float
    abstract var change: Float
}