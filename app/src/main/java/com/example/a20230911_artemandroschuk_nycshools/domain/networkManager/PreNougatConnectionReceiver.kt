package com.example.a20230911_artemandroschuk_nycshools.domain.networkManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.a20230911_artemandroschuk_nycshools.presentation.eventBus.EventBus
import com.example.a20230911_artemandroschuk_nycshools.presentation.eventBus.events.ConnectionLostEvent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
@DelicateCoroutinesApi
class PreNougatConnectionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        // pre nougat versions only!
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N && intent?.action == CONNECTIVITY_CHANGE) {
            GlobalScope.launch {
                EventBus.emitEvent(ConnectionLostEvent())
            }
        }
    }
}