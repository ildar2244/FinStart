package ru.axdar.finstart.screens.trade_quotes

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quote_index.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.axdar.finstart.R
import ru.axdar.finstart.models.QuoteIndex
import java.lang.ClassCastException

class QuoteIndexAdapter : ListAdapter<QuoteIndexItem,
        RecyclerView.ViewHolder>(QuoteIndexDiffCallback()) {

    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<QuoteIndex>?) {
        adapterScope.launch {
            val items = when(list) {
                null -> listOf(QuoteIndexItem.Header)
                else -> listOf(QuoteIndexItem.Header) + list.map { QuoteIndexItem.IndexDataItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> IndexHeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> IndexItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is IndexItemViewHolder -> {
                val indexItem = getItem(position) as QuoteIndexItem.IndexDataItem
                holder.bind(indexItem.quoteIndex)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is QuoteIndexItem.Header -> ITEM_VIEW_TYPE_HEADER
            is QuoteIndexItem.IndexDataItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class IndexHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): IndexHeaderViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_quote_index, parent, false)
                return IndexHeaderViewHolder(view)
            }
        }
    }

    class IndexItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): IndexItemViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_quote_index, parent, false)
                return IndexItemViewHolder(view)
            }
        }

        fun bind(quote: QuoteIndex) {
            with(itemView) {
                tv_quote_index_name.text = quote.name
                tv_quote_index_time_update.text = quote.datetime
                tv_quote_index_value.text = quote.value.toString()
                tv_quote_index_change.text = context.getString(R.string.quote_index_change, "%.2f".format(quote.change))

                if (quote.change > 0) {
                    tv_quote_index_change.setTextColor(Color.GREEN)
                } else {
                    tv_quote_index_change.setTextColor(Color.RED)
                }
            }
        }
    }
}

class QuoteIndexDiffCallback : DiffUtil.ItemCallback<QuoteIndexItem>() {
    override fun areItemsTheSame(oldItem: QuoteIndexItem, newItem: QuoteIndexItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: QuoteIndexItem, newItem: QuoteIndexItem): Boolean {
        return oldItem == newItem
    }
}

sealed class QuoteIndexItem {

    abstract val id: Long

    data class IndexDataItem(val quoteIndex: QuoteIndex) : QuoteIndexItem() {
        override val id: Long = quoteIndex.indexId
    }

    object Header : QuoteIndexItem() {
        override val id: Long = Long.MIN_VALUE
    }
}