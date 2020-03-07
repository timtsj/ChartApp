package com.example.android.chartapp.presentation.feature.favorite

import com.example.android.chartapp.R
import com.example.android.chartapp.presentation.base.adapter.regular.KDelegateAdapter
import kotlinx.android.synthetic.main.item_favorite.*

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

class FavoriteAdapter : KDelegateAdapter<FavoriteDvo>() {

    override val layoutId: Int = R.layout.item_favorite

    override fun isForViewType(items: List<*>, position: Int): Boolean = items[position] is FavoriteDvo

    override fun onBind(item: FavoriteDvo, viewHolder: KViewHolder) = with(viewHolder) {
        tv_favorite_title.text = item.title
    }
}