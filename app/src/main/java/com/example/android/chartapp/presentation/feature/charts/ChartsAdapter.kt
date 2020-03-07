package com.example.android.chartapp.presentation.feature.charts

import com.example.android.chartapp.R
import com.example.android.chartapp.presentation.base.adapter.regular.KDelegateAdapter
import kotlinx.android.synthetic.main.item_chart.*

/**
 * @author Timur Seisembayev
 * @since 28.02.2020
 */

class ChartsAdapter : KDelegateAdapter<ChartDvo>() {

    override val layoutId: Int = R.layout.item_chart

    override fun isForViewType(items: List<*>, position: Int): Boolean = items[position] is ChartDvo

    override fun onBind(item: ChartDvo, viewHolder: KViewHolder) = with(viewHolder) {
        tv_title.text = item.title
    }
}