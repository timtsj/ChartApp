package com.example.android.chartapp.presentation.feature.favorite

import android.os.Bundle
import android.view.View
import com.example.android.chartapp.R
import com.example.android.chartapp.presentation.base.BaseFragment
import com.example.android.chartapp.presentation.base.adapter.regular.CompositeAdapter
import com.example.android.chartapp.presentation.utils.setItems
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * @author Timur Seisembayev
 * @since 25.02.2020
 */

class FavoriteFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_favorite

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CompositeAdapter.Builder()
            .add(FavoriteAdapter())
            .build()
        rv_favorite.adapter = adapter

        val list = mutableListOf<FavoriteDvo>()
        repeat(5) {
            list.add(
                FavoriteDvo(
                    0,
                    "USD/BTC"
                )
            )
        }
        rv_favorite.setItems(list)
    }
}