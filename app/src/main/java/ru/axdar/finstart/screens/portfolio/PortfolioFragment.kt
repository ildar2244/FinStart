package ru.axdar.finstart.screens.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_portfolio.*
import ru.axdar.finstart.R

class PortfolioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PortfolioFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = listOf(PortfolioTotalEquityFragment.newInstance(), PortfolioEquityFragment.newInstance(), PortfolioCostsFragment.newInstance())
        pager_portfolio.adapter = PortfolioPagerAdapter(this, list)
        TabLayoutMediator(tab_portfolio, pager_portfolio) { tab, position ->
            when (position) {
                0 -> tab.text = "Состав"
                1 -> tab.text = "Активы"
                2 -> tab.text = "Расходы"
            }
        }.attach()
    }
}