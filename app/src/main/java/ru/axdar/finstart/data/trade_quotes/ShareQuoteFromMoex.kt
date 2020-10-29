package ru.axdar.finstart.data.trade_quotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "share_quotes_moex")
data class ShareQuoteFromMoex(
    @PrimaryKey
    @ColumnInfo(name = "ticker") val ticker: String, //SECID
    @ColumnInfo(name = "last_price") val lastTransactionPrice: Float, //LAST
    //@ColumnInfo(name = "last_change") val lastChange: Float, //LASTCHANGE
    //val lastChangePercent: Double = 0.0, //LASTCHANGEPRCNT
    //val marketToday: Double = 0.0, //MARKETPRICETODAY
    //val marketYesterday: Double = 0.0, //MARKETPRICE
    //val lastYesterday: Double = 0.0, //LASTTOPREVPRICE
    //val updateTime: String = "0-0", //UPDATETIME
    //val ofClose: Double = 0.0, //LCLOSEPRICE
    //val ofCurrent: Double = 0.0, //LCURRENTPRICE
    //val change: Double = 0.0, //CHANGE
)