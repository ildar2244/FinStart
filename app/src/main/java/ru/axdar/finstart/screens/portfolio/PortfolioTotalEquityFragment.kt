package ru.axdar.finstart.screens.portfolio

import android.graphics.Color
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
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

        fillChart()
        fillLegend()

        val testList: List<ChartEquityProduct> = listOf(
            ChartEquityProduct("Акции", color = "#33CCCC", dolya = "52.1", summa = "2 350 880", change_summa = "57 525", change_percent = "+ 2,64"),
            ChartEquityProduct("Валюта", color = "#FFE240", dolya = "34.7", summa = "1 522 009", change_summa = "1 788", change_percent = "- 0,12"),
            ChartEquityProduct("Вклад", color = "#E238A7", dolya = "13.2", summa = "671 343", change_summa = "0", change_percent = "+ 0,00")
        )

        rv_portfolio_equity_products.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = PortfolioTotalEquityProductAdapter().apply { setList(testList) }
        }
    }

    private fun fillChart() {
        val xvalues = ArrayList<PieEntry>()
        xvalues.add(PieEntry(34.7f, "Валюта"))
        xvalues.add(PieEntry(13.2f, "Вклады"))
        xvalues.add(PieEntry(52.1f, "Акции"))

        val dataSet = PieDataSet(xvalues, "")
        val data = PieData(dataSet)

        // In Percentage
        //data.setValueFormatter(PercentFormatter(chart_portfolio_total_equity))
        //chart_portfolio_total_equity.setUsePercentValues(true)

        dataSet.colors = listOf(Color.parseColor("#FFE240"), Color.parseColor("#E238A7"), Color.parseColor("#33CCCC"))
        data.setValueTextSize(13f)

        chart_portfolio_total_equity.data = data
        chart_portfolio_total_equity.description.isEnabled = false
        chart_portfolio_total_equity.setDrawEntryLabels(false) // To remove labels from piece of pie

        //"бублик"+скругление
        chart_portfolio_total_equity.isDrawHoleEnabled = true
        //chart_portfolio_total_equity.setDrawRoundedSlices(true)
    }

    private fun fillLegend() {
        val l = chart_portfolio_total_equity.legend
        chart_portfolio_total_equity.legend.isWordWrapEnabled = true
        chart_portfolio_total_equity.legend.isEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT // position
        l.formSize = 8F
        l.formToTextSpace = 8f // отступ текста
        l.form = Legend.LegendForm.CIRCLE // form type : line, square, circle ..
        l.textSize = 10f
        l.orientation = Legend.LegendOrientation.VERTICAL // side by side or bottom to bottom
        l.isWordWrapEnabled = true
    }
}