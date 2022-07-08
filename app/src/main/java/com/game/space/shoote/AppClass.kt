package com.game.space.shoote

import android.app.Application
import android.content.Context
import com.game.space.shoote.black.AI
import com.game.space.shoote.black.CNST
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val ONESIGNAL_APP_ID = "cdb680b6-96fa-4c3d-a0a7-7055806cc81d"
@HiltAndroidApp
class AppClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        GlobalScope.launch(Dispatchers.IO) {
            applyDeviceId(context = applicationContext)
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)


    }

    private suspend fun applyDeviceId(context: Context) {
        val advertisingInfo = AI(context)
        val idInfo = advertisingInfo.getAdvertisingId()
        Hawk.put(CNST.MAIN_ID, idInfo)
    }
}

