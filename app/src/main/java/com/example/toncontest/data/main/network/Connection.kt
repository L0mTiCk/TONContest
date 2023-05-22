package com.example.toncontest.data.main.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

fun checkConnection(context: Context, connection: (Boolean) -> Unit = {}, connected: (Boolean) -> Unit = {} ) {
    class MyNetworkCallback : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("transactions", "Network Available")
            connected(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("transactions", "Network Lost")
            connection(false)
        }
    }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    val networkCallback = MyNetworkCallback()

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager.requestNetwork(networkRequest, networkCallback)
    val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    val isWifiConnected = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
    val isMobileConnected = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
    if (isWifiConnected || isMobileConnected) {
        Log.d("transactions", "Wifi est")
        connection(true)
    } else {
        Log.d("transactions", "Wifi net")
        connection(false)
    }
}