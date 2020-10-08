package ru.axdar.finstart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.inc_content_main.*
import ru.axdar.finstart.screens.drawer_menu.NavigationDrawerFragment
import ru.axdar.finstart.screens.portfolio.PortfolioFragment
import ru.axdar.finstart.screens.trade_quotes.TradeQuotesFragment
import ru.axdar.finstart.screens.transactions.TransactionsFragment
import ru.axdar.finstart.utilits.APP_ACTIVITY

class MainActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APP_ACTIVITY = this

        setFragment(TradeQuotesFragment.newInstance())
        setListeners()

        //setSupportActionBar(toolbar)
        //initNav()
        //initDrawerToggle()
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
                R.id.menu_planner -> {
                    setFragment(NavigationDrawerFragment())
                    true
                }
                else -> false
            }
        }
    }

    /*private fun initNav() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_main)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_alerts, R.id.menu_settings, R.id.menu_share, R.id.menu_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //bottomNavView.setupWithNavController(navController)
        //bottomNavView.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener)
    }*/

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //управление боковым меню
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    /*private fun initDrawerToggle() {
        //без этого вместо "гамбургера" будет стрелка
        val drawerToggle = ActionBarDrawerToggle(
            this, drawer_layout, R.string.drawer_open, R.string.drawer_close
        )
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }*/

    /*override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }*/

    /*private val onBottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        item.isChecked = true
        when (item.itemId) {
            R.id.menu_quotes -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_quotes)
            }
            R.id.menu_portfolio -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_portfolio)
            }
            R.id.menu_transactions -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_transactions)
            }
        }
        false
    }*/
}