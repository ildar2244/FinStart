package ru.axdar.finstart.data.trade_quotes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.axdar.finstart.models.QuoteTicker

class ShareQuoteRepository(private val shareDao: ShareQuoteDao) {

    fun favoriteSharesFlow(): Flow<List<QuoteTicker>> {
        return shareDao.getShareQuotesFlow()
            .map {
                val ml = mutableListOf<QuoteTicker>()
                it.forEach { moex ->
                    ml.add(moex.toShareUI())
                }
                ml.toList()
            }
    }

    suspend fun insert(shareQuote: ShareQuoteFromMoex) {
        shareDao.insert(shareQuote)
    }

    private fun ShareQuoteFromMoex.toShareUI() = QuoteTicker(
        id = 1,
        name = ticker,
        datetime = "2020-10-19",
        value = lastTransactionPrice, //lastTransactionPrice,
        change = 0.3f, //lastChange
    )
}