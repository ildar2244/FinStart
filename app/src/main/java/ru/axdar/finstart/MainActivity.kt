package ru.axdar.finstart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.axdar.finstart.screens.portfolio.PortfolioFragment
import ru.axdar.finstart.screens.trade_quotes.TradeQuotesFragment
import ru.axdar.finstart.screens.transactions.TransactionsFragment
import ru.axdar.finstart.utilits.APP_ACTIVITY

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APP_ACTIVITY = this

        setFragment(TradeQuotesFragment.newInstance())
        setListeners()

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment)
            .commit()
    }

    fun setListeners() {
        bottom_nav_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_quotes -> {
                    setFragment(TradeQuotesFragment.newInstance())
                    true
                }
                R.id.menu_portfolio -> {
                    setFragment(PortfolioFragment.newInstance())
                    true
                }
                R.id.menu_transactions -> {
                    setFragment(TransactionsFragment())
                    true
                }
                else -> false
            }
        }
    }
}