package ru.axdar.finstart.screens.trade_quotes

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.axdar.finstart.data.AppDatabase
import ru.axdar.finstart.data.trade_quotes.ShareQuoteDao
import ru.axdar.finstart.data.trade_quotes.ShareQuoteFromMoex
import ru.axdar.finstart.data.trade_quotes.ShareQuoteRepository
import ru.axdar.finstart.models.QuoteCurrency
import ru.axdar.finstart.models.QuoteIndex
import ru.axdar.finstart.models.QuoteTicker

class TradeQuotesViewModel(application: Application) : AndroidViewModel(application) {

    private val sharesQuotes: LiveData<List<QuoteTicker>>

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
        //initializeQuotes()
        val shareDao = AppDatabase.getDatabase(application, viewModelScope).shareQuoteDao()
        Log.d("9999_flow", "VM:$shareDao")
        val repository = ShareQuoteRepository(shareDao)
        //sharesQuotes = repository.favoriteSharesFlow
        /*sharesQuotes = repository.favoriteSharesFlow.map {
            val ml = mutableListOf<QuoteTicker>()
            it.forEach { moex -> ml.add(
                QuoteTicker(88, moex.ticker, "date", moex.lastTransactionPrice, 0.01f)
            ) }
            ml.toList()
        }.asLiveData(Dispatchers.Main + viewModelScope.coroutineContext)*/

        sharesQuotes = repository.favoriteSharesFlow().asLiveData(Dispatchers.Main + viewModelScope.coroutineContext)
    }

    fun getQuotesIndexes(): LiveData<List<QuoteIndex>> = quotesIndexes

    fun getQuotesCurrencies(): LiveData<List<QuoteCurrency>> = quotesCurrencies

    fun getQuoteTickers(): LiveData<List<QuoteTicker>> = quotesTickers

    fun getQuoteShares(): LiveData<List<QuoteTicker>> {
        Log.d("9999", "getQuoteShares: $sharesQuotes")
        return sharesQuotes
    }

    private fun initializeQuotes() {
        quotesIndexes.value = listIndexes
        quotesCurrencies.value = listCurrency
        //quotesTickers.value = listTickers
    }

}