package com.example.android.chartapp.data.di

import com.example.android.chartapp.data.network.EchoWebSocketListener
import com.example.android.chartapp.data.network.WsListener
import com.rabtman.wsmanager.WsManager
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

val networkModule = module {

    single {
        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient().newBuilder()
            .pingInterval(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(log)
            .build()
    }

//    single {
//        EchoWebSocketListener()
//    }

//    single {
//        Request.Builder().url("wss://api.bitfinex.com/ws").build()
//    }
//
//    single {
//        get<OkHttpClient>().newWebSocket(get<Request>(), get<EchoWebSocketListener>())
//    }
//
//    single {
//        get<OkHttpClient>().dispatcher.executorService.shutdown()
//    }

    single {
        WsManager.Builder(androidContext())
            .wsUrl("wss://api.bitfinex.com/ws")
            .needReconnect(true)
            .client(get<OkHttpClient>())
            .build()
    }

    single {
        WsListener()
    }

    single {
        get<WsManager>().setWsStatusListener(get())
    }
}