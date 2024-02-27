package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.Toast

class NetworkMonitoringUtil(private val _context: Context) : ConnectivityManager.NetworkCallback() {
    private val _networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()
    private val _connectivityManager: ConnectivityManager =
        _context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Toast.makeText(_context, "connected", Toast.LENGTH_SHORT).show()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Toast.makeText(_context, "disconnected", Toast.LENGTH_SHORT).show()
    }

    fun registerNetworkCallbackEvents() {
        _connectivityManager.registerNetworkCallback(_networkRequest, this)
    }
}