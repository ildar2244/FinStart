package ru.axdar.finstart.screens.trade_quotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trade_quotes.*
import ru.axdar.finstart.R
import ru.axdar.finstart.models.QuoteCurrency
import ru.axdar.finstart.models.QuoteIndex
import ru.axdar.finstart.screens.trade_quotes.adapters.QuoteDataAdapter

class TradeQuotesFragment : Fragment() {

    private lateinit var adapterDataIndexes: QuoteDataAdapter
    private lateinit var adapterDataCurrency: QuoteDataAdapter
    private lateinit var adapterDataWatchList: QuoteDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trade_quotes, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TradeQuotesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listIndexes = listOf(
            QuoteIndex(1, "IMOEX", "11:48", 3054.56f, 0.1999f),
            QuoteIndex(1, "RTSI", "11:48", 1316.32f, 0.3799f),
            QuoteIndex(1, "S&P500", "19/08", 3361.44f, -0.4299f),
            QuoteIndex(1, "Nasdaq", "19/08", 11146.46f, -0.5799f)
        )
        val listSecond = listOf(
            QuoteCurrency(1, "eur/usd", "12:44", 1.1623f, 0.0029f),
            QuoteCurrency(1, "usd/rub", "12:05", 73.32f, 0.0799f),
            QuoteCurrency(1, "eur/rub", "01/08", 87.44f, -0.1299f)
        )

        val hm= mapOf("Индексы" to listIndexes, "Валюта" to listSecond)

        //ИНДЕКСЫ
        adapterDataIndexes = QuoteDataAdapter().apply {
            addHeaderAndSubmitList("Индексы", listIndexes)
            //addHeaderAndMap(hm)
        }
        rv_quotes_index.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            adapter = adapterDataIndexes
        }

        //ВАЛЮТА
        adapterDataCurrency = QuoteDataAdapter().apply {
            addHeaderAndSubmitList("Валюта", listSecond)
        }
        rv_quotes_currency.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            adapter = adapterDataCurrency
        }
    }
}