package com.game.space.shoote.black

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.appsflyer.AppsFlyerLib
import com.game.space.shoote.R
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("ActivityPREF", MODE_PRIVATE)
        if (prefs.getBoolean("activity_exec", false)) {
            Intent(this, TransitAct::class.java).also { startActivity(it) }
            finish()
        } else {
            val exec = prefs.edit()
            exec.putBoolean("activity_exec", true)
            exec.apply()
        }


        viewModel.deePP(this)
        AppsFlyerLib.getInstance()
            .init(CNST.AF_DEV_KEY, viewModel.conversionDataListener, applicationContext)
        AppsFlyerLib.getInstance().start(this)
        afNullRecordedOrNotChecker(1500)
    }
    private fun toTestGrounds() {
        Intent(this, TransitAct::class.java)
            .also { startActivity(it) }
        finish()

    }

    private fun afNullRecordedOrNotChecker(timeInterval: Long): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            while (NonCancellable.isActive) {
                val hawk1: String? = Hawk.get(CNST.C1)
                if (hawk1 != null) {
                    Log.d("TestInUIHawk", hawk1.toString())
                    toTestGrounds()
                    break
                } else {
                    val hawk1: String? = Hawk.get(CNST.C1)
                    Log.d("TestInUIHawkNulled", hawk1.toString())
                    delay(timeInterval)
                }
            }
        }
    }
}