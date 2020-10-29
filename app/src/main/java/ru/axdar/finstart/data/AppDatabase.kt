package ru.axdar.finstart.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.axdar.finstart.data.trade_quotes.ShareQuoteDao
import ru.axdar.finstart.data.trade_quotes.ShareQuoteFromMoex

@Database(entities = [ShareQuoteFromMoex::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shareQuoteDao(): ShareQuoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                Log.d("9999", "getDatabase: TEMP:$tempInstance")
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finstart-database"
                ).fallbackToDestructiveMigration().build()
                    //.fallbackToDestructiveMigration() //схема миграции: сейчас всё удаляем //.addCallback(AppDatabaseCallback(scope))
                    //.build()
                INSTANCE = instance
                Log.d("9999", "getDatabase: instance:$instance")
                return instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        private val listTickers = listOf(
            ShareQuoteFromMoex(ticker = "NLMK", lastTransactionPrice = 161.1623f), //, lastChange = 0.0029f),
            ShareQuoteFromMoex(ticker = "VTBR", lastTransactionPrice = 0.003249f), //, lastChange = 0.0799f),
            ShareQuoteFromMoex(ticker = "LSRG", lastTransactionPrice = 7.1422f), //, lastChange = -0.1299f),
            ShareQuoteFromMoex(ticker = "CSCO", lastTransactionPrice = 42.33f), //, lastChange = -0.1299f)
        )

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.shareQuoteDao())
                }
            }
        }

        suspend fun populateDatabase(dao: ShareQuoteDao) {
            dao.deleteAll()
            listTickers.forEach {
                dao.insert(it)
            }
        }
    }
}