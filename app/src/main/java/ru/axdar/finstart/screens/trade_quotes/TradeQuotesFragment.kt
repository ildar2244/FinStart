package ru.axdar.finstart.screens.trade_quotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trade_quotes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.axdar.finstart.R
import ru.axdar.finstart.data.AppDatabase
import ru.axdar.finstart.data.trade_quotes.ShareQuoteDao
import ru.axdar.finstart.data.trade_quotes.ShareQuoteFromMoex
import ru.axdar.finstart.screens.trade_quotes.adapters.QuoteDataAdapter

class TradeQuotesFragment : Fragment() {

    private lateinit var viewModel: TradeQuotesViewModel
    private lateinit var adapterDataIndexes: QuoteDataAdapter
    private lateinit var adapterDataCurrency: QuoteDataAdapter
    private lateinit var adapterDataWatchList: QuoteDataAdapter

    private val listTickers = listOf(
        ShareQuoteFromMoex(ticker = "CHMF", lastTransactionPrice = 2751.1623f), //, lastChange = 0.0029f),
        ShareQuoteFromMoex(ticker = "MNGT", lastTransactionPrice = 0.003249f), //, lastChange = 0.0799f),
        ShareQuoteFromMoex(ticker = "LSRG", lastTransactionPrice = 7.1422f), //, lastChange = -0.1299f),
        ShareQuoteFromMoex(ticker = "TATNP", lastTransactionPrice = 92.33f), //, lastChange = -0.1299f)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TradeQuotesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_trade_quotes, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TradeQuotesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("9999", "onViewCreated: START")

        //ИНДЕКСЫ
        adapterDataIndexes = QuoteDataAdapter()
        rv_quotes_index.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            adapter = adapterDataIndexes
        }

        //ВАЛЮТА
        adapterDataCurrency = QuoteDataAdapter()
        rv_quotes_currency.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            adapter = adapterDataCurrency
        }

        //WatchList
        adapterDataWatchList = QuoteDataAdapter()
        rv_quotes_watchlist.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            adapter = adapterDataWatchList
        }

        viewModel.apply {
            /*getQuotesIndexes().observe(viewLifecycleOwner, Observer {
                adapterDataIndexes.addHeaderAndSubmitList("Индексы", it)
            })
            getQuotesCurrencies().observe(viewLifecycleOwner, Observer {
                adapterDataCurrency.addHeaderAndSubmitList("Валюта", it)
            })*/
            /*getQuoteTickers().observe(viewLifecycleOwner, Observer {
                adapterDataWatchList.addHeaderAndSubmitList("Избранное", it)
            })*/
            Log.d("9999", "onViewCreated: VIEW_MODEL")
            getQuoteShares().observe(viewLifecycleOwner, Observer {
                Log.d("9999", "onViewCreated: $it")
                adapterDataWatchList.addHeaderAndSubmitList("Избранное", it)
            })
        }

        fab.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                populateDatabase(AppDatabase.getDatabase(requireContext(), viewModel.viewModelScope).shareQuoteDao())
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