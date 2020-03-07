package com.example.android.chartapp.presentation.feature.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.android.chartapp.R
import com.example.android.chartapp.presentation.base.BaseActivity
import com.example.android.chartapp.presentation.feature.charts.ChartsFragment
import com.example.android.chartapp.presentation.feature.favorite.FavoriteFragment
import com.rabtman.wsmanager.WsManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.WebSocket
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val socket: WsManager by inject()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.setGraph(R.navigation.main_graph)
        bottom_nav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.label) {
                ChartsFragment::class.java.simpleName,
                FavoriteFragment::class.java.simpleName -> showBottomNav(true)
                else -> showBottomNav(false)
            }
        }
    }

    private fun showBottomNav(show: Boolean) {
        bottom_nav.isVisible = show
    }

    override fun onStart() {
        super.onStart()
        socket.startConnect()
    }

    override fun onStop() {
        socket.stopConnect()
        super.onStop()
    }
}