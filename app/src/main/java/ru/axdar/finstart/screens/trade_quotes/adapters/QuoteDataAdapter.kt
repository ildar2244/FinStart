package ru.axdar.finstart.screens.trade_quotes.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.axdar.finstart.models.QuoteCurrency
import ru.axdar.finstart.models.QuoteData
import ru.axdar.finstart.models.QuoteIndex
import ru.axdar.finstart.screens.trade_quotes.viewholders.CurrencyItemViewHolder
import ru.axdar.finstart.screens.trade_quotes.viewholders.HeaderViewHolder
import ru.axdar.finstart.screens.trade_quotes.viewholders.IndexItemViewHolder
import java.lang.ClassCastException

class QuoteDataAdapter : ListAdapter<QuoteItem, RecyclerView.ViewHolder>(QuoteDiffCallback()) {

    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM_INDEX = 1
    private val ITEM_VIEW_TYPE_ITEM_CURRENCY = 2
    private val ITEM_VIEW_TYPE_ITEM_WATCHLICT = 3
    private val adapterScope = CoroutineScope(Dispatchers.Default)
    private lateinit var mList: List<QuoteItem>

    //список с заголовком
    fun addHeaderAndSubmitList(title: String, list: List<QuoteData>?) {
        adapterScope.launch {
            val items = when(list) {
                null -> listOf(QuoteItem.Header("Нет данных"))
                else -> listOf(QuoteItem.Header(title)) +
                        list.map { QuoteItem.ItemData(it) }
            }
            mList = items
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    //так можно отобразить в одном RecyclerView
    fun addHeaderAndMap(map: Map<String, List<QuoteData>>) {
        adapterScope.launch {
            val mutList = mutableListOf<QuoteItem>()
            map.forEach { (k, vList) ->
                mutList.add(QuoteItem.Header(k))
                vList.forEach { qd ->
                    mutList.add(QuoteItem.ItemData(qd))
                }
            }
            Log.d("9999", "addHeaderHashMap: MTL:$mutList")
            mList = mutList
            withContext(Dispatchers.Main) {
                submitList(mutList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM_INDEX -> IndexItemViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM_CURRENCY -> CurrencyItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> {
                val header = getItem(position) as QuoteItem.Header
                holder.bind(header.title)
            }
            is IndexItemViewHolder -> {
                val itemData = getItem(position) as QuoteItem.ItemData
                holder.bind(itemData.quoteData as QuoteIndex)
            }
            is CurrencyItemViewHolder -> {
                val itemData = getItem(position) as QuoteItem.ItemData
                holder.bind(itemData.quoteData as QuoteCurrency)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when(getItem(position)) {
            is QuoteItem.Header -> ITEM_VIEW_TYPE_HEADER
            is QuoteItem.ItemData -> {
                val data = mList[position] as QuoteItem.ItemData
                when(data.quoteData) {
                    is QuoteIndex -> ITEM_VIEW_TYPE_ITEM_INDEX
                    is QuoteCurrency -> ITEM_VIEW_TYPE_ITEM_CURRENCY
                    else -> -1
                }
            }
        }

        /*return when(getItem(position)) {
            is QuoteItem.Header -> ITEM_VIEW_TYPE_HEADER
            is QuoteItem.ItemData -> when(mList[position-1]) {
                is QuoteIndex -> ITEM_VIEW_TYPE_ITEM_INDEX
                is QuoteCurrency -> ITEM_VIEW_TYPE_ITEM_CURRENCY
                else -> -1
            }
        }*/
    }

}

