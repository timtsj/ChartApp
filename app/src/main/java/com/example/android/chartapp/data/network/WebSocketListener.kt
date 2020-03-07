package com.example.android.chartapp.data.network

import com.rabtman.wsmanager.listener.WsStatusListener
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import timber.log.Timber

class EchoWebSocketListener : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Timber.i("onOpen : ${response.message}")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Timber.i("Receiving : $text")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Timber.i("Receiving bytes : " + bytes.hex())
    }

    override fun onClosing(
        webSocket: WebSocket,
        code: Int,
        reason: String
    ) {
        Timber.i("Closing : $code / $reason")
    }

    override fun onFailure(
        webSocket: WebSocket,
        t: Throwable,
        response: Response?
    ) {
        Timber.e("Error : " + t.message)
    }
}

class WsListener : WsStatusListener() {

    override fun onOpen(response: Response?) {
        super.onOpen(response)
        Timber.i("onOpen ${response?.message}")
    }

    override fun onFailure(t: Throwable?, response: Response?) {
        super.onFailure(t, response)
        Timber.i("onFailure ${t?.message} ${response?.message}")
    }

    override fun onReconnect() {
        super.onReconnect()
        Timber.i("onReconnect")
    }

    override fun onClosing(code: Int, reason: String?) {
        super.onClosing(code, reason)
        Timber.i("onClosing $code $reason")
    }

    override fun onMessage(text: String?) {
        super.onMessage(text)
        Timber.i("onMessage $text")
    }

    override fun onMessage(bytes: ByteString?) {
        super.onMessage(bytes)
        Timber.i("onMessage bytes ${bytes?.hex()}")
    }

    override fun onClosed(code: Int, reason: String?) {
        super.onClosed(code, reason)
        Timber.i("onClosed $code $reason")
    }
}