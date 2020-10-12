package ru.axdar.finstart.screens.drawer_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_navigation_drawer.*
import kotlinx.android.synthetic.main.inc_content_main.*
import ru.axdar.finstart.R
import ru.axdar.finstart.screens.alerts.AlertsFragment
import ru.axdar.finstart.screens.settings.SettingsFragment

class NavigationDrawerFragment : Fragment() {

    private lateinit var mActivity: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        val view: View = inflater.inflate(R.layout.fragment_navigation_drawer, container, false)

        initNav(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initNav(view: View) {

        val mToolbar: Toolbar = view.findViewById(R.id.toolbar)
        val drawerLayout: CustomDrawerLayout = view.findViewById(R.id.drawer_layout)
        val navView: NavigationView = view.findViewById(R.id.nav_view)

        mActivity = activity as AppCompatActivity
        mActivity.setSupportActionBar(mToolbar)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drawerToggle = ActionBarDrawerToggle(
            activity, drawerLayout, R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        //val navController = activity?.findNavController(R.id.nav_host_fragment)
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_alerts, R.id.menu_settings, R.id.menu_share, R.id.menu_send
            ), drawerLayout
        )

        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)

        setTitleInToolbar(resources.getString(R.string.menu_alerts))
        navView.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
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
    }

    private fun setTitleInToolbar(name: String) {
        mActivity.supportActionBar?.title = name
    }

    //выбор из выдвижной панели
    private fun selectDrawerItem(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.menu_alerts -> {
                childFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, AlertsFragment()).commit()
                setTitleInToolbar(resources.getString(R.string.menu_alerts))
            }
            R.id.menu_settings -> {
                childFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, SettingsFragment()).commit()
                setTitleInToolbar(resources.getString(R.string.menu_settings))
            }
            R.id.menu_share -> { Toast.makeText(requireContext(), "SHARE", Toast.LENGTH_SHORT).show() }
            R.id.menu_send -> {
                Toast.makeText(requireContext(), "SEND", Toast.LENGTH_SHORT).show()
            }
        }
    }
}