package ru.axdar.finstart.screens.trade_quotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trade_quotes.*
import ru.axdar.finstart.R
import ru.axdar.finstart.screens.trade_quotes.adapters.QuoteDataAdapter

class TradeQuotesFragment : Fragment() {

    private lateinit var viewModel: TradeQuotesViewModel
    private lateinit var adapterDataIndexes: QuoteDataAdapter
    private lateinit var adapterDataCurrency: QuoteDataAdapter
    private lateinit var adapterDataWatchList: QuoteDataAdapter

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
            getQuotesIndexes().observe(this@TradeQuotesFragment, Observer {
                adapterDataIndexes.addHeaderAndSubmitList("Индексы", it)
            })
            getQuotesCurrencies().observe(this@TradeQuotesFragment, Observer {
                adapterDataCurrency.addHeaderAndSubmitList("Валюта", it)
            })
            getQuoteTickers().observe(this@TradeQuotesFragment, Observer {
                adapterDataWatchList.addHeaderAndSubmitList("Избранное", it)
            })
        }
    }
}