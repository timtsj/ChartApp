package com.example.android.chartapp.data.repository

import com.example.android.chartapp.domain.repository.ChartsRepository
import com.google.gson.JsonObject
import com.rabtman.wsmanager.WsManager
import okhttp3.WebSocket

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

class ChartsRepositoryImpl(
    private val socket: WsManager
) : ChartsRepository {

    override fun getCharts() {
        val ticker = JsonObject().apply {
            addProperty("event", "subscribe")
            addProperty("channel", "ticker")
            addProperty("pair", "BTCUSD")
        }

        socket.sendMessage(ticker.toString())
    }
}