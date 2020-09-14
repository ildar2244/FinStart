package ru.axdar.finstart.screens.portfolio

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_portfolio_total_equity.*
import kotlinx.android.synthetic.main.inc_portfolio_total_value.*
import ru.axdar.finstart.R

class PortfolioTotalEquityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio_total_equity, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PortfolioTotalEquityFragment()
    }

    override fun onStart() {
        super.onStart()
        initFields()
    }

    fun initFields() {
        val totalValue = DecimalFormat().apply { groupingSize = 3 }.format(4038579).replace(",", " ")
        tv_portfolio_value.text = totalValue
        tv_portfolio_value_change.text = getString(R.string.portfolio_value_change, 203640)
        tv_portfolio_change_interval.text = "/ месяц"

        val testList: List<ChartEquityProduct> = listOf(
            ChartEquityProduct("Акции", color = "#33CCCC", dolya = "52", summa = "2 350 880", change_summa = "57 525", change_percent = "+ 2,64"),
            ChartEquityProduct("Валюта", color = "#FFE240", dolya = "35", summa = "1 522 009", change_summa = "1 788", change_percent = "- 0,12"),
            ChartEquityProduct("Вклад", color = "#E238A7", dolya = "13", summa = "671 343", change_summa = "0", change_percent = "+ 0,00")
        )

        rv_portfolio_equity_products.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = PortfolioTotalEquityProductAdapter().apply { setList(testList) }
        }
    }
}