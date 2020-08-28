package ru.axdar.finstart.screens.trade_quotes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.axdar.finstart.models.QuoteCurrency
import ru.axdar.finstart.models.QuoteIndex
import ru.axdar.finstart.models.QuoteTicker

class TradeQuotesViewModel : ViewModel() {
    //Fake data
    private val listIndexes = listOf(
        QuoteIndex(1, "IMOEX", "11:48", 3054.56f, 0.1999f),
        QuoteIndex(1, "RTSI", "11:48", 1316.32f, 0.3799f),
        QuoteIndex(1, "S&P500", "19/08", 3361.44f, -0.4299f),
        QuoteIndex(1, "Nasdaq", "19/08", 11146.46f, -0.5799f)
    )
    private val listCurrency = listOf(
        QuoteCurrency(1, "eur/usd", "12:44", 1.1623f, 0.0029f),
        QuoteCurrency(1, "usd/rub", "12:05", 73.32f, 0.0799f),
        QuoteCurrency(1, "eur/rub", "01/08", 87.44f, -0.1299f)
    )
    private val listTickers = listOf(
        QuoteTicker(1, "NLMK", "15:44", 161.1623f, 0.0029f),
        QuoteTicker(1, "VTBR", "15:38", 0.003249f, 0.0799f),
        QuoteTicker(1, "LSRG", "15:38", 7.1422f, -0.1299f),
        QuoteTicker(1, "CSCO", "20/08", 42.33f, -0.1299f)
    )

    private val quotesIndexes = MutableLiveData<List<QuoteIndex>>()
    private val quotesCurrencies = MutableLiveData<List<QuoteCurrency>>()
    private val quotesTickers = MutableLiveData<List<QuoteTicker>>()

    init {
        initializeQuotes()
    }

    fun getQuotesIndexes(): LiveData<List<QuoteIndex>> = quotesIndexes

    fun getQuotesCurrencies(): LiveData<List<QuoteCurrency>> = quotesCurrencies

    fun getQuoteTickers(): LiveData<List<QuoteTicker>> = quotesTickers

    private fun initializeQuotes() {
        quotesIndexes.value = listIndexes
        quotesCurrencies.value = listCurrency
        quotesTickers.value = listTickers
    }

}