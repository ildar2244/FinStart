package ru.axdar.finstart.screens.portfolio

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portfolio_total_equity_product.view.*
import ru.axdar.finstart.R

class ChartEquityProductVH(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(equity: ChartEquityProduct) {
        itemView.apply {
            equity.color.let {
                tv_pf_part_color.setBackgroundColor(Color.parseColor(it))
                //tv_pf_part_color.setBgColor(it) //for CustomView
            }
            tv_pf_part_about.text = resources.getString(
                R.string.portfolio_equity_product_about, equity.name, equity.dolya
            )
            tv_pf_part_value.text = resources.getString(
                R.string.portfolio_equity_product_summa, equity.summa
            )
            tv_pf_part_change_count.text = resources.getString(
                R.string.portfolio_equity_product_change_summa, equity.change_summa
            )
            tv_pf_part_change_percent.text = resources.getString(
                R.string.portfolio_equity_product_change_percent, equity.change_percent
            )
        }
    }
}