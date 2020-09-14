package ru.axdar.finstart.screens.portfolio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.axdar.finstart.R

class PortfolioTotalEquityProductAdapter : RecyclerView.Adapter<ChartEquityProductVH>() {

    private var list: List<ChartEquityProduct> = listOf()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartEquityProductVH {
        return ChartEquityProductVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_portfolio_total_equity_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChartEquityProductVH, position: Int) {

        holder.bind(list[position])
    }

    internal fun setList(list: List<ChartEquityProduct>) {
        this.list = list
        notifyDataSetChanged()
    }
}