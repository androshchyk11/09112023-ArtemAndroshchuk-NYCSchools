package com.example.a20230911_artemandroschuk_nycshools.domain.networkManager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.example.a20230911_artemandroschuk_nycshools.presentation.eventBus.EventBus
import com.example.a20230911_artemandroschuk_nycshools.presentation.eventBus.events.ConnectionLostEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class ConnectionManager @Inject constructor(@ApplicationContext context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager?

    var isConnected = MutableLiveData(isAvailableConnection())

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager?.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isConnected.postValue(true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    isConnected.postValue(false)
                }

            })
        }
        GlobalScope.launch {
            EventBus.events
                .filter { it == ConnectionLostEvent() }
                .collectLatest { isConnected.postValue(false) }
        }
    }

    @Suppress("DEPRECATION")
    fun isAvailableConnection(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager?.activeNetwork
            val capabilities = connectivityManager?.getNetworkCapabilities(network)
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
                    || capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
        } else {
            connectivityManager?.activeNetworkInfo?.isConnected ?: false
        }
    }
}