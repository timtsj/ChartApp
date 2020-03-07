package com.example.android.chartapp.presentation.feature.charts

import android.os.Bundle
import android.view.View
import com.example.android.chartapp.R
import com.example.android.chartapp.presentation.base.BaseFragment
import com.example.android.chartapp.presentation.base.adapter.regular.CompositeAdapter
import com.example.android.chartapp.presentation.utils.setItems
import kotlinx.android.synthetic.main.fragment_charts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Timur Seisembayev
 * @since 25.02.2020
 */

class ChartsFragment : BaseFragment() {

    private val viewModel: ChartsViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_charts

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CompositeAdapter.Builder()
            .add(ChartsAdapter())
            .build()
        rv_charts.adapter = adapter

        val list = mutableListOf<ChartDvo>()
        repeat(5) {
            list.add(
                ChartDvo(
                    0,
                    "BTC/USD"
                )
            )
        }
        rv_charts.setItems(list)

        btn_get_charts.setOnClickListener { viewModel.get() }
    }
}