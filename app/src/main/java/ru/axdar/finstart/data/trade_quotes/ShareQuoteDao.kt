package ru.axdar.finstart.data.trade_quotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShareQuoteDao {

    @Query("SELECT * FROM share_quotes_moex")
    fun getShares(): List<ShareQuoteFromMoex>

    @Query("SELECT * FROM share_quotes_moex")
    fun getShareQuotes(): LiveData<List<ShareQuoteFromMoex>>

    @Query("SELECT * FROM share_quotes_moex")
    fun getShareQuotesFlow(): Flow<List<ShareQuoteFromMoex>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shareQuoteFromMoex: ShareQuoteFromMoex)

    @Query("DELETE FROM share_quotes_moex")
    suspend fun deleteAll()
}